/*

CREATE DATABASE IF NOT EXISTS todoliste DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE todoliste;

DROP TABLE IF EXISTS categories;
CREATE TABLE categories
(
    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    categorie VARCHAR(20) NOT NULL
    
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS liste;
CREATE TABLE liste
(
    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tache VARCHAR(100) NOT NULL,
    id_categorie INT UNSIGNED,
    urgent BOOLEAN,
    Fait BOOLEAN,
    CONSTRAINT fk_id_categorie FOREIGN KEY (id_categorie)
    REFERENCES categories(id)

    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/