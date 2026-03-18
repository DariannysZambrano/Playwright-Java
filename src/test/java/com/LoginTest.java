package com;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LoginTest {

    @Test
    void showThePageTitle () {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(false) // false = no seas invisible, ¡muéstrate!
                .setSlowMo(1500)    // Pausa 1.5 segundos (1500 milisegundos) entre cada acción
        );
        
        Page page = browser.newPage();

        page.navigate("https://www.saucedemo.com/");
        String title = page.title();

        Assertions.assertThat(title).contains("Swag Labs");

        browser.close();
        playwright.close();

    }

    @Test
    void loginWithValidCredentials () {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(false) // false = no seas invisible, ¡muéstrate!
                .setSlowMo(1500)    // Pausa 1.5 segundos (1500 milisegundos) entre cada acción
        );
        Page page = browser.newPage();

        page.navigate("https://www.saucedemo.com/");

        //2. rellenamos el usuario, esta es una forma de hacerlo
        page.fill("#user-name", "standard_user");

        //3. rellenamos la contraseña, esta es otra forma de hacerlo
        page.locator("#password").fill("secret_sauce");     

        page.locator("#login-button").click();
    
        page.locator("#add-to-cart-sauce-labs-backpack").click();

        page.locator(".shopping_cart_link").click();

        page.locator(".cart_button").click();

        browser.close();
        playwright.close();
        
    }
}




