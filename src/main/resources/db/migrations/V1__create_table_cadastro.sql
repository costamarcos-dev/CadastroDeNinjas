-- V1__create_table_tb_cadastro.sql
CREATE TABLE tb_cadastro (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(255),
                             email VARCHAR(255) UNIQUE,
                             imgUrl VARCHAR(255),
                             idade INT,
                             missoes_id BIGINT
);