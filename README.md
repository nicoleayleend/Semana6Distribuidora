# Proyecto: Aplicación de Distribuidora de Alimentos

## Descripción del Proyecto
Este proyecto consiste en el desarrollo de una aplicación para una empresa de distribución de alimentos que ha incluido un servicio de despacho a domicilio. La aplicación permitirá a los usuarios calcular automáticamente los costos de despacho basados en reglas de negocio específicas y gestionar la temperatura de los productos que requieren cadena de frío.

## Requerimientos del Proyecto

### Requerimientos Funcionales
1. **Registro de Usuarios**
   - Los usuarios deben poder registrarse en la aplicación utilizando una cuenta de Gmail.

2. **Login de Usuarios**
   - Se creará una pantalla de login que permitirá a los usuarios autenticarse utilizando correo electrónico y contraseña a través de Firebase Authentication.

3. **Ingreso de Datos del Producto**
   - La aplicación debe solicitar al usuario la siguiente información sobre los productos:
     - Nombre del producto
     - Cantidad
     - Precio por unidad

4. **Cálculo Automático del Costo de Despacho**
   - La aplicación debe calcular automáticamente el costo de despacho basado en las siguientes reglas:
     - **Compras mayores a $50,000**: El despacho es gratuito dentro de un radio de 20 km.
     - **Compras entre $25,000 y $49,999**: Se cobra $150 por kilómetro recorrido.
     - **Compras menores a $25,000**: Se cobra $300 por kilómetro recorrido.

5. **Gestión de Temperatura de Productos**
   - La aplicación debe permitir al administrador del local monitorear la temperatura del congelador del camión de despacho. Si la temperatura supera el límite establecido, debe emitirse una alarma en su dispositivo móvil.

6. **Visualización de la Información**
   - Después de realizar la compra, la aplicación debe mostrar la información ingresada por el usuario en la consola.

7. **Almacenamiento de Datos en Firebase**
   - Se almacenará la posición GPS del dispositivo en Firebase RealTime Database si el usuario coincide con los datos de login.

### Requerimientos No Funcionales
1. **Seguridad**
   - El sistema debe asegurar que los datos de los usuarios (especialmente las credenciales de Gmail) se manejen de manera segura.

2. **Performance**
   - La aplicación debe responder en tiempo real a las entradas del usuario y realizar cálculos sin demoras perceptibles.

3. **Usabilidad**
   - La interfaz debe ser intuitiva y fácil de usar, permitiendo a los usuarios interactuar sin necesidad de formación previa.

4. **Compatibilidad**
   - La aplicación debe ser compatible con Android Lollipop y versiones superiores, teniendo en cuenta que la mayoría de los clientes utilizan Android Oreo.

5. **Mantenibilidad**
   - El código debe estar bien documentado y estructurado para facilitar futuras actualizaciones y mantenimiento.

6. **Disponibilidad**
   - La aplicación debe estar disponible en un repositorio de GitHub para su descarga y revisión.

7. **Portabilidad**
   - El sistema debe poder ser transferido y ejecutado en diferentes entornos sin requerir modificaciones significativas.

## Estructura del Repositorio
- `MainActivity.kt`: Código fuente para la pantalla de login y gestión de usuarios.
- `MenuActivity.kt`: Código fuente para la pantalla de menú y cálculo de despacho.
- `README.md`: Documentación completa del proyecto.
- `LICENSE`: Licencia del proyecto.

## Instrucciones de Uso
1. **Compilación**: Compila el código fuente desde Android Studio o desde la línea de comandos utilizando Gradle.
2. **Ejecución**: Ejecuta la aplicación en un dispositivo físico o virtual.
3. **Contribución**: Si deseas contribuir al proyecto, por favor, sigue las directrices de contribución en el archivo `CONTRIBUTING.md`.

## Autor
- Nicole Diaz

## Licencia
Este proyecto está licenciado bajo los términos de la [Licencia MIT](LICENSE).
