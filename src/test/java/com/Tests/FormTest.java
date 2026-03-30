package com.Tests;

import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;
import com.config.SetUpPlaywright;
import java.nio.file.Paths;

    // Historia de Usuario 1: Envío Exitoso del Formulario de Contacto
    // Como un cliente del sitio
    // Quiero enviar un mensaje a través del formulario de contacto
    // Para solicitar soporte o información sobre un producto.
    // Criterios de Aceptación:
    // El usuario debe completar: Nombre, Apellido, Email, Asunto (Subject) y
    // Mensaje.
    // Al hacer clic en "Send", el sistema debe mostrar un mensaje de éxito.
    // No deben aparecer errores de validación si los datos son correctos.

@UsePlaywright(SetUpPlaywright.class)

public class FormTest {

    @Test
    void HappyPathForm(Page page) {

        page.navigate("/contact");

        page.getByLabel("Nombre").fill("John");
        page.getByLabel("Apellido").fill("Doe");
        page.getByLabel("Dirección de correo electrónico").fill("juan.perez@gmail.com");
        page.getByLabel("Asunto").selectOption("payments");
        // otra opcion para trabajar con el dropdown 
        //Locator dropdown = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Subject"));
        //dropdown.selectOption("customer-service");
        page.getByLabel("Mensaje *").fill("Hola, tengo una consulta sobre mi pedido. ¿Podrían ayudarme? Gracias. Saludos. John.");
        page.getByLabel("Adjunto").setInputFiles(Paths.get("src/test/java/com/config/resources/mensaje.txt"));

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Enviar")).click();
        
    }
}