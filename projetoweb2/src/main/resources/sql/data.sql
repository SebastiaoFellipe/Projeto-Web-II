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
