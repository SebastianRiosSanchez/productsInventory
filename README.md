# 🛍️ Products & Inventory Microservices

Este proyecto contiene dos microservicios independientes desarrollados con **Spring Boot**, comunicados mediante **REST API**, contenedorizados con **Docker** Se ha documentado el servicio de producto unicamente con **Swagger (OpenAPI)**.Esto con el fin de ilustrar a aquellas personas que se adentran en el maravilloso mundo del desarrollo, más puntualmente el desarrollo del lado del backend 💡 
¡Espero que pueda ser de ayuda para ti y tus sueños!. 🫡 
Y como dijo uno de los grandes... 🕊️ 🥲 "_Si puedes imaginarlo, puedes programarlo_" 🚀🔥🚀🔥🚀🔥

---

## 📁 Estructura del proyecto

```
productsInventory/
- product-service/product-service-----------------> Servicio de gestión de productos
- inventory-service/inventory-service-------------> Servicio de gestión de inventarios
- docker-compose.yml------------------------------> Orquestación de los contenedores
- README.md --------------------------------------> Documentación del proyecto
```

---

## 🚀 Servicios incluidos

| Servicio          | Puerto | Descripción                                  |
|------------------|--------|----------------------------------------------|
| `product-service` | `8081` | CRUD de productos. |
| `inventory-service` | `8082` | Consulta y actualización de inventario      |

---

## 🔐 Autenticación

El `inventory-service` utiliza una **API Key** para autenticarse.

| Header        | Valor                |
|---------------|----------------------|
| `x-api-key`   | `mi-api-key-secreta` |

> **⚠️ Importante:** Swagger en el servicio de inventario requiere autenticación para acceder a los endpoints. Por eso no olvides copiar la apikey 😉.

---

## 🧪 Swagger UI

- [🔗 Product Service](http://localhost:8081/swagger-ui/index.html)
- [🔗 Inventory Service](http://localhost:8082/swagger-ui/index.html)

---

## 🐳 Cómo ejecutar con Docker

1. Asegúrate de tener Docker y Docker Compose instalados.
2. Desde la raíz del proyecto (`productsInventory/`) ejecuta:

```bash
docker-compose up --build
```

Esto construirá las imágenes de ambos servicios y los levantará en los puertos `8081` y `8082`.

---

## 🧰 Endpoints de ejemplo

### 📦 `GET /products`

```bash
curl -X GET http://localhost:8081/products
```

### 📦 `GET /inventories/{productId}`

```bash
curl -X GET http://localhost:8082/inventories/1   -H "x-api-key: mi-api-key-secreta"
```

---

## 💻 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Docker & Docker Compose
- Swagger / OpenAPI 3
- REST API + Seguridad por API Key

---

## ✨ Autor

Desarrollado con todo el 💚 por [Sebastián Rios Sanchez](https://github.com/sebasr23)

---

## 📌 Licencia

Este proyecto está bajo la Licencia MIT. Puedes usarlo, modificarlo y compartirlo libremente.
