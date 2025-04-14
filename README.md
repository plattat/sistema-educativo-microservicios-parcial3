**Introducción**

Este proyecto implementa una arquitectura de microservicios utilizando Spring Boot 3.3.10, Eureka Server para descubrimiento de servicios, Spring Cloud Gateway como puerta de enlace, y autenticación JWT en el usuarios-servicio. La comunicación entre servicios se realiza con Feign Client, y se simula una matrícula entre estudiantes y asignaturas.

**Tecnologías Utilizadas**

* Java 17

* Spring Boot 3.3.10

* Spring Cloud (2023.0.5)

* Spring Security

* JWT (JJWT 0.11.5)

* Spring Cloud Gateway

* Eureka Discovery Server

* Feign Client

* Maven

**El proyecto está dividido en los siguientes módulos/microservicios:**

1. **eureka-server**: Servicio de descubrimiento

2. **api-gateway**: Gateway que enruta el tráfico a los microservicios registrados

3. **usuarios-servicio**: Servicio de autenticación y gestión de usuarios

4. **asignaturas-servicio**: Servicio CRUD para asignaturas

5. **matriculas-servicio**: Servicio que consume los otros microservicios y simula una matrícula completa

Cada microservicio cuenta con su propio **application.properties**, archivo **pom.xml**, estructura de paquetes y configuraciones independientes.

**Detalles por Microservicio**

**1\. eureka-server**

* Puerto: 8761

* Rol: Registro centralizado de microservicios

**Main Config:**

<img width="400" alt="{0C638B6F-3B11-4695-91D9-E0C1C57C34B6}" src="https://github.com/user-attachments/assets/da15eaaf-981d-4d12-8866-444b7283fc84" />

**Dependencias Maven:**
<img width="366" alt="{9AADB8C8-2336-4729-8BA6-DE419784CE40}" src="https://github.com/user-attachments/assets/fc7abcf2-5f2b-4408-94d0-319fd750df76" />

Funcionamiento: 

![image](https://github.com/user-attachments/assets/42a8554e-a292-4c31-ac14-300303e9fbef)

**Comunicación de los microservicion con el server** 

![image](https://github.com/user-attachments/assets/6d6d51bf-9dd2-4114-989a-fd48ee6f4662)

*2\. api-gateway**

* Puerto: 8080

* Rol: Enrutador principal de peticiones HTTP

* Rutas definidas por servicio (/api/usuarios/\*\*, /api/asignaturas/\*\*, /api/matriculas/\*\*)

* **Importante:** Usa spring-cloud-starter-gateway-mvc

**Configuracion del** **Aplication.properties:**

![image](https://github.com/user-attachments/assets/a5c81d5b-fc56-486e-b6b1-275668f91b6a)

**Pruebas de funcionamiento:**


![image](https://github.com/user-attachments/assets/b6a6bb14-62a7-42a3-a4f0-5a9fda939c16)

**3\.  usuarios-servicio**

* Puerto: 8082

* Rol:

  * Autenticación de usuarios (JWT)

  * Expone /auth/login para generar token JWT

  * Expuesto en Eureka y enrutado desde Gateway

**Estructura de Paquetes:**

* controller ➝ AuthController, UsuarioController

* model ➝ Usuario, AuthRequest, AuthResponse

* service ➝ JwtUtil, CustomUserDetailsService

* config ➝ SecurityConfig

* filter ➝ JwtRequestFilter

**Dependencias JWT:**

![image](https://github.com/user-attachments/assets/644ea630-0c8e-4709-8c12-572e0d7bb97a)


Pruebas en postman del microservicio 

![image](https://github.com/user-attachments/assets/b3c5a1ea-c85e-4dcf-ab83-5a6b567af6ab)

Ejecución del servicio

![image](https://github.com/user-attachments/assets/e2da5531-d06c-47bc-87d3-03357321bdd3)

![image](https://github.com/user-attachments/assets/593cb651-c58f-42ec-9786-6d9175c73e2e)


**4\. asignaturas-servicio**

* **Puerto:** 8081

* **Rol:** CRUD para asignaturas

* **Ejemplo:**
<img width="358" alt="{E1B065B3-85C8-487B-AF1F-3A8C2C0E9BE4}" src="https://github.com/user-attachments/assets/3b873922-00f9-4487-8cf3-e94ff1c3c9dc" />

**Ejecucion del servicio**

![image](https://github.com/user-attachments/assets/36c17ee6-f60a-4735-b933-1ecd8797740f)

![image](https://github.com/user-attachments/assets/64bf1854-bcce-4000-b81c-2b6501025fa3)


**5.matriculas-servicio**

* Puerto: 8083

* Rol: Simula matrículas llamando a usuarios-servicio y asignaturas-servicio mediante Feign Client

**Paquetes:**

* controller ➝ MatriculaController

* dto ➝ MatriculaResponse, MatriculaCompleta

* feign ➝ UsuarioClient, AsignaturaClient

* service ➝ MatriculaService, FeignClientInterceptor

* model ➝ Usuario, Asignatura, Matricula

**Feign Clients:**

![image](https://github.com/user-attachments/assets/d83cb024-0817-4aad-88a7-e5bb304b94fa)

**Interceptor para JWT:**

<img width="357" alt="{BBA8CCD7-81C5-4434-97FB-52AD75E1C55C}" src="https://github.com/user-attachments/assets/c3cab69f-06ab-4762-be93-0b8bcbbc2ced" />

**Ejecution del servicio** 

![image](https://github.com/user-attachments/assets/19a508dd-b252-4d78-a8b6-e55735af26ac)

![image](https://github.com/user-attachments/assets/b79f2b67-bb8b-4ad6-ae8c-07544fd704c9)


**Ejemplo de Flujo**

1. Se hace POST a /auth/login con admin/admin y se obtiene un JWT.

2. Se puede consultar /api/matriculas/completas pasando el JWT como Bearer en el header.

3. Este microservicio llama con Feign a los otros servicios inyectando el mismo token.

Ejecución de una matrícula complete (se enviaron datos estáticos)


![image](https://github.com/user-attachments/assets/717f5b22-6907-4950-88c9-887dd4153b88)

# **Pruebas Unitarias e Integración** 

#    **Microservicio: Usuarios-servicios**

# Prueba unitaria para JwtUtil Prueba la generación y validación de tokens JWT

![image](https://github.com/user-attachments/assets/d1225be1-9445-471f-aac1-9e40b30378c0)


Prueba unitaria para UsuarioController

Prueba la consulta de usuario

![image](https://github.com/user-attachments/assets/77e74d96-57c2-4eb7-86e8-9a1e49f0dbd2)

#  Pruebas de Integración con Spring Bot Test  Estas pruebas validan el comportamiento de los endpoints REST usando MockMvc.

![image](https://github.com/user-attachments/assets/3a690879-a648-49d3-bea7-883a10355858)

**Resultado:**

Se realizaron seis pruebas automatizadas con el microservicio usuarios-servicio. Todas las pruebas pasaron exitosamente sin errores ni fallos. Esto valida que los componentes clave del microservicio están funcionando correctamente de forma aislada y conjunta.

![image](https://github.com/user-attachments/assets/2185e1a4-f7a7-4e9b-8675-6cf316b3748d)

# Pruebas Unitarias e Integración 

##    Microservicio: Matriculas-servicio

**Prueba unitaria:** 

![image](https://github.com/user-attachments/assets/7a5f54d5-517a-4224-977b-0485f39febdc)

**Prueba unitaria:**

![image](https://github.com/user-attachments/assets/74a92c64-5c2f-49ce-aacc-1e2a22924180)


**Resultado:**

Se ejecuto dos pruebas, para authTokenManagertest y para MatriculaControllerTest,, con resultados exitosos.

![image](https://github.com/user-attachments/assets/c15875c8-e70a-414d-87b6-2984205266cb)

# Pruebas Unitarias e Integración 

##    Microservicio: Asignatura-servicio

**Pruebas unitarias**

![image](https://github.com/user-attachments/assets/b3040786-6043-41af-88c3-828d16aaadab)


**Prueba de integración:**

![image](https://github.com/user-attachments/assets/548cec52-0929-47cb-a854-f9c34b167b08)

**Resultado:** se ejecuto una prueba unitara y una de integración con resultados exitosos

![image](https://github.com/user-attachments/assets/0163d15e-e474-4e8e-b319-2120f6df660e)

# Construir Docker.

## Construir Imagen:

Sobre cada microservicio se ejecuta el siguiente mensaje para crear la imagen del microservicio

docker build \-t spring-api-matriculas-servicios .

![image](https://github.com/user-attachments/assets/b6f43f24-2deb-4f70-943f-fb1b3d18da31)

**Se realiza lo mismo para los demás Microservicios y se confirma las imágenes por consola o con el  Docker desktop**

![image](https://github.com/user-attachments/assets/597eeb1b-fb30-4660-b90c-ccb274bf320c)

![image](https://github.com/user-attachments/assets/9eb68971-ed3a-4b37-877c-f453541ef25c)

#crear contenedor:
Sobre cada microservicio ejecutar el siguiente comando para crear el contenedor
docker-compose up

![image](https://github.com/user-attachments/assets/7b502d0c-0d85-48d7-9df0-c6e64f07d2b7)

Se confirma la creación de las imágenes y contenedores con el siguiente comando:

**Docker ps**

![image](https://github.com/user-attachments/assets/807458e6-5720-4a9e-97ce-f455f2458e6e)

Para que los Microservicios puedan comunicarse con los demás contenedores/microservicios es necesario crear una red de Docker que tengan en común

Se crea la red
docker network create --attachable api-network   # se crea la red compartida

Se asigna la red 
docker network connect api-network "image"

se asigna la red en la configuración del docker-compose, por cada microservicio

![image](https://github.com/user-attachments/assets/69c37dd2-0ed8-40c2-a69d-72b4bcf1f9cb)


Si se realizo cambios en el código ejecutar los siguientes comandos para actualizar el contenedor y la imagen de Docker

**Reconstruye la imagen**
**docker-compose build**
**docker-compose up**

En application.properties cambiar la URL por la imagen creada en el microservicio

![image](https://github.com/user-attachments/assets/acaf6ab4-0836-493b-a188-97c8171717d9)

![image](https://github.com/user-attachments/assets/802c4751-09e1-4196-8020-6500bda1ebcf)


Se confirma el funcionamiento y comunicación de los microservicios con Docker.

![image](https://github.com/user-attachments/assets/862fe42b-7c40-499b-993f-2d3f936dfbff)


**Se valida la comunicación con Postman:**
![image](https://github.com/user-attachments/assets/c215625c-7c19-4f4a-97aa-d19a934d24a0)



**Autores:**
**Cristian David Otalvaro Almanza** **&**
**Roman Andres Urrego Peña**








