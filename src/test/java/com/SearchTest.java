package com;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SearchTest {
    
    @Test
    void search () {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(false) // false = no seas invisible, ¡muéstrate!
                .setSlowMo(1500)    // Pausa 1.5 segundos (1500 milisegundos) entre cada acción 
        );
        Page page = browser.newPage();

        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("[placeholder= Buscar]").fill("pliers"); // estoy utilizando un selector css para localizar el campo de búsqueda, y luego le digo que escriba "pliers"
        page.locator("button:has-text('Buscar')").click();

        int numberOfResults = page.locator(".product").count(); // cuento cuántos resultados hay, utilizando el selector css ".product" que es el que identifica cada resultado

    }    
}
