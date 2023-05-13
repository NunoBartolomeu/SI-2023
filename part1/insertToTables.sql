INSERT INTO Regioes (nome) VALUES
('Portugal'),
('Espanha');

INSERT INTO Jogadores (username, email, estado, regiao) VALUES
('Rui', 'rui@themail.com', 'ativo', 'Portugal'),
('João', 'joao@themail.com', 'inativo', 'Portugal'),
('Carlos', 'carlos@themail.com', 'ativo', 'Espanha'),
('Julio', 'julio@themail.com', 'banido', 'Espanha');

INSERT INTO Estatisticas_Jogador (id_jogador, num_jogos, num_partidas, total_pontos) VALUES
(1, 2, 2, 200),
(2, 1, 4, 400),
(3, 1, 1, 100),
(4, 2, 2, 200);

INSERT INTO Amigos (id_jogador1, id_jogador2) VALUES
(1, 2), (1,3),
(2, 1), (2, 3),
(3, 1), (3, 2), (3, 3),
(4, 3);

INSERT INTO Conversas (nome) VALUES
('Portugueses'),
('Españoles');

INSERT INTO Participantes_Conversa (id_conversa, id_jogador) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4);

INSERT INTO Mensagens (id_conversa, id_jogador, texto, data) VALUES
(1, 1, 'Boas', '2017-01-01 12:30:00'),
(1, 2, 'Boas', '2017-01-01 12:30:12'),
(1, 1, 'Bora?', '2017-01-01 12:31:16'),
(1, 1, 'Yah', '2017-01-01 12:31:59');

INSERT INTO Jogos (id, nome, url) VALUES
('LOL1234567', 'League of Legends', 'www.lol.com'),
('MC12345678', 'Minecraft', 'www.mc.com');

INSERT INTO Estatisticas_Jogo (id_jogo, num_jogadores, num_partidas, total_pontos) VALUES
('LOL1234567', 4, 7, 700),
('MC12345678', 2, 2, 200);

INSERT INTO Compras (id_jogador, id_jogo, data_de_compra, preco) VALUES
(1, 'LOL1234567', '2017-01-01', 0),
(1, 'MC12345678', '2017-01-01', 20),
(2, 'LOL1234567', '2017-01-02', 0),
(3, 'LOL1234567', '2017-01-03', 0),
(4, 'LOL1234567', '2017-01-04', 0),
(4, 'MC12345678', '2017-01-04', 20);

INSERT INTO Crachas (id_jogo, nome, url, limite_pontos) VALUES
('LOL1234567', 'begin', 'www.lol.com/img/begin', 10),
('LOL1234567', 'intermediate', 'www.lol.com/img/intermediate', 100),
('LOL1234567', 'advanced', 'www.lol.com/img/advanced', 1000),
('MC12345678', 'begin', 'www.mc.com/img/begin', 10),
('MC12345678', 'intermediate', 'www.mc.com/img/intermediate', 100),
('MC12345678', 'advanced', 'www.mc.com/img/advanced', 1000);

INSERT INTO Crachas_Obtidos (id_jogador, nome_cracha, id_jogo) VALUES
(1, 'begin', 'LOL1234567'),
(1, 'intermediate', 'LOL1234567'),
(1, 'advanced', 'LOL1234567'),
(2, 'begin', 'LOL1234567'),
(2, 'intermediate', 'LOL1234567'),
(3, 'begin', 'LOL1234567'),
(4, 'begin', 'MC12345678'),
(4, 'intermediate','MC12345678'),
(4, 'advanced', 'MC12345678');

INSERT INTO Partidas (id, id_jogo, data_inicio, data_fim, regiao) VALUES
(1, 'LOL1234567', '2017-01-03 10:10', '2017-01-03 10:20', 'Portugal'),
(2, 'LOL1234567', '2017-01-04 12:12', '2017-01-04 12:22', 'Portugal'),
(3, 'LOL1234567', '2017-01-05 14:14', '2017-01-05 14:24', 'Portugal'),
(3, 'MC12345678', '2017-01-05 14:14', '2017-01-05 14:24', 'Portugal'),
(1, 'MC12345678', '2017-01-06 16:16', '2017-01-06 16:26', 'Espanha');

INSERT INTO Partidas_Normais (id_partida, id_jogo, dificuldade) VALUES
(1, 'MC12345678', 5);

INSERT INTO Partidas_Multi_Jogador (id_partida, id_jogo, estado) VALUES
(1, 'LOL1234567', 'terminada'),
(2, 'LOL1234567', 'em curso'),
(3, 'LOL1234567', 'em curso');

INSERT INTO Pontuacoes (id_partida, id_jogo, id_jogador, pontos) VALUES
(1, 'LOL1234567', 1, 50),
(1, 'LOL1234567', 2, 50),
(2, 'LOL1234567', 3, 100),
(2, 'LOL1234567', 4, 100),
(3, 'MC12345678', 1, 50),
(3, 'LOL1234567', 2, 50),
(1, 'LOL1234567', 3, 100),
(1, 'MC12345678', 4, 100);