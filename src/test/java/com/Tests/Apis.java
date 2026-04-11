package com.Tests;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.APIRequest;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat; // Para assertThat(locator).isVisible()

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.stream.Stream;

class MakingAPIClass { 
    
    record Product(String name, double price) {}
   
    private static APIRequestContext requestContext;
    private static Playwright playwright;

    @BeforeAll
    public static void setupRequestContext() {
        playwright = Playwright.create();
        requestContext = playwright.request().newContext(
            new APIRequest.NewContextOptions()
                // Define la dirección principal de la API.
                .setBaseURL("https://api.practicesoftwaretesting.com/api")
                .setExtraHTTPHeaders(new HashMap<>() {{
                    put("Accept", "application/json");
                }})
        );
    }

    @DisplayName("Obtener productos desde la API")
    @ParameterizedTest(name = "Producto: {0}")
    @MethodSource("Products")
    void checkNowProducts(Product product) {
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com");
        
        
        page.fill("[placeholder='Search']", product.name());
        page.click("button:has-text('Search')");

        
        // Playwright permite un filtro a la vez por opciones, así que encadenamos los filtros.
        Locator productCard = page.locator(".card")
            .filter(new Locator.FilterOptions().setHasText(product.name()))
            .filter(new Locator.FilterOptions().setHasText(Double.toString(product.price())));

        assertThat(productCard).isVisible();
        
        // BUENA PRÁCTICA: Cerrar el navegador al terminar cada test
        browser.close();
    }

    static Stream<Product> Products() {
        APIResponse response = requestContext.get("/products?page=2");
        
        // Ajustado para usar aserciones nativas de JUnit 5
        Assertions.assertEquals(200, response.status());

        JsonObject jsonResponse = new Gson().fromJson(response.text(), JsonObject.class);
        JsonArray data = jsonResponse.getAsJsonArray("data");

        return data.asList().stream()
            .map(jsonElement -> {
                JsonObject productJson = jsonElement.getAsJsonObject();
                return new Product(
                    productJson.get("name").getAsString(),
                    productJson.get("price").getAsDouble()
                );
            });
    }
}