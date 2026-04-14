# Encargo DevOps Seccion 002D OLS

Este repositorio documenta y aplica las convenciones de desarrollo colaborativo para el microservicio creado con **Java con Spring Boot**, siguiendo los requisitos del encargo

## 1. Estrategia de Ramificación: GitFlow

Hemos adoptado **GitFlow** como modelo de trabajo. 

### Justificación
Se seleccionó GitFlow frente a *Trunk-based development* debido a que:
* **Estructura** Es un modelo de trabajo más segmentado y ordenado, más similar a un entorno de trabajo profesional, muy ligada a equipos grandes de desarrollo
* **Rigidez** siempre se trabajo siguiendo instrucciones claras y concretas, no da paso a que un desarrollor toque codigo que funciona y pueda afectar al producto negativamente

---

## 2. Convenciones de Naming 

Para mantener el repositorio organizado, las ramas deben seguir este formato:

| Tipo de Rama | Prefijo | Ejemplo |
| :--- | :--- | :--- |
| **Producción** | `main` | `main` |
| **Integración** | `develop` | `develop` |
| **Funcionalidades** | `feature/` | `feature/api-endpoints` |
| **Correcciones** | `hotfix/` | `hotfix/connection-timeout` |

---

## 3. Estándar de Commits 

Utilizamos mensajes semánticos para que el historial sea legible y fácil de auditar. Cada commit debe empezar con un prefijo seguido de dos puntos:

* **`feat:`** Una nueva característica para el usuario.
* **`fix:`** Corrección de un error o bug.
* **`docs:`** Cambios solo en la documentación (como este README).

*Ejemplo: `feat: implement login controller logic`*

---

## 4. Flujo de Revisión y Merge

Para garantizar la calidad del código, no se permiten "pushes" directos a `main` ni a `develop`. El flujo obligatorio es:

1.  **Pull Requests (PR):** Todo cambio desde una `feature/` o `hotfix/` debe solicitarse mediante un PR.
2.  **Revisión por Pares:** Al menos un miembro del equipo debe revisar el código, comentar posibles mejoras y dar su aprobación (**Approve**).
3.  **Merge:** Una vez aprobado y pasadas las pruebas automáticas, se procede al merge.

- Agregar que se han realizado 4 Merge con su respectivo pull request, con las 2 features al develop, el hotfix al main y un merge final al main

---
## 5 Integración Continua (GitHub Actions)

Hemos configurado un workflows automatizado ubicado en `.github/workflows/ci.yml`.

**¿Qué hace este Action?**
- Se acciona cada que se ejecuta un `push` y `pull_request` hacia las ramas `main` y `develop`.
- Configura un entorno con **Java 25**.
- Ejecuta `./mvnw clean compile` para verificar que el código compile correctamente antes de permitir el merge.

**¿Por qué?**

Porque no es raro que los codigos puedan compilar dependiendo del computador
por ende esta validación asegura que en un entorno aislado compile con seguridad los cambios realizaados 

---

##  Integrantes
* **Juan Fernández**
* **Richard Hernández**
