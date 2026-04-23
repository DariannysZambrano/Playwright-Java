1. **PRIMERA HISTORIA DE USUARIO**
Historia de Usuario (US-101): Validar mensaje de "Sin Resultados" con datos dinámicos

Como Ingeniero de Automatización QA,
Quiero consumir un texto aleatorio desde un servicio API externo y utilizarlo como parámetro de búsqueda en la tienda, Para verificar que la interfaz de usuario (UI) maneje correctamente las búsquedas fallidas y aplique los patrones POM y Service Object Model en el código.

Criterios de Aceptación (Acceptance Criteria):

- El framework debe conectarse a https://jsonplaceholder.typicode.com/posts/1 utilizando una clase de tipo Service Object (Encapsulamiento).

- El framework debe extraer el atributo title de la respuesta JSON (Abstracción).

- El framework debe abrir el navegador e ir a https://practicesoftwaretesting.com/.

- El framework debe utilizar una clase de tipo Page Object para localizar la barra de búsqueda y el botón (Encapsulamiento).

- El framework debe escribir el título obtenido de la API en la barra de búsqueda y hacer clic en buscar.

- El test debe fallar si la página NO muestra un mensaje o indicador visual de que no hubo resultados (Aserción en la clase Test).