# Encargo DevOps Seccion 002D (Primera evaluación)

Este repositorio documenta y aplica las convenciones de desarrollo colaborativo para el microservicio creado con **Java con Spring Boot**, siguiendo los requisitos del encargo

## 1. Estrategia de Ramificación: GitFlow

Hemos adoptado **GitFlow** como modelo de trabajo. 

### Justificación
Se seleccionó GitFlow frente a *Trunk-based development* debido a que:
* **Estructura** Es un modelo de trabajo más segmentado y ordenado, más similar a un entorno de trabajo profesional, muy ligada a equipos grandes de desarrollo
* **Rigidez** siempre se trabajo siguiendo instrucciones claras y concretas, no da paso a que un desarrollor toque codigo que funciona y pueda afectar al producto negativamente

---
## 2 Arquitectura del proyecto

### Se ha usado una estructura CSR (Controller-Service-Repository)

src/main/java/com/tuproyecto/microservice/
- controller/       # Capa de entrada (API REST)
    - UsuarioController.java
    - TareaController.java
- service/          # Lógica de negocio
    - TareaService.java
- repository/       # Acceso a datos. Implementa repositorio para operaciones CRUD.
    - TareaRepository.java
- model/            # Entidades de datos
    - Usuario.java
    - Tarea.java
---

## 3. Convenciones de Naming 

Para mantener el repositorio organizado, las ramas deben seguir este formato:

| Tipo de Rama | Prefijo | Ejemplo |
| :--- | :--- | :--- |
| **Producción** | `main` | `main` |
| **Integración** | `develop` | `develop` |
| **Funcionalidades** | `feature/` | `feature/api-endpoints` |
| **Correcciones** | `hotfix/` | `hotfix/connection-timeout` |

---

## 4. Estándar de Commits 

Utilizamos mensajes semánticos para que el historial sea legible y fácil de auditar. Cada commit debe empezar con un prefijo seguido de dos puntos:

* **`feat:`** Una nueva característica para el usuario.
* **`fix:`** Corrección de un error o bug.
* **`docs:`** Cambios solo en la documentación (como este README).

*Ejemplo: `feat: implement login controller logic`*

---

## 5. Flujo de Revisión y Merge

Para garantizar la calidad del código, no se permiten "pushes" directos a `main` ni a `develop`. El flujo obligatorio es:

1.  **Pull Requests (PR):** Todo cambio desde una `feature/` o `hotfix/` debe solicitarse mediante un PR.
2.  **Revisión por Pares:** Al menos un miembro del equipo debe revisar el código, comentar posibles mejoras y dar su aprobación (**Approve**).
3.  **Merge:** Una vez aprobado y pasadas las pruebas automáticas, se procede al merge.

- Agregar que se han realizado 4 Merge con su respectivo pull request, con las 2 features al develop, el hotfix al main y un merge final al main

---
## 6 Integración Continua (GitHub Actions)

Hemos configurado un workflows automatizado ubicado en `.github/workflows/ci.yml`.

**¿Qué hace este Action?**
- Se acciona cada que se ejecuta un `push` y `pull_request` hacia las ramas `main` y `develop`.
- Configura un entorno con **Java 25**.
- Ejecuta `./mvnw clean compile` para verificar que el código compile correctamente antes de permitir el merge.

**¿Por qué?**

Porque no es raro que los codigos puedan compilar dependiendo del computador
por ende esta validación asegura que en un entorno aislado compile con seguridad los cambios realizaados 

---
# Encargo DevOps Seccion 002D (Segunda evaluación)

## 7 Contenerización Docker

Para garantizar un despliegue optimizado, la aplicación ha sido contenerizada implementando un Multi-stage Build

### Justificación 
* capa Builder: Utilizamos una imagen inicial pesada que incluye Maven y el Java Development Kit (JDK). La necesitamos porque contiene absolutamente todas las herramientas requeridas para descargar sus dependencias, compilar el código fuente en Java 25 y empaquetar el proyecto.
* capa Runtime: Trasladamos solo el artefacto final (.jar) a una imagen muy ligera que contiene únicamente el entorno de ejecución (JRE). Al basarse en Alpine Linux, dejamos atrás todo el peso del código fuente y las herramientas de desarrollo. Esto reduce drásticamente el tamaño del contenedor, mejora la seguridad al reducir la superficie de ataque y agiliza el despliegue automático en su entorno simulado.

## 8 Pipeline de Integración Continua y Seguridad (CI/CD)

Para automatizar la validación, seguridad y empaquetado del código en cada actualización, se implementó un pipeline de CI/CD utilizando GitHub Actions.

### Justificación
* **Pruebas Unitarias (`mvn clean test`):** Ejecutamos las pruebas de forma aislada (incluyendo `TareaRepositoryTest.java`) en cada nueva integración. Esto asegura la calidad del código y garantiza que los nuevos cambios no rompan las funcionalidades existentes.
* **Cobertura de Código (JaCoCo):** Integrado a través del `pom.xml` para auditar la calidad de las pruebas. Durante la fase de validación, genera automáticamente un reporte que nos permite medir el porcentaje de código que está siendo evaluado, asegurando que las partes críticas de la aplicación estén correctamente cubiertas.
* **Escaneo de Vulnerabilidades (Snyk):** Integramos un análisis de seguridad automatizado como un paso obligatorio del flujo de trabajo. Esto nos permite detectar de manera temprana y proactiva vulnerabilidades en las dependencias antes de que el código sea empaquetado.
* **Corrección de Vulnerabilidades:** Gracias a la implementación del análisis con Snyk, se identificaron y corrigieron exitosamente las vulnerabilidades y brechas de seguridad detectadas en el proyecto, garantizando un artefacto final seguro.
* **Construcción Automatizada (Docker Buildx):** Tras pasar las pruebas y la validación de seguridad, el pipeline construye automáticamente la imagen Docker de la aplicación. Esto estandariza la creación del artefacto y lo deja listo para el despliegue en el entorno simulado.

### Requisitos del Pipeline
Para que el workflow de GitHub Actions se ejecute correctamente y apruebe el análisis de seguridad, es indispensable cumplir con lo siguiente en el repositorio remoto:

* **Configuración de Snyk:** El administrador del repositorio debe agregar el token de autenticación creando un secreto llamado `SNYK_TOKEN` en la ruta **Settings > Secrets and variables > Actions** de GitHub.

## 9 Despliegue en entorno simulado (docker-compose)

Para asegurar el correcto funcionamiento de la aplicación, el despliegue automático y se realiza en un entorno cloud simulado utilizando **Docker Compose**.

### Justificación

* **Mantenibilidad (`build` e `image`):** Centralizamos la construcción. Docker Compose sabe dónde buscar el Dockerfile y qué nombre asignarle a la imagen sin tener que escribir comandos largos
* **Estabilidad (`restart: unless-stopped`):** Le estamos diciendo al orquestador que si por algún motivo la aplicación sufre un error interno y se cae, debe levantarla automáticamente de nuevo.
* **Escalabilidad y Seguridad (`networks`):** Al crear una red privada (`devops-network`), estamos dejando la arquitectura lista para que en el futuro puedan agregar un contenedor de base de datos (como MySQL) y que todos se comuniquen de forma segura.

### Instrucciones para levantar el dockercompose
Tener dockercompose instalado y ejecutar los siguientes comandos en la raíz del repositorio:

**1. Construir la imagen Docker:**
El siguiente comando le indica al motor de Docker que debe leer las instrucciones de su `Dockerfile` para empaquetar una nueva imagen:
`docker build -t devops-fehe-app:latest .`

**2. Iniciar los contenedores:**
Para levantar el  dockercompose la configuración definida en `docker-compose.yml`, se debe ejecutar el siguiente comando::
`docker compose up -d`

**3. Detener y limpiar el entorno:**
Para apagar los contenedores y liberar los recursos, se debe ejecutar el siguiente comando:
`docker compose down`

---
# Encargo DevOps Seccion 002D (Tercera evaluación)

## 10 Despliegue Continuo en AWS EC2

Para cumplir con el despliegue en un entorno orquestado en la nube, se implementó un flujo de Despliegue Continuo utilizando GitHub Actions, Docker Hub y AWS Systems Manager (SSM).

### Justificación 
En lugar de compilar el código fuente directamente en el servidor de producción, implementamos el estándar de la industria mediante un *Container Registry*:

1. *Construcción Aislada:* GitHub Actions asume el rol de integración, construyendo la imagen optimizada  y empujándola hacia Docker Hub.
2. *Despliegue Seguro (AWS SSM):* Utilizamos AWS Systems Manager para conectarnos a la instancia EC2 de AWS Academy sin necesidad de abrir el puerto SSH (22) al público general. 
3. *Orquestación (Docker Compose):* El pipeline envía el archivo docker-compose.yml al servidor. La instancia EC2 actúa únicamente como entorno de ejecución (Runtime), descargando la imagen finalizada desde Docker Hub y levantándola de forma eficiente y escalable.

##  Integrantes
* **Juan Fernández**
* **Richard Hernández**
