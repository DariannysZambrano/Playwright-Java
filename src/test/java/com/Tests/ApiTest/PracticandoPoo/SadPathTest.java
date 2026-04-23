package com.Tests.ApiTest.PracticandoPoo;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Locator;

import org.junit.jupiter.api.Test;

public class SadPathTest extends BaseTest {


    @Test
    public void obtenerRegistroErroneoDelservidor() {

        prepararAPI api = new prepararAPI(requestContext);
        String titulo = api.obtenerTituloDelPost();

        PrepararUI paginaPrincipal = new PrepararUI(page);

        paginaPrincipal.realizarBusqueda(titulo);
        
page.locator("[data-test='no-results']").waitFor(new Locator.WaitForOptions().setTimeout(20000));

    assertThat(page.locator("[data-test='no-results']")).isVisible();
    }
}
    

