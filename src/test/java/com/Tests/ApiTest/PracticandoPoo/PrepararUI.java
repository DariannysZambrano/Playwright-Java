package com.Tests.ApiTest.PracticandoPoo;

import com.microsoft.playwright.Page;

public class PrepararUI {
    
    Page page;

    private String barraBusqueda = "[data-test=\"search-query\"]";
    private String botonBuscar = "[data-test=\"search-submit\"]";

    public PrepararUI(Page page) {
        this.page = page;
        this.page.navigate("https://practicesoftwaretesting.com/");
    }


    public void realizarBusqueda(String buscarProducto) {
        page.fill(barraBusqueda, buscarProducto);
        page.click(botonBuscar);
    }
}
