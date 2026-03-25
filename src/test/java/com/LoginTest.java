package com;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;


    @UsePlaywright(LoginTest.MisOpciones.class)
public class LoginTest {

    private static Browser browser;
    private static Page page;
    private static BrowserContext context;

    @BeforeAll
    static void launchBrowser(Playwright playwright) {
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false)
            .setSlowMo(1500));
        
        // Creamos un solo contexto y una sola página
        context = browser.newContext();
        page = context.newPage();
    }
    
    public static class MisOpciones implements OptionsFactory{
        @Override
        public Options getOptions() {
            return new Options()
            .setLaunchOptions(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1500)
        );

        }
    }

    @Test
    void showThePageTitle (Page page) {

        page.navigate("https://www.saucedemo.com/");
        String title = page.title();

        Assertions.assertThat(title).contains("Swag Labs");

    }

    @Test
    void loginWithValidCredentials (Page page) {

        page.navigate("https://www.saucedemo.com/");

        //2. rellenamos el usuario, esta es una forma de hacerlo
        page.fill("#user-name", "standard_user");

        //3. rellenamos la contraseña, esta es otra forma de hacerlo
        page.locator("#password").fill("secret_sauce");     

        page.locator("#login-button").click();

        page.locator(".shopping_cart_link").click();

        page.locator("#checkout").click();

    }
}

                


