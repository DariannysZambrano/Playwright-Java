package com;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LoginTest {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void setup(){

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(false) // false = no seas invisible, ¡muéstrate!
                .setSlowMo(1500)    // Pausa 1.5 segundos (1500 milisegundos) entre cada acción
        );
        page = browser.newPage();
    }


    @AfterEach
    void teardown(){
        browser.close();
        playwright.close();
    }
    
    @Test
    void showThePageTitle () {

        page.navigate("https://www.saucedemo.com/");
        String title = page.title();

        Assertions.assertThat(title).contains("Swag Labs");

        browser.close();
        playwright.close();

    }

    @Test
    void loginWithValidCredentials () {

        page.navigate("https://www.saucedemo.com/");

        //2. rellenamos el usuario, esta es una forma de hacerlo
        page.fill("#user-name", "standard_user");

        //3. rellenamos la contraseña, esta es otra forma de hacerlo
        page.locator("#password").fill("secret_sauce");     

        page.locator("#login-button").click();
    
        page.locator("#add-to-cart-sauce-labs-backpack").click();

        page.locator(".shopping_cart_link").click();

        page.locator(".cart_button").click();

    }
}




