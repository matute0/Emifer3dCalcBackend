# Emifer 3D - Backend API

API RESTful desarrollada con **Spring Boot** para la gestión centralizada de impresión 3D: administración de catálogo de impresoras, filamentos, control de usuarios con autenticación JWT y motor de cálculo de presupuestos.
<img width="1084" height="910" alt="image" src="https://github.com/user-attachments/assets/caebb134-f53e-41a1-be5f-4e04b0dc530b" />

---

## 🚀 Descripción del Proyecto

El backend de Emifer 3D proporciona los servicios centrales para la plataforma:
* **Cálculo de Costos Avanzado:** Algoritmo que contempla horas/minutos de impresión, consumo eléctrico de la impresora ($/\text{kWh}$), desgaste mecánico, masa de filamento utilizada y margen de ganancia configurable.
* **Gestión de Catálogo (CRUD):** Control de impresoras 3D y tipos de filamentos con filtrado por disponibilidad.
* **Seguridad y Sesiones:** Autenticación de usuario mediante cookies HTTP-Only y tokens JWT.
* **Manejo Centralizado de Errores:** Estructura uniforme de respuestas para excepciones (`ErrorResponse`).

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje & Framework:** Java & Spring Boot 3+
* **Seguridad:** Spring Security + JWT (JSON Web Tokens)
* **Base de Datos:** MongoDB / Spring Data MongoDB
* **Documentación:** OpenAPI 3.1.0 & Swagger UI
* **Contenedorización:** Docker

---

## 📖 Documentación de la API (Swagger UI)

Con la aplicación en ejecución, puedes explorar e interactuar con todos los endpoints directamente desde tu navegador:

* **Swagger UI:** `http://[IP_DEL_SERVIDOR]:8080/swagger-ui.html`
* **Especificación OpenAPI (JSON):** `http://[IP_DEL_SERVIDOR]:8080/v3/api-docs`

---

## 📌 Resumen de Endpoints

### 🔐 Autenticación y Usuarios (`/user`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/user/login` | Autentica credenciales e inyecta la cookie de sesión JWT. |
| `PATCH` | `/user/logout` | Termina la sesión y limpia la cookie del navegador. |
| `GET` | `/user/validate` | Verifica la validez del token/sesión activa. |
| `GET` | `/user/get` | Obtiene el perfil e información del usuario autenticado. |

### 🖨️ Impresoras (`/printer`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/printer/register` | Registra una nueva impresora 3D. |
| `GET` | `/printer/list` | Obtiene la lista completa de impresoras registradas. |
| `GET` | `/printer/get/{id}` | Obtiene el detalle de una impresora mediante su ID. |
| `PUT` | `/printer/update/{id}` | Actualiza las especificaciones de una impresora. |
| `DELETE`| `/printer/delete` | Elimina una impresora del sistema (`?id=...`). |

### 🧵 Filamentos (`/filament`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/filament/register` | Registra un nuevo tipo de filamento. |
| `GET` | `/filament/get` | Lista los filamentos disponibles en el catálogo. |
| `GET` | `/filament/getByID` | Consulta un filamento por su ID (`?id=...`). |
| `PATCH`| `/filament/update/{id}` | Actualización parcial de los datos de un filamento. |
| `DELETE`| `/filament/delete` | Elimina un filamento del sistema (`?id=...`). |

### 💰 Configuración y Cálculo de Costos (`/cost`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/cost/calculate` | Ejecuta el cálculo completo del costo final de una impresión. |
| `GET` | `/cost/config/get` | Obtiene la configuración global de costos (precio kWh, ganancia). |
| `PUT` | `/cost/config/update` | Actualiza los valores de la configuración global de costos. |

---

## ⚙️ Perfiles y Variables de Entorno

El proyecto soporta múltiples entornos de ejecución mediante Spring Profiles (`dev` y `docker`).
