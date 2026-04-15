package com.Tests.ApiTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetProductsTest {

    @Test
    public void setupRequestContext() {

        try (Playwright playwright = Playwright.create()) {
            APIRequestContext requestContext = playwright.request().newContext(
                    new APIRequest.NewContextOptions().setBaseURL("https://jsonplaceholder.typicode.com/"));

            List<String> nombresObtenidos = obtenerNombresProductos(requestContext);

            for (int i = 0; i < nombresObtenidos.size(); i++) {
                String nombreModificado = nombresObtenidos.get(i) + " - " + (i + 1);
                enviarPostAbierto(requestContext, nombreModificado);
            }
        }
    }

    // get

    public static List<String> obtenerNombresProductos(APIRequestContext request) {
        APIResponse response = request.get("/todos");
        JsonArray arregloJson = new Gson().fromJson(response.text(), JsonArray.class);

        List<String> listaDeNombres = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            String nombre = arregloJson.get(i).getAsJsonObject().get("title").getAsString();
            listaDeNombres.add(nombre);
        }
        
        return listaDeNombres;
    }

    // metodo post
    public static void enviarPostAbierto(APIRequestContext request, String nombreModificado) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("name", nombreModificado);


    APIResponse response = request.post("/todos", RequestOptions.create().setData(cuerpo));

    Assertions.assertEquals(201, response.status(), "El servidor respondió con éxito");

    }

}


// aplicar la libreria de Rest Assured given(), when(), then() 
// utilizar la clase record para transportar los datos obtenidos
// imprimir los nombres obtenidos en consola y hacerlo mas visual
