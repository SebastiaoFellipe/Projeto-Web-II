-- ===============================
-- POPULANDO TABELA FUNCIONARIOS
-- ===============================

INSERT INTO funcionarios (nome, cpf, cargo, tipo_vinculo)
VALUES 
('Ana Silva', '12345678901', 'Gerente de Projetos','FIXO'),
('Bruno Costa', '98765432109', 'Desenvolvedor Pleno', 'TEMPORARIO'),
('Carla Dias', '11223344556', 'Assistente Administrativo', 'ESTAGIARIO');

-- ===============================
-- POPULANDO TABELA PROFESSORES
-- ===============================

INSERT INTO professores (nome, cpf, disciplina, nivel_ensino)
VALUES
('Mariana Filho', '99988877766', 'Biologia Marinha', 'SUPERIOR'),
('Carlos Mendes', '33322211100', 'Epidemiologia', 'DOUTORADO');
