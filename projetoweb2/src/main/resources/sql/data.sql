INSERT INTO funcionarios (id_professor, nome, cpf, cargo, tipo_vinculo)
VALUES
('Ana Silva', '12345678901', 'Agente Contábil', 'FIXO'),
('Bruno Costa', '98765432109', 'Analista de Aves', 'TEMPORARIO'),
('Carla Dias', '11223344556', 'Assistente Administrativo', 'ESTAGIARIO');
('Diego Souza', '66554433221', 'Técnico de Reabilitação', 'FIXO');
('Elaine Rocha', '77889966554', 'Bióloga', 'TEMPORARIO');

INSERT INTO professores (id_professor, nome, cpf, area_aplicada, nivel_academico) VALUES
('Mariana Filho', '99988877766', 'Biologia Marinha', 'GRADUACAO'),
('Lucas Almeida', '55566677788', 'Ecologia', 'MESTRADO'),
('Fernanda Gomes', '44433322211', 'Zoologia', 'DOUTORADO');

INSERT INTO habitats (descricao, tipo_ambiente, temperatura) VALUES
('Aquário de água doce com plantas', 'Aquático', 24.5),
('Recinto de aves tropicais', 'Aéreo', 28.0),
('Terrário de répteis', 'Terrestre', 30.0);

INSERT INTO animais (habitat_id, nome, nome_cientifico, familia, genero, especie, classificacao, dieta, status_saude, data_entrada, idade) VALUES
(1, 'Peixe Betta', 'Betta splendens', 'Osphronemidae', 'Betta', 'peixe', 'NAO_AMEACADO', 'Onívoro', 'Saudável', '2025-10-01', 1),
(2, 'Papagaio Arara', 'Ara macao', 'Psittacidae', 'Ara', 'ave', 'AMEACADO', 'Frugívoro', 'Saudável', '2025-09-15', 3),
(3, 'Jiboia', 'Boa constrictor', 'Boidae', 'Boa', 'réptil', 'NAO_AMEACADO', 'Carnívoro', 'Doente', '2025-10-10', 5);
(1, 'Tetra Neon', 'Paracheirodon innesi', 'Characidae', 'Paracheirodon', 'peixe', 'NAO_AMEACADO', 'Onívoro', 'Saudável', '2025-10-12', 1),
(1, 'Corydora', 'Corydoras paleatus', 'Callichthyidae', 'Corydoras', 'peixe', 'NAO_AMEACADO', 'Onívoro', 'Saudável', '2025-10-05', 2),
(2, 'Canário', 'Serinus canaria', 'Fringillidae', 'Serinus', 'ave', 'NAO_AMEACADO', 'Frugívoro', 'Doente', '2025-10-08', 1),
(2, 'Arara Azul', 'Anodorhynchus hyacinthinus', 'Psittacidae', 'Anodorhynchus', 'ave', 'AMEACADO', 'Frugívoro', 'Saudável', '2025-09-25', 4),
(3, 'Iguana Verde', 'Iguana iguana', 'Iguanidae', 'Iguana', 'réptil', 'NAO_AMEACADO', 'Herbívoro', 'Saudável', '2025-10-03', 3),
(3, 'Cascavel', 'Crotalus durissus', 'Viperidae', 'Crotalus', 'réptil', 'AMEACADO', 'Carnívoro', 'Doente', '2025-10-09', 6),
(1, 'Peixe Oscar', 'Astronotus ocellatus', 'Cichlidae', 'Astronotus', 'peixe', 'NAO_AMEACADO', 'Carnívoro', 'Saudável', '2025-10-02', 2);


INSERT INTO reabilitacoes (animal_id, motivo, tratamento, data_entrada, data_saida, status, observacoes) VALUES
(3, 'Doença respiratória', 'Nebulização diária e antibióticos', '2025-10-10', NULL, 'Em tratamento', 'Animal ainda em observação, necessita acompanhamento diário.'),
(2, 'Fratura na asa', 'Imobilização da asa e fisioterapia', '2025-09-20', '2025-10-05', 'Concluído', 'Recuperação completa, liberado para voo.'),
(1, 'Troca de água inadequada', 'Correção do pH e monitoramento', '2025-10-01', '2025-10-07', 'Concluído', 'Animal reagiu bem, sem complicações.');

INSERT INTO candidatos (nome, cpf, formacao_academica, email)
VALUES
('Ana Souza', '123.456.789-00', 'Engenharia de Software', 'ana.souza@email.com'),
('Carlos Silva', '987.654.321-00', 'Ciência da Computação', 'carlos.silva@email.com'),
('Beatriz Lima', '456.789.123-00', 'Sistemas de Informação', 'beatriz.lima@email.com'),
('Daniel Costa', '321.654.987-00', 'Análise e Desenvolvimento de Sistemas', 'daniel.costa@email.com');

INSERT INTO estoque (codigo_produto, nome_produto, quantidade, unidade_medida, data_validade)
VALUES
('RAC-001', 'Ração Canina Premium', 50, 'kg', '2025-03-10'),
('RAC-002', 'Ração Felina Adulto', 30, 'kg', '2025-05-20'),
('SUP-010', 'Suplemento Vitaminado A-Z', 100, 'un', '2026-01-15'),
('FAR-500', 'Feno Selecionado', 200, 'kg', '2024-12-01'),
('GRA-300', 'Grãos Mistos', 150, 'kg', '2025-08-12');

INSERT INTO alimentacoes 
(tipo_alimentacao, quantidade, data_alimentacao, observacoes, funcionario_id, animal_id, estoque_id)
VALUES
('Frutas picadas', 0.25, '2025-01-20', 'Frutas variadas: maçã e banana', 2, 2, 5),
('Roedor congelado', 0.30, '2025-01-20', 'Roedor pequeno descongelado', 12, 3, 1),
('Ração flocada', 0.02, '2025-01-20', 'Alimentação diária', 1, 4, 2),
('Ração para fundo', 0.03, '2025-01-20', 'Ração específica de fundo', 1, 5, 2),
('Sementes mistas', 0.04, '2025-01-20', 'Mistura de alpiste', 12, 6, 5),
('Frutas e castanhas', 0.30, '2025-01-20', 'Mistura rica em energia', 2, 7, 5),
('Vegetais frescos', 0.50, '2025-01-20', 'Couve, rúcula e espinafre', 14, 8, 4),
('Roedor congelado', 0.25, '2025-01-20', 'Roedor pequeno', 12, 9, 1),
('Ração de peixe carnívoro', 0.05, '2025-01-20', 'Consumo normal', 1, 10, 2),
('Vegetais frescos', 0.55, '2025-01-20', 'Alimentação reforçada', 13, 11, 4),
('Ração específica para Betta', 0.01, '2025-01-20', 'Pequena porção', 1, 12, 2),
('Frutas e sementes', 0.20, '2025-01-20', 'Alimentação variada', 2, 13, 5),
('Peixe pequeno', 0.40, '2025-01-20', 'Peixe fresco', 14, 14, 3),
('Ração mista + frutas', 0.80, '2025-01-20', 'Frutas incluídas por recomendação veterinária', 13, 15, 1),
('Castanhas', 0.10, '2025-01-21', 'Reforço energético', 12, 2, 5);
