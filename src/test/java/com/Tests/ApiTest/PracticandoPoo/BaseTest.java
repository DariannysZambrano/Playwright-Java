package com.Tests.ApiTest.PracticandoPoo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseTest {

    // Api
    protected static Playwright playwright;
    protected static APIRequestContext requestContext;

    // UI
    protected static Browser browser;
    protected static BrowserContext context;
    protected Page page;

    @BeforeAll
    static void arrancarMotoresPlaywright() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(1500)

        );

    }

    @BeforeEach
    void PreparacionCadaTest(){

        context = browser.newContext();
        page = context.newPage();

         requestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions().setBaseURL("https://jsonplaceholder.typicode.com/"));
    }

    @AfterEach
    void cerrarElTest(){
        context.close();
    };

    @AfterAll
    static void cerrarElMotor(){
        playwright.close();
    }
}

