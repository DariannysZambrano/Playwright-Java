package com.Tests.ApiTest.PracticandoPoo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

public class prepararAPI {

    APIRequestContext requestContext;
    APIResponse respuesta;

    public prepararAPI(APIRequestContext requestContext) {
        this.requestContext = requestContext;
        this.respuesta = requestContext.get("posts/1");
    }

    public String obtenerTituloDelPost() {

        if (!respuesta.ok()) {
            throw new RuntimeException("Error al obtener el registro: " + respuesta.status());
        }

        JsonObject objetoJson = JsonParser.parseString(respuesta.text()).getAsJsonObject();
        String titulo = objetoJson.get("title").getAsString();
        return titulo;
    }
}
