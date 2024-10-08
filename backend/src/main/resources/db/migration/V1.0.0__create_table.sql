CREATE DATABASE IF NOT EXISTS `visual_dictionary`;

USE `visual_dictionary`;

CREATE TABLE IF NOT EXISTS `users` (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `dictionary` (
    id               INT             NOT NULL AUTO_INCREMENT,
    name             VARCHAR(32)     NOT NULL,
    description      VARCHAR(400)     NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `tags` (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `tags_relation_map` (
    dictionary_id INT NOT NULL,
    tag_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (dictionary_id, tag_id),
    FOREIGN KEY (dictionary_id) REFERENCES dictionary(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);