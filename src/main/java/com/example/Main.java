package com.example;

import com.microsoft.playwright.*;

public class Main {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Esto abrirá una ventana de Chrome real (headless: false)
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            System.out.println("¡Éxito! El título es: " + page.title());
            
            // Esperamos 3 segundos para que veas que abrió
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            
            browser.close();
        }
    }
}