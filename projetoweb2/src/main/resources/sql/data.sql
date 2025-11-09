INSERT INTO funcionarios (nome, cpf, cargo, setor, tipo_vinculo, especialidade)
VALUES
('Ana Silva', '12345678901', 'Gerente de Projetos', 'TI', 'CLT', 'Gestão de Software'),
('Bruno Costa', '98765432109', 'Desenvolvedor Pleno', 'TI', 'PJ', 'Backend Java'),
('Carla Dias', '11223344556', 'Assistente Administrativo', 'RH', 'ESTAGIARIO', 'Rotinas de Pessoal');

INSERT INTO habitats (descricao, tipo_ambiente, temperatura) VALUES
('Aquário de água doce com plantas', 'Aquático', 24.5),
('Recinto de aves tropicais', 'Aéreo', 28.0),
('Terrário de répteis', 'Terrestre', 30.0);

INSERT INTO animais (habitat_id, nome, nome_cientifico, familia, genero, especie, classificacao, dieta, status_saude, data_entrada, idade) VALUES
(1, 'Peixe Betta', 'Betta splendens', 'Osphronemidae', 'Betta', 'splendens', 'NAO_AMEACADO', 'Onívoro', 'Saudável', '2025-10-01', 1),
(2, 'Papagaio Arara', 'Ara macao', 'Psittacidae', 'Ara', 'macao', 'AMEACADO', 'Frugívoro', 'Saudável', '2025-09-15', 3),
(3, 'Jiboia', 'Boa constrictor', 'Boidae', 'Boa', 'constrictor', 'NAO_AMEACADO', 'Carnívoro', 'Doente', '2025-10-10', 5);

INSERT INTO reabilitacoes (animal_id, motivo, tratamento, data_entrada, data_saida, status, observacoes) VALUES
(3, 'Doença respiratória', 'Nebulização diária e antibióticos', '2025-10-10', NULL, 'Em tratamento', 'Animal ainda em observação, necessita acompanhamento diário.'),
(2, 'Fratura na asa', 'Imobilização da asa e fisioterapia', '2025-09-20', '2025-10-05', 'Concluído', 'Recuperação completa, liberado para voo.'),
(1, 'Troca de água inadequada', 'Correção do pH e monitoramento', '2025-10-01', '2025-10-07', 'Concluído', 'Animal reagiu bem, sem complicações.');
