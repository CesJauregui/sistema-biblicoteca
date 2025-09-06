
# 📘 Sistema de Gestión de Biblioteca

## 📌 Descripción
Aplicación desarrollada en **Java** para la gestión de una biblioteca.  
Permite administrar libros, usuarios y préstamos, garantizando el control de stock y el historial de operaciones.

Este proyecto fue desarrollado con fines educativos para practicar:
- **Programación Orientada a Objetos (POO)**
- **Persistencia en base de datos relacional (MySQL)**
- **Patrones DAO y servicios**
- **Validaciones y reglas de negocio**

---

## 🚀 Características principales
- Gestión de **Libros**: alta, baja, modificación y listado.
- Gestión de **Usuarios**: registro, edición, eliminación y listado.
- Gestión de **Préstamos**:
    - Registro de préstamo de libros (con validación de stock).
    - Registro de devoluciones (actualiza stock).
- **Reportes**:
    - Libros disponibles.
    - Libros prestados actualmente.
    - Historial de préstamos por usuario.

---

## 🗂️ Modelo de datos

### Tablas principales
**usuarios**
- id (PK)
- nombre
- email (único)
- rol (admin/lector)

**libros**
- id (PK)
- titulo
- autor
- año_publicacion
- genero
- cantidad_disponible

**prestamos**
- id (PK)
- usuario_id (FK → usuarios.id)
- libro_id (FK → libros.id)
- fecha_prestamo
- fecha_devolucion
- estado (prestado/devuelto)

---

## 🏗️ Arquitectura del proyecto
- **Modelo (Entities):** `Usuario`, `Libro`, `Prestamo`
- **DAO** Manejo de datos en BD (JDBC)
- **Servicio:** Lógica y Reglas de negocio (validaciones: stock, duplicados, etc.)
- **Presentación:** Menú en consola

---

## 🔑 Casos de uso principales
- Registrar un libro
- Registrar un usuario
- Registrar un préstamo (validando stock)
- Registrar devolución de un libro
- Reportar libros disponibles y préstamos activos

---

## ⚙️ Requisitos técnicos
- **Java 17+**
- **Maven** (para gestión de dependencias)
- **MySQL** como base de datos
- **IDE**: IntelliJ IDEA

---

## 🚀 Ejecución del proyecto
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/CesJauregui/sistema-biblicoteca
2. Crear la base de datos en MySQL:
   ```bash 
   CREATE DATABASE biblioteca_db;
3. Ejecutar la aplicación desde el IDE o terminal:
   ```bash
   mvn clean install
   mvn exec:java
---
## 📝 Licencia
Este proyecto es de uso libre para fines académicos y de aprendizaje.