CREATE DATABASE centro_innovacion_un ;

USE centro_innovacion_un;

CREATE TABLE AUTOR (
  autorID VARCHAR(255) NOT NULL PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  afiliacion VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (author_id)
);

CREATE TABLE TEMA (
  temaID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  authorID INT NOT NULL,
  titulo VARCHAR(255) NOT NULL,
  link VARCHAR(255) NOT NULL,
  FOREIGN KEY (authorID) REFERENCES AUTOR(authorID)
);

CREATE TABLE ARTICULO (
    articuloID INT  PRIMARY KEY,
    autorID INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    link VARCHAR(255) NOT NULL,
    citacion_id VARCHAR(255),
    autores VARCHAR(255),
    publicacion VARCHAR(255),
    citado_por VARCHAR(255),
    año DATE NOT NULL,
    FOREIGN KEY (autorID) REFERENCES AUTOR(autorID)
);

