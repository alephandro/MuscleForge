CREATE SCHEMA MuscleForge;

USE MuscleForge;

CREATE TABLE users (
	email varchar(100) PRIMARY KEY,
	password varchar(100)
);

CREATE TABLE exercises (
	name varchar(50) PRIMARY KEY,
	muscle_group varchar(50)
);
CREATE UNIQUE INDEX idx_muscle_group ON exercises (muscle_group);