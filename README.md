# Encargo DevOps

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


---



---

##  Integrantes
* **Juan Fernández**
* **Richard Hernández**
