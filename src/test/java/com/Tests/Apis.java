package com.Tests;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import com.microsoft.playwright.APIRequest;

// mi guia paso a paso. 
public class Apis {
        Playwright playwright = Playwright.create();
    APIRequestContext requestContext = playwright.request().newContext();

    //primera peticion get 
    @Test
    void verificarProductosAPI() {

    
    // 1. En lugar de page.navigate, hacemos una petición GET
    // Esto es como pedir el menú de la biblioteca directamente
    APIResponse response = requestContext.get("/products");

    // 2. En lugar de Assertions sobre el texto de un h2, 
    // verificamos que el servidor nos respondió con éxito (Código 200)
    Assertions.assertEquals(200, response.status());

    // 3. Opcional: Imprimimos lo que nos devolvió para "verlo" (como el textContent)
    System.out.println(response.text());
}

@Test
void crearMensajeContactoAPI() {
    // Creamos los datos del "formulario" en un mapa
    Map<String, String> datosContacto = new HashMap<>();
    datosContacto.put("name", "Tu Nombre");
    datosContacto.put("subject", "Consulta");
    datosContacto.put("message", "Hola, esto es una prueba");

    // 1. Enviamos la petición POST
    // .setData(datosContacto) es lo que envía la información al servidor
    APIResponse response = requestContext.post("/messages", 
        RequestOptions.create().setData(datosContacto));

    // 2. Verificamos que se creó con éxito (Código 201)
    Assertions.assertEquals(201, response.status());
}
    }

    

