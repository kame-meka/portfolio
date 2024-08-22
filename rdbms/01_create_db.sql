CREATE DATABASE IF NOT EXISTS visual_dictionary;
USE visual_dictionary;

CREATE TABLE dictionary (
    id               INT             NOT NULL AUTO_INCREMENT,
    name             VARCHAR(32)     NOT NULL,
    description      VARCHAR(400)     NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tags (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tags_relation_map (
    dictionary_id INT NOT NULL,
    tag_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (dictionary_id, tag_id),
    FOREIGN KEY (dictionary_id) REFERENCES dictionary(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);