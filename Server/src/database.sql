CREATE SCHEMA MuscleForge;

USE MuscleForge;

CREATE TABLE usuarios (
	user varchar(50),
    password varchar(100)
);

CREATE  USER usuario1 IDENTIFIED BY 'contrasenya';