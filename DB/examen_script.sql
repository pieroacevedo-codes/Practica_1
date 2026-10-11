-- =====================================================
-- EXAMEN PARCIAL - Script PostgreSQL (pgAdmin)
-- Misma base que MediConnect (HealthLink), schema propio: examen
-- Reemplaza 00000000 por tu codigo de alumno (sin la "U").
-- =====================================================

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE SCHEMA IF NOT EXISTS examen;

DROP TABLE IF EXISTS examen.tm_auditoria;
DROP TABLE IF EXISTS examen.tm_registro_clima;
DROP TABLE IF EXISTS examen.tm_usuario;
DROP TABLE IF EXISTS examen.tm_ciudad;
DROP TABLE IF EXISTS examen.tm_rol;

CREATE TABLE examen.tm_rol (
    id_rol     BIGSERIAL PRIMARY KEY,
    nombre_rol VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE examen.tm_usuario (
    id_usuario       BIGSERIAL PRIMARY KEY,
    id_rol           BIGINT NOT NULL,
    username_usuario VARCHAR(100) NOT NULL UNIQUE,
    clave_usuario    VARCHAR(100) NOT NULL,
    CONSTRAINT fk_usuario_rol
        FOREIGN KEY (id_rol) REFERENCES examen.tm_rol (id_rol)
);

CREATE TABLE examen.tm_ciudad (
    id_ciudad     BIGSERIAL PRIMARY KEY,
    nombre_ciudad VARCHAR(100) NOT NULL,
    codigo_pais   VARCHAR(10)  NOT NULL
);

CREATE TABLE examen.tm_registro_clima (
    id_registro_clima BIGSERIAL PRIMARY KEY,
    id_ciudad         BIGINT NOT NULL,
    fecha_registro    DATE NOT NULL,
    temperatura       DOUBLE PRECISION NOT NULL,
    descripcion       VARCHAR(255) NOT NULL,
    CONSTRAINT fk_registro_ciudad
        FOREIGN KEY (id_ciudad) REFERENCES examen.tm_ciudad (id_ciudad)
);

CREATE TABLE examen.tm_auditoria (
    id_auditoria      BIGSERIAL PRIMARY KEY,
    id_creacion       BIGINT NOT NULL,
    id_edicion        BIGINT NULL,
    id_eliminacion    BIGINT NULL,
    id_registro       BIGINT NOT NULL,
    fecha_creacion    TIMESTAMP NOT NULL,
    fecha_edicion     TIMESTAMP NULL,
    fecha_eliminacion TIMESTAMP NULL,
    tabla_afectada    VARCHAR(50) NOT NULL,
    estado_auditoria  INTEGER NOT NULL,
    CONSTRAINT fk_aud_creacion
        FOREIGN KEY (id_creacion)    REFERENCES examen.tm_usuario (id_usuario),
    CONSTRAINT fk_aud_edicion
        FOREIGN KEY (id_edicion)     REFERENCES examen.tm_usuario (id_usuario),
    CONSTRAINT fk_aud_eliminacion
        FOREIGN KEY (id_eliminacion) REFERENCES examen.tm_usuario (id_usuario),
    CONSTRAINT uq_aud_registro UNIQUE (tabla_afectada, id_registro)
);

-- Roles
INSERT INTO examen.tm_rol (nombre_rol) VALUES ('ADMIN'), ('CLIMA');

-- Usuarios: el password de todos es el codigo de alumno, encriptado con BCrypt
INSERT INTO examen.tm_usuario (id_rol, username_usuario, clave_usuario)
VALUES ((SELECT id_rol FROM examen.tm_rol WHERE nombre_rol = 'ADMIN'), 'admin', crypt('00000000', gen_salt('bf', 10))),
       ((SELECT id_rol FROM examen.tm_rol WHERE nombre_rol = 'CLIMA'), 'clima', crypt('00000000', gen_salt('bf', 10)));

-- Datos de prueba para HU04 (opcional)
INSERT INTO examen.tm_ciudad (nombre_ciudad, codigo_pais) VALUES ('Lima', 'PE'), ('Cusco', 'PE');

INSERT INTO examen.tm_registro_clima (id_ciudad, fecha_registro, temperatura, descripcion)
VALUES (1, '2025-10-01', 19.5, 'Nublado'),
       (1, '2025-10-02', 21.0, 'Soleado'),
       (2, '2025-10-01', 11.0, 'Frio'),
       (2, '2025-10-02', 13.0, 'Despejado');

-- Auditoria de los datos de prueba: los creo el usuario admin
INSERT INTO examen.tm_auditoria (id_creacion, id_registro, fecha_creacion, tabla_afectada, estado_auditoria)
SELECT (SELECT id_usuario FROM examen.tm_usuario WHERE username_usuario = 'admin'), id_ciudad, NOW(), 'tm_ciudad', 1
FROM examen.tm_ciudad
UNION ALL
SELECT (SELECT id_usuario FROM examen.tm_usuario WHERE username_usuario = 'admin'), id_registro_clima, NOW(), 'tm_registro_clima', 1
FROM examen.tm_registro_clima;

-- Limpieza de las tablas del script anterior (quedaron en el schema public de HealthLink)
DROP TABLE IF EXISTS public.xy_weather_record, public.xy_user, public.xy_city, public.xy_role;
