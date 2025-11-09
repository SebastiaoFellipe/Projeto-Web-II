CREATE DATABASE IF NOT EXISTS projeto-web2;

CREATE TABLE funcionarios (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  cpf CHAR(11) NOT NULL UNIQUE,
  cargo VARCHAR(100) NOT NULL,
  setor VARCHAR(100) NOT NULL,
  tipo_vinculo ENUM('FIXO','TEMPORARIO','ESTAGIARIO') NOT NULL,
  especialidade VARCHAR(200) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela Habitat
CREATE TABLE habitats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    tipo_ambiente VARCHAR(100) NOT NULL,
    temperatura DOUBLE NOT NULL
);

-- Tabela Animal
CREATE TABLE animais (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    habitat_id BIGINT,
    nome VARCHAR(255) NOT NULL,
    nome_cientifico VARCHAR(255),
    familia VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    especie VARCHAR(255) NOT NULL,
    classificacao ENUM('AMEACADO','NAO_AMEACADO','EXTINTO') NOT NULL,
    dieta VARCHAR(255) NOT NULL,
    status_saude VARCHAR(255) NOT NULL,
    data_entrada DATE NOT NULL,
    idade INT NOT NULL,
    FOREIGN KEY (habitat_id) REFERENCES habitats(id)
);

-- Tabela Reabilitacao
CREATE TABLE reabilitacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    animal_id BIGINT NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    tratamento VARCHAR(255),
    data_entrada DATE NOT NULL,
    data_saida DATE,
    status VARCHAR(100) NOT NULL,
    observacoes TEXT,
    FOREIGN KEY (animal_id) REFERENCES animais(id)
);

-- Tabela Professores
CREATE TABLE professores(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    area_aplicada VARCHAR(100) NOT NULL,
    nivel_academico ENUM('GRADUACAO','MESTRADO','DOUTORADO','POS-DOUTORADO') NOT NULL,
);

-- Tabela funcionarios
CREATE TABLE funcionarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    cargo VARCHAR(100) NOT NULL,
    tipo_vinculo ENUM('FIXO','TEMPORÁRIO','ESTAGIARIO') NOT NULL,
);