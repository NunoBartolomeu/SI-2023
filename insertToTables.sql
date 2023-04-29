INSERT INTO Regioes (nome) VALUES
('Portugal'),
('Espanha');

INSERT INTO Jogadores (username, email, estado, regiao) VALUES
('Rui', 'rui@themail.com', 'ativo', 'Portugal'),
('João', 'joao@themail.com', 'inativo', 'Portugal'),
('Carlos', 'carlos@themail.com', 'ativo', 'Espanha'),
('Julio', 'julio@themail.com', 'banido', 'Espanha'),

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

INSERT INTO Jogos (nome, url) VALUES
('League of Legends', 'www.lol.com'),
('Minecraft', 'www.mc.com'),

INSERT INTO Estatisticas_Jogo (id_jogo, num_jogadores, num_partidas, total_pontos) VALUES
(1, 4, 7, 700),
(2, 2, 2, 200);

INSERT INTO Compras (id_jogador, id_jogo, data_de_compra, preco) VALUES
(1, 1, '2017-01-01', 0),
(1, 2, '2017-01-01', 20),
(2, 1, '2017-01-02', 0),
(3, 1, '2017-01-03', 0),
(4, 1, '2017-01-04', 0),
(4, 2, '2017-01-04', 20),

INSERT INTO Crachas (id_jogo, nome, url, limite_pontos) VALUES
(1, 'begin', 'www.lol.com/img/begin', 10),
(1, 'intermediate', 'www.lol.com/img/intermediate', 100),
(1, 'advanced', 'www.lol.com/img/advanced', 1000),
(2, 'begin', 'www.mc.com/img/begin', 10),
(2, 'intermediate', 'www.mc.com/img/intermediate', 100),
(2, 'advanced', 'www.mc.com/img/advanced', 1000);

INSERT INTO Crachas_Obtidos (id_jogador, id_cracha) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 1),
(4, 1),
(4, 2),
(4, 3);

INSERT INTO Partida (id_jogo, data_inicio, data_fim, regiao) VALUES
(1, '2017-01-03 10:10', '2017-01-03 10:20', 'Portugal'),
(1, '2017-01-04 12:12', '2017-01-04 12:22', 'Portugal'),
(1, '2017-01-05 14:14', '2017-01-05 14:24', 'Portugal'),
(2, '2017-01-06 16:16', '2017-01-06 16:26', 'Espanha'),

INSERT INTO Partida_Normal (id_partida, id_jogo, dificuldade) VALUES
(1, 2, 5);

INSERT INTO Partida_Multi_Jogador (id_partida, id_jogo, estado) VALUES
(1, 1, "terminada"),
(1, 2, "em curso"),
(1, 3, "em curso");

INSERT INTO Pontuacao (id_partida, id_jogo, id_jogador, pontos) VALUES
(1, 1, 1, 50),
(1, 1, 2, 50),
(2, 1, 3, 100),
(2, 1, 4, 100),
(3, 1, 1, 50),
(3, 1, 2, 50),
(4, 1, 3, 100),
(4, 1, 4, 100);