# TaskManagement API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![Oracle](https://img.shields.io/badge/Oracle-Database-red?style=for-the-badge&logo=oracle)
![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apache-maven)

**TaskManagement** es un sistema robusto de gestión de tareas diseñado para facilitar la organización y el seguimiento de actividades mediante una 
arquitectura RESTful eficiente.

---

## Propósito del Proyecto
Este sistema permite centralizar la creación, consulta, actualización y eliminación de tareas, asegurando la integridad de los datos y 
ofreciendo una interfaz limpia para ser consumida por cualquier cliente.

## Stack Tecnológico
* **Lenguaje:** Java 17
* **Herramientas de Framework:** Spring Boot 4.0.6
* **Gestor de Dependencias:** Maven
* **Base de Datos:** Oracle Database
* **Documentación:** Swagger / OpenAPI 3

---

## Configuración de la Base de Datos

A diferencia de la configuración tradicional por archivos `.properties`, este proyecto utiliza una clase Java de configuración dinámica.

Para conectar tu instancia de Oracle, dirígete a:
`src\main\java\com\prueba\TaskManagement\Configuration\DataSourceConfig.java`

Localiza la función `dataSource()` y actualiza las siguientes credenciales:

```java
@Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1521/orcl"); // Modificar aqui
        dataSource.setUsername("MFPastranaTasksManager"); // Modificar aqui
        dataSource.setPassword("password1"); // Modificar aqui
        
        return dataSource;
    }
```
---

El RestController principal está mapeado en /api/tasks.
A continuación los detalles:
| Método | Endpoint | Descripción |
|--------------|--------------|--------------|
| GET | /api/tasks | Lista todas las tareas registradas. |
| GET | /api/tasks/{id} | Obtiene el detalle de una tarea específica por su ID. |
| POST | /api/tasks | Agrega una nueva tarea. |
| PUT | /api/tasks/{id} | Actualiza los datos de una tarea existente. |
| DELETE | /api/tasks/{id} | Elimina de forma física una tarea por ID. |

---


Documentacion
Puedes acceder a la interfaz interactiva de Swagger UI en:
`http://localhost:8080/swagger-ui/index.html` una vez que el proyecto esté en ejecución.


---

Para empezar
Como clonar el repositorio:

`git clone [https://github.com//Miguel01-2003/TaskManagement.git](https://github.com//Miguel01-2003/TaskManagement.git)`

Configurar Oracle: Asegúrate de tener una instancia de Oracle activa y haber configurado el DataSourceConfig como se indicó arriba.

Compilar y ejecutar

`mvn clean install
mvn spring-boot:run`

---
## Arquitectura del Proyecto
El sistema está diseñado bajo una Arquitectura en Capas, lo que facilita la separación de responsabilidades, mejora la mantenibilidad y permite un escalado organizado del código.

Capas del Sistema:
- RestController: Punto de entrada de la aplicación. Se encarga de gestionar las peticiones HTTP, validar los datos de entrada y retornar las respuestas adecuadas.

- Service (Lógica de Negocio): Actúa como intermediario entre la capa de presentación y la de datos. Aquí se procesan las reglas de negocio y se coordinan las operaciones.

- DAO / Repository: Capa encargada de la abstracción del acceso a datos, desacoplando la lógica de negocio del motor de base de datos.

- JPA (Persistencia): Representa nuestras entidades de base de datos y gestiona el ciclo de vida de los objetos en Oracle.

- ML / DTO (Modelos de Validación): Se implementó una doble capa de modelos para separar los objetos de persistencia (JPA) de los objetos de transferencia de datos. Esto permite aplicar validaciones estrictas en la entrada sin afectar la estructura de la base de datos.

- Configuration: Contiene las definiciones de Beans de Spring, seguridad y la configuración personalizada del DataSource.
