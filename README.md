# Proyecto: Soda "La Máquina"
### Curso: SC-403 Desarrollo de Aplicaciones Web y Patrones - Universidad Fidélitas

Bienvenido al repositorio oficial del proyecto **La Máquina**, una aplicación web diseñada para la gestión automatizada de pedidos, menú digital y control de ventas de la soda.


## Integrantes del Grupo
* **Luis Carlos Campos Vega** - [luisCarlos1711]
* **Kasandra Miranda Romero** - [kasandra22]
* **José Daniel Carballo Murillo** - [DanielbigM]
* **Edmilson Yasser Flores Salazar** - [EdmilsonFlores]
# Sistema de Gestión - Soda La Máquina

## Estado del Proyecto

* **Avance general estimado:** 50%
* **Módulo Inventario:** 80%
* **Módulo Administración:** En desarrollo
* **Módulo Ventas:** En desarrollo

## Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework Principal:** Spring Boot 3.3.5
* **Persistencia:** Spring Data JPA
* **Motor de Base de Datos:** MySQL
* **Plantillas / Frontend:** Thymeleaf y Bootstrap 5
* **Gestor de Dependencias:** Maven

## Configuración e Instalación

1. Base de Datos
Crea la base de datos en tu servidor MySQL local ejecutando la siguiente sentencia:

CREATE DATABASE soda_la_maquina;

2. Configurar el Entorno
Modifica el archivo src/main/resources/application.properties con tus credenciales locales de MySQL:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/soda_la_maquina
spring.datasource.username=tu_usuario
spring.datasource.password=12345

3. Ejecución del Proyecto
Abre una terminal en la raíz del proyecto y ejecuta los siguientes comandos:

Bash
mvn clean install
mvn spring-boot:run
Una vez iniciado, el sistema estará disponible en de forma local en: http://localhost:8080

Características del Sistema
Avance Actual

Inventario
-CRUD de Categorías de Insumos.
-CRUD de Insumos.
-Relación mapeada entre Categorías e Insumos.
-Persistencia de datos completa en MySQL.
-Interfaz gráfica adaptada con Bootstrap.

Administración
-Dashboard principal estructurado.
-Estructura base para la gestión de Usuarios.
-Estructura base para la asignación de Roles.
-Estructura base para el manejo de Proveedores.

Características Pendientes (Próximos Pasos)
-Registro de Movimientos de Inventario.
-Control y registro de Mermas.
-Alertas de Stock mínimo.
-Implementación de Seguridad con Spring Security.
-Módulo de Punto de Venta (POS) y Gestión de Ventas.
-Generación de Reportes y Estadísticas.
