
CREATE DATABASE IF NOT EXISTS estacionamento;
USE estacionamento;

CREATE TABLE motorista (
    idMotorista int NOT NULL AUTO_INCREMENT,
    nome varchar (100) NOT NULL,
    genero varchar(50) NOT NULL,
    cpf int NOT NULL,
    rg int NOT NULL,
    celular int NOT NULL,
    email varchar(100) NOT NULL,
    senha varchar (50) NOT NULL,
    PRIMARY KEY (idMotorista));

