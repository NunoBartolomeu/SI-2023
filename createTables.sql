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
    data TIMESTAMP NOT NULL,

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
    id SERIAL,
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