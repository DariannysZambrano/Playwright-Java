package com;

import org.junit.jupiter.api.Assertions;
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
                .setHeadless(false)
                .setSlowMo(1500)    
        );
        Page page = browser.newPage();

        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("[placeholder= Buscar]").fill("pliers"); // estoy utilizando un selector css para localizar el campo de búsqueda, y luego le digo que escriba "pliers" dentro de ese campo
        page.locator("button:has-text(Buscar)").click();

        int matchingSearchResults = page.locator(".card").count();

        Assertions.assertTrue(matchingSearchResults > 0);

        browser.close();
        playwright.close();

    }    
}
