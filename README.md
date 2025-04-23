# Documentacion
API REST que permite gestionar un catálogo de productos con operaciones CRUD (Crear, Leer, Actualizar, Eliminar). Construida con Spring Boot 2.7.18 y Java 11.

# Estructura del proyecto
* Modelo: Entidad de datos
* Repositorio: Acceso a datos
* Servicio: Logica de negocio
* Controlador: Endpoints REST

# Configuración de la base de datos
La aplicación utiliza H2, una base de datos en memoria:

* URL: jdbc:h2:mem:productosdb
* Usuario: user
* Contraseña: password1234
* Consola H2: http://localhost:8080/h2-console

# Endpoints de la API
## 1. Crear un producto
* POST http://localhost:8080/api/productos
* Content-Type: application/json
* Body de ejemplo: 
* {
  * "nombre": "Silla Gamer",
  * "descripcion": "Silla gamer ergonomica de alta calidad",
  * "precio": 1899.99,
  * "cantidad": 5
* }

## 2. Obtener un producto por ID
* GET http://localhost:8080/api/productos/{id}

## 3. Obtener un producto por nombre
* GET http://localhost:8080/api/productos/nombre/{nombre}

## 4. Actualizar un producto
* PUT http://localhost:8080/api/productos/{id}
* Content-Type: application/json
* Body de ejemplo
* {
  * "nombre": "Silla Gamer Pro",
  * "descripcion": "Silla gamer ergonomica de alta calidad importada",
  * "precio": 2100,
  * "cantidad": 5
* }

## 5. Eliminar un producto
* DELETE http://localhost:8080/api/productos/{id}

## 6. Obtener todos los productos ordenados por precio ascendente
* GET http://localhost:8080/api/productos/ordenados-por-precio

# Consideraciones Técnicas
* Validaciones: La aplicacion valida que no existan productos con el mismo nombre.
* Manejo de Errores: Implementa un controlador global de excepciones que devuelve respuestas JSON con información relevante.








