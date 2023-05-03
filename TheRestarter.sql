DROP TABLE IF EXISTS Pontuacao;
DROP TABLE IF EXISTS Partida_Multi_Jogador;
DROP TABLE IF EXISTS Partida_Normal;
DROP TABLE IF EXISTS Partida;
DROP TABLE IF EXISTS Crachas_Obtidos;
DROP TABLE IF EXISTS Crachas;
DROP TABLE IF EXISTS Compras;
DROP TABLE IF EXISTS Estatisticas_Jogo;
DROP TABLE IF EXISTS Jogos;
DROP TABLE IF EXISTS Mensagens;
DROP TABLE IF EXISTS Participantes_Conversa;
DROP TABLE IF EXISTS Conversas;
DROP TABLE IF EXISTS Amigos;
DROP TABLE IF EXISTS Estatisticas_Jogador;
DROP TABLE IF EXISTS Jogadores;
DROP TABLE IF EXISTS Regioes;








CREATE TABLE IF NOT EXISTS Regioes (
    nome VARCHAR(255) NOT NULL PRIMARY KEY
);

DROP TYPE IF EXISTS ESTADO_JOGADOR;
CREATE TYPE ESTADO_JOGADOR AS ENUM ('ativo', 'inativo', 'banido');

CREATE TABLE IF NOT EXISTS Jogadores (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    estado ESTADO_JOGADOR NOT NULL DEFAULT 'ativo',
    regiao VARCHAR(255) NOT NULL,
    
	CONSTRAINT fk_regiao FOREIGN KEY (regiao) REFERENCES Regioes(nome)
);

CREATE TABLE IF NOT EXISTS Estatisticas_Jogador (
    id_jogador INT NOT NULL,
    num_jogos INT NOT NULL,
    num_partidas INT NOT NULL,
    total_pontos INT NOT NULL,

    CONSTRAINT pk_jogador PRIMARY KEY (id_jogador),
    
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Amigos (
    id_jogador1 INT NOT NULL,
    id_jogador2 INT NOT NULL,

    CONSTRAINT pk_amigos PRIMARY KEY (id_jogador1, id_jogador2),

    CONSTRAINT fk_jogador1 FOREIGN KEY (id_jogador1) REFERENCES Jogadores(id),
    CONSTRAINT fk_jogador2 FOREIGN KEY (id_jogador2) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Conversas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Participantes_Conversa (
    id_conversa INT NOT NULL, 
    id_jogador INT NOT NULL,

    CONSTRAINT pk_jogador_conversa PRIMARY KEY (id_jogador, id_conversa),

    CONSTRAINT fk_conversa FOREIGN KEY (id_conversa) REFERENCES Conversas(id),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Mensagens (
    numero SERIAL,
    id_conversa INT NOT NULL,
    id_jogador INT NOT NULL,
    texto VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_mensagens PRIMARY KEY (numero, id_conversa),

    CONSTRAINT fk_conversa FOREIGN KEY (id_conversa) REFERENCES Conversas(id),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Jogos (
    id VARCHAR(10) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
	
	CONSTRAINT id_length_10 CHECK (LENGTH(id) = 10)
);

CREATE TABLE IF NOT EXISTS Estatisticas_Jogo (
    id_jogo VARCHAR(10) NOT NULL,
    num_jogadores INT NOT NULL,
    num_partidas INT NOT NULL,
    total_pontos INT NOT NULL,

    CONSTRAINT pk_jogo PRIMARY KEY (id_jogo),

    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id)
);

CREATE TABLE IF NOT EXISTS Compras (
    id_jogador INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    data_de_compra TIMESTAMP NOT NULL,
    preco FLOAT NOT NULL,

    CONSTRAINT pk_jogo_jogador PRIMARY KEY (id_jogador, id_jogo),

    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id),
    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id)
);

CREATE TABLE IF NOT EXISTS Crachas (
    nome VARCHAR(255) NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    url VARCHAR(255) NOT NULL,
    limite_pontos INT NOT NULL,

    CONSTRAINT pk_crachas primary key (id_jogo, nome),

    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id)
);

CREATE TABLE IF NOT EXISTS Crachas_Obtidos (
    id_jogador INT NOT NULL,
    nome_cracha VARCHAR(255) NOT NULL,
	id_jogo VARCHAR(10) NOT NULL,
	
    CONSTRAINT pk_jogador_cracha_jogo PRIMARY KEY (id_jogador, id_jogo, nome_cracha),

    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id),
    CONSTRAINT fk_cracha FOREIGN KEY (nome_cracha, id_jogo) REFERENCES Crachas(nome, id_jogo)
);

CREATE TABLE IF NOT EXISTS Partida (
    id INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    regiao VARCHAR(255) NOT NULL,

    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id),
    CONSTRAINT fk_regiao FOREIGN KEY (regiao) REFERENCES Regioes(nome),
    CONSTRAINT pk_jogo_partida PRIMARY KEY (id, id_jogo)
);

CREATE TABLE IF NOT EXISTS Partida_Normal (
    id_partida INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    dificuldade INT NOT NULL,

    CONSTRAINT fk_partida FOREIGN KEY (id_partida, id_jogo) REFERENCES Partida(id, id_jogo),
    CONSTRAINT pk_partida PRIMARY KEY (id_partida, id_jogo),
	
	CONSTRAINT dificuldade_1_a_5 CHECK (dificuldade BETWEEN 1 AND 5)
);

DROP TYPE IF EXISTS ESTADO_PARTIDA;
CREATE TYPE ESTADO_PARTIDA AS ENUM ('em curso', 'terminada');

CREATE TABLE IF NOT EXISTS Partida_Multi_Jogador (
    id_partida INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    estado ESTADO_PARTIDA NOT NULL DEFAULT 'em curso',

    CONSTRAINT fk_partida FOREIGN KEY (id_partida, id_jogo) REFERENCES Partida(id, id_jogo),
    CONSTRAINT pk_partidaMult PRIMARY KEY (id_partida, id_jogo)
);

CREATE TABLE IF NOT EXISTS Pontuacao (
    id_partida INT NOT NULL,
    id_jogador INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    pontos INT NOT NULL,

    CONSTRAINT fk_partida FOREIGN KEY (id_partida, id_jogo) REFERENCES Partida(id, id_jogo),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id),
    CONSTRAINT pk_pontuaçao PRIMARY KEY (id_partida, id_jogador, id_jogo)
);












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

INSERT INTO Partida (id, id_jogo, data_inicio, data_fim, regiao) VALUES
(1, 'LOL1234567', '2017-01-03 10:10', '2017-01-03 10:20', 'Portugal'),
(2, 'LOL1234567', '2017-01-04 12:12', '2017-01-04 12:22', 'Portugal'),
(3, 'LOL1234567', '2017-01-05 14:14', '2017-01-05 14:24', 'Portugal'),
(1, 'MC12345678', '2017-01-06 16:16', '2017-01-06 16:26', 'Espanha');

INSERT INTO Partida_Normal (id_partida, id_jogo, dificuldade) VALUES
(1, 'MC12345678', 5);

INSERT INTO Partida_Multi_Jogador (id_partida, id_jogo, estado) VALUES
(1, 'LOL1234567', 'terminada'),
(2, 'LOL1234567', 'em curso'),
(3, 'LOL1234567', 'em curso');

INSERT INTO Pontuacao (id_partida, id_jogo, id_jogador, pontos) VALUES
(1, 'LOL1234567', 1, 50),
(1, 'LOL1234567', 2, 50),
(2, 'LOL1234567', 3, 100),
(2, 'LOL1234567', 4, 100),
(3, 'LOL1234567', 1, 50),
(3, 'LOL1234567', 2, 50),
(1, 'MC12345678', 3, 100),
(1, 'MC12345678', 4, 100);