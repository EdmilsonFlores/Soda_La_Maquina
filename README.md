# Proyecto: Soda "La Máquina"
### Curso: SC-403 Desarrollo de Aplicaciones Web y Patrones - Universidad Fidélitas

Bienvenido al repositorio oficial del proyecto **La Máquina**, una aplicación web diseñada para la gestión automatizada de pedidos, menú digital y control de ventas de la soda.


## Integrantes del Grupo
* **Luis Carlos Campos Vega** - [luisCarlos1711]
* **Kasandra Miranda Romero** - [kasandra22]
* **José Daniel Carballo Murillo** - [DanielbigM]
* **Edmilson Yasser Flores Salazar** - [EdmilsonFlores]

Tecnologías
Java 21
Spring Boot 3.3.5
Spring Data JPA
Thymeleaf
Bootstrap 5
MySQL
Maven
Requisitos
JDK 21
MySQL
NetBeans 29 (o IDE compatible)
Maven

-Crear la base de datos:
CREATE DATABASE soda_la_maquina;
Configurar application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/soda_la_maquina
spring.datasource.username=username
spring.datasource.password=12345
-Ejecución
mvn clean install
mvn spring-boot:run
La aplicación estará disponible en:
http://localhost:8080
Avance Actual
Inventario
-CRUD de Categorías de Insumos
-CRUD de Insumos
-Relación Categoría <> Insumo
-Persistencia en MySQL
-Interfaz Bootstrap
Administración
-Dashboard principal
-Estructura para Usuarios
-Estructura para Roles
Estructura para Proveedores
Pendiente
Movimiento de Inventario
Mermas
Alertas de Stock
Spring Security
POS y Ventas
Reportes
Estado del Proyecto
Avance general estimado: 50%
Módulo Inventario: 80%
Módulo Administración: En desarrollo
Módulo Ventas: En desarrollo
