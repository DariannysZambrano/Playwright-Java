package com.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class SearchTest {
    
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1500) 
                
        );
        
        playwright.selectors().setTestIdAttribute("data-test");
        page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
    }

    @AfterEach
    void closeAll() {
        browser.close();
        playwright.close();
    }

    
    //1. ejercicio verificar el nombre del logo 
    @Test
    void verificarTitulo() {
        //obtener titulo  
        String titulo = page.title();
        //verificar titulo 
        Assertions.assertEquals("Practice Software Testing - Toolshop - v5.0", titulo);
    }

    //2. ejercicio buscar menu de categorias
    @Test
    void buscarMenuCategorias() {
        //ubicarnos en categorias
        page.locator("[data-test=\"nav-categories\"]").click();
        //seleccionar categoria Hand Tools
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Herramientas manuales")).click();
        //verificar heading
        String h2Text = page.locator("h2").textContent();
        Assertions.assertTrue(h2Text.contains("Categoría: Hand Tools"));
    }

    //3. ejercicio buscar con placeholder
    @Test
    void buscarHammers(){
        page.getByPlaceholder("Buscar").fill("HAMMER");
        page.locator("[data-test=\"search-submit\"]").click();
        Integer countTarget = page.locator(".card").count();
        Assertions.assertTrue(countTarget > 1,"" + countTarget);
    }

    //4. ejercicio verificar que el login no se puede hacer sin crar una cuenta primero
    @Test
    void inicioDeseccionIncorrecto(){
        page.locator("[data-test=\"nav-sign-in\"]").click();
        page.getByLabel("Dirección de correo electrónico *").fill("DaRy@gmail.com");
        page.getByLabel("Contraseña *").fill("12345678");
        page.locator("[data-test=\"login-submit\"]").click();

        String errorMessage = page.locator("[data-test=\"login-error\"]").textContent();
        Assertions.assertEquals("Invalid email or password", errorMessage);
    }

    //5. verificar icono rojo del carrito de compras
    @Test
    void validacionToolTips(){
        page.locator("[data-test=\"product-01KMKG6BH4G65RPH7APEY7K66H\"]").click();

        Locator botonSuma = page.locator("[data-test=\"increase-quantity\"]");
        for(int i = 0; i < 2; i++){
            botonSuma.click();
        }

        page.locator("[data-test='add-to-cart']").click();
        Locator cantidadCarrito = page.locator("[data-test='cart-quantity']");
        cantidadCarrito.waitFor();
        Assertions.assertTrue(page.locator("[data-test=\"cart-quantity\"]").isVisible());
        Assertions.assertEquals("3", cantidadCarrito.innerText().trim());

    }

    //6. primera vez probando un filtro dinamico. Seleccionar una marca y verificar que todas las cards sean de esa marca en especifico
    @Test
    void filtrarPorMarcaYPrecio(){
        //aqui va el codigo para el slider

        page.locator(".checkbox");
        page.locator("[data-test=\"brand-01KMR3TESBYQMTA8GVW2XA1NGV\"]").check();
        Locator cards = page.locator(".card");

        for(int i = 0; i < cards.count(); i++){
            cards.nth(i).click();

        }

        }

    //ejercicio extra del curso
    @Test
    void searchForPliers(){
        page.getByPlaceholder("Buscar").fill("pliers");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Buscar")).click();

        assertThat(page.locator(".card").count()).isEqualTo(4);

        List<String> productNames = page.getByTestId("product-name").allTextContents();
        assertThat(productNames).allMatch(name -> name.contains("Pliers"));

        Locator outOfStockItemsLocator = page.locator(".card").filter(new Locator.FilterOptions().setHasText("Out of stock"))
            .getByTestId("product-name");

            assertThat(outOfStockItemsLocator.count()).isEqualTo(1);
            assertThat(outOfStockItemsLocator.innerText()).contains("Long Nose Pliers");

    }

}
