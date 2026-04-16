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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GetProductsTest {

    private static Playwright playwright;
    private static APIRequestContext requestContext;

    @BeforeAll
    public static void setupRequestContext() {
        playwright = Playwright.create();
        requestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions().setBaseURL("https://jsonplaceholder.typicode.com/")
        );
    }

    @Test
    public void validarModificacionYCreacionDeLibros(){

        String tituloUsuario = "Once minutos - Paulo Coelho";

        List<String> nombresObtenidos = obtenerNombresProductos(requestContext);
        nombresObtenidos.add(tituloUsuario);

        Assertions.assertEquals(6, nombresObtenidos.size());

        String ultimoRegistro = nombresObtenidos.getLast();
        Assertions.assertEquals(tituloUsuario, ultimoRegistro);

        for (int i = 0; i < nombresObtenidos.size(); i++) {
            String tituloAEnviar = nombresObtenidos.get(i);
            
            if (i < 5) {
                tituloAEnviar = tituloAEnviar + " - " + (i + 1);
            }

            enviarPostAbierto(requestContext, tituloAEnviar);
        }
    }
    
    @AfterAll
    public static void tearDown() {
            playwright.close();
    }

    // get

    public static List<String> obtenerNombresProductos(APIRequestContext request) {
        APIResponse response = request.get("/todos");
        JsonArray arregloJson = new Gson().fromJson(response.text(), JsonArray.class);

        List<String> listaDeNombres = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String nombre = arregloJson.get(i).getAsJsonObject().get("title").getAsString();
            listaDeNombres.add(nombre);
        }
        
        return listaDeNombres;
    }

    // metodo post
    public static void enviarPostAbierto(APIRequestContext request, String nombreModificado) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("title", nombreModificado);

    APIResponse response = request.post("/todos", RequestOptions.create().setData(cuerpo));
    Assertions.assertEquals(201, response.status(), "El servidor respondió con éxito");

    }

}
