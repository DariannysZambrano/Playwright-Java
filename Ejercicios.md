EJERCICIOS PLAYWRIGHT

1. **El "Hello World" de Testing:** Crea una prueba que navegue a la página y verifique que el título de la pestaña contiene "Practice Software Testing". Usa una aserción de JUnit.
    
2. **Exploración de Herramientas:** En la página principal, busca el menú de categorías (lado izquierdo). Haz clic en la categoría "Hand Tools" usando `getByRole` y verifica que el texto de la cabecera cambie a "Category: Hand Tools".
    
3. **Búsqueda Precisa:** Usa el campo de búsqueda (input) superior. Utiliza `getByPlaceholder` para escribir "Hammer" y presiona Enter. Verifica que aparezca al menos un producto en los resultados.
    
4. **Simulación de Login (Fallo):** Ve a la sección de "Sign in". Intenta loguearte con un correo y contraseña aleatorios. Usa `getByLabel` para encontrar los campos. Verifica que aparezca el mensaje de error "Invalid email or password" usando `getByText`.
    
5. **Validación de Tooltips:** Busca algún icono en la página que tenga un atributo `title` (como el botón de favoritos o el carrito). Usa `getByTitle` para validar que el texto del tooltip sea el correcto.

6. **Filtros Dinámicos:** Selecciona un rango de precio en el slider lateral y marca una marca (brand) específica. Verifica que los productos mostrados correspondan a esa marca.
    
7. **El Carrito de Compras:** Entra en el detalle de un producto, aumenta la cantidad a 3 unidades y añádelo al carrito. Ve al carrito y verifica que el subtotal sea correcto ($Precio \times 3$).
    
8. **Formulario de Contacto:** Completa el formulario de "Contact" usando todos los localizadores aprendidos. Valida que tras enviar, aparezca el mensaje de éxito.
    
9. **Persistencia de Sesión (Reto):** Crea un test donde te loguees con una cuenta válida, extraigas el `storageState` y verifiques que en una nueva `Page` (dentro del mismo contexto) sigues logueado sin escribir la clave.
    
10. **E2E Completo:** Realiza el flujo completo: Login -> Buscar producto -> Añadir al carrito -> Checkout -> Pago -> Confirmación de orden.