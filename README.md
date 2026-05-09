Desarrollo-de-Microservicios
Este repositorio contiene el desarrollo de una arquitectura de microservicios diseñada para demostrar la integración entre servicios generados mediante herramientas de automatización (Skills) y servicios desarrollados de forma manual. Toda la arquitectura está centralizada y gestionada a través de un servidor de descubrimiento de servicios.
🧩 Tarea: Desarrollo de Microservicios con y sin Skills
Estudiante: Jhon  
Materia: Arquitectura de Microservicios  
Tecnología: Spring Boot 3.5 · Spring Cloud · Java 17 · Gradle
---
📁 Estructura del Repositorio
```
/
├── eurekaserver/          ← Servidor de descubrimiento
├── productos/             ← Microservicio 1 (generado con Skills/IA)
├── proveedores/           ← Microservicio 2 (desarrollado manualmente)
├── SKILL_PROMPT.md        ← Prompt utilizado con el skill
└── README.md              ← Este archivo
```
---
🏛️ Arquitectura General
```
┌──────────────────────────────────────────────────┐
│                  EUREKA SERVER                    │
│              http://localhost:8761                │
│        Servidor de descubrimiento de servicios    │
└──────────────────┬───────────────┬───────────────┘
                   │               │
             registra         registra
                   │               │
                   ▼               ▼
         ┌──────────────┐  ┌──────────────────┐
         │  PRODUCTOS   │  │   PROVEEDORES    │
         │   :8083      │  │     :8084        │
         │ (con Skill)  │  │    (manual)      │
         └──────────────┘  └──────────────────┘
```
---
🔵 Eureka Server
Servidor de descubrimiento que permite registrar y localizar microservicios automáticamente sin necesidad de IPs fijas.
Propiedad	Valor
Puerto	`8761`
Dashboard	http://localhost:8761
Anotación clave	`@EnableEurekaServer`
Arrancar
```bash
cd eurekaserver
.\gradlew.bat bootRun
```
---
🟢 Microservicio 1 — PRODUCTOS (Generado con Skills/IA)
¿Qué es un Skill?
Para este proyecto, se define un skill como una herramienta de inteligencia artificial capaz de generar código fuente de manera automática mediante instrucciones en lenguaje natural. En el desarrollo del primer microservicio, se utilizó Claude AI (Antigravity) como el skill encargado de producir toda la arquitectura base, incluyendo la creación de entidades, DTOs, repositorios, servicios y el controlador con todos sus respectivos endpoints.
Ventajas del uso de Skills:
Generación rápida de código boilerplate
Implementación automática de buenas prácticas
Estructura en capas generada correctamente desde el inicio
Reducción de errores en la configuración inicial
Arquitectura generada automáticamente
```
ProductoController  ←── recibe peticiones HTTP
       │
       │  ProductoDTO (objeto de transferencia)
       ▼
ProductoService     ←── lógica de negocio, convierte DTO ↔ Entity
       │
       │  Producto (entidad JPA)
       ▼
ProductoRepository  ←── acceso a base de datos H2
       │
       ▼
  Base de datos H2 (en memoria)
```
¿Qué es un DTO?
Un DTO (Data Transfer Object) es un objeto que controla qué datos se exponen en la API, separando la entidad de base de datos de la respuesta HTTP. Permite ocultar campos sensibles y dar forma diferente a los datos según el consumidor.
Endpoints disponibles
Método	URL	Descripción
GET	`/productos`	Listar todos los productos
GET	`/productos/paginado?page=0&size=5&sortBy=nombre&dir=asc`	Listar con paginación
GET	`/productos/buscar?nombre=X`	Buscar por nombre parcial
GET	`/productos/{id}`	Obtener producto por ID
GET	`/productos/nombre/{nombre}`	Buscar por nombre exacto
POST	`/productos`	Crear nuevo producto
PUT	`/productos/{id}`	Actualizar producto completo
PATCH	`/productos/{id}`	Actualizar campos específicos
DELETE	`/productos/{id}`	Eliminar producto
Diferencia PUT vs PATCH
	PUT	PATCH
Tipo	Actualización completa	Actualización parcial
Campos null	Sobrescribe con null	Ignora campos null
Uso	Reemplazar todo el recurso	Modificar uno o pocos campos
Ejemplo POST
```json
{
  "nombre": "Laptop Lenovo",
  "descripcion": "Laptop 15 pulgadas 16GB RAM",
  "categoria": "Electronica",
  "precio": 899.99,
  "stock": 25,
  "marca": "Lenovo",
  "activo": true,
  "fechaIngreso": "2025-01-10"
}
```
Ejemplo PATCH
```json
{
  "precio": 799.99,
  "stock": 15
}
```
Arrancar
```bash
cd productos
.\gradlew.bat bootRun
```
---
🟡 Microservicio 2 — PROVEEDORES (Desarrollado Manualmente)
Este microservicio fue desarrollado desde cero, sin herramientas generadoras, escribiendo cada archivo manualmente. Implementa una arquitectura completa por capas incluyendo manejo de excepciones y validaciones.
Estructura de carpetas
```
proveedor/
├── controller/
├── service/
│   └── impl/
├── repository/
├── entity/
├── dto/
├── exception/
└── validation/
```
Endpoints disponibles
Método	URL	Descripción
GET	`/proveedores`	Listar todos los proveedores
GET	`/proveedores/paginado?page=0&size=5&sortBy=nombre&dir=asc`	Listar con paginación
GET	`/proveedores/{id}`	Obtener proveedor por ID
GET	`/proveedores/nombre/{nombre}`	Buscar por nombre
GET	`/proveedores/email/{email}`	Buscar por email
POST	`/proveedores`	Crear nuevo proveedor
PUT	`/proveedores/{id}`	Actualizar completo
PATCH	`/proveedores/{id}`	Actualizar parcial
DELETE	`/proveedores/{id}`	Eliminar
Ejemplo POST
```json
{
  "nombre": "TechSupplies SA",
  "email": "tech@supplies.com",
  "telefono": "0991234567",
  "direccion": "Av. Principal 123",
  "pais": "Ecuador",
  "activo": true,
  "fechaRegistro": "2025-01-15"
}
```
Arrancar
```bash
cd proveedores
.\gradlew.bat bootRun
```
---
🔗 Integración con Eureka
Ambos microservicios usan `@EnableDiscoveryClient` y apuntan al Eureka Server:
```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
```
Al arrancar, cada servicio se registra automáticamente y aparece como UP en el dashboard de Eureka.
---
▶️ Orden de arranque
> ⚠️ Siempre iniciar Eureka Server primero
```bash
# Terminal 1 — Siempre primero
cd eurekaserver && .\gradlew.bat bootRun

# Terminal 2
cd productos && .\gradlew.bat bootRun

# Terminal 3
cd proveedores && .\gradlew.bat bootRun
```
Verificar en http://localhost:8761 que PRODUCTOS y PROVEEDORES aparezcan como UP.
---
🛠️ Tecnologías
Tecnología	Versión	Propósito
Java	17	Lenguaje principal
Spring Boot	3.5.14	Framework base
Spring Web	—	API REST
Spring Data JPA	—	Acceso a datos + paginación
H2 Database	—	Base de datos en memoria
Spring Cloud Eureka	2024.0.1	Descubrimiento de servicios
Lombok	—	Reducción de código repetitivo
Spring Boot Actuator	—	Health checks
Gradle	—	Gestión de dependencias
Thunder Client	—	Prueba de endpoints
