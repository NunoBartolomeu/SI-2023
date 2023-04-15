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
    id SERIAL PRIMARY KEY,
    id_jogador INT NOT NULL,
    num_jogos INT NOT NULL,
    num_partidas INT NOT NULL,
    total_pontos INT NOT NULL,

    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Amigos (
    id SERIAL PRIMARY KEY,
    id_jogador1 INT NOT NULL,
    id_jogador2 INT NOT NULL,

    CONSTRAINT fk_jogador1 FOREIGN KEY (id_jogador1) REFERENCES Jogadores(id),
    CONSTRAINT fk_jogador2 FOREIGN KEY (id_jogador2) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Conversas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Participantes_Conversa (
    id SERIAL PRIMARY KEY,
    id_conversa INT NOT NULL, 
    id_jogador INT NOT NULL,

    CONSTRAINT fk_conversa FOREIGN KEY (id_conversa) REFERENCES Conversas(id),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Mensagens (
    id SERIAL PRIMARY KEY,
    id_conversa INT NOT NULL,
    id_jogador INT NOT NULL,
    texto VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL,

    CONSTRAINT fk_conversa FOREIGN KEY (id_conversa) REFERENCES Conversas(id),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id)
);

CREATE TABLE IF NOT EXISTS Jogos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Estatisticas_Jogo (
    id SERIAL PRIMARY KEY,
    id_jogo INT NOT NULL,
    num_jogadores INT NOT NULL,
    num_partidas INT NOT NULL,
    total_pontos INT NOT NULL,

    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id)
);

CREATE TABLE IF NOT EXISTS Compras (
    id SERIAL PRIMARY KEY,
    id_jogador INT NOT NULL,
    id_jogo INT NOT NULL,
    data_de_compra TIMESTAMP NOT NULL,
    preco FLOAT NOT NULL,

    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id),
    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id)
);

CREATE TABLE IF NOT EXISTS Crachas (
    id SERIAL PRIMARY KEY,
    id_jogo INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    limite_pontos INT NOT NULL,

    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id)
);

CREATE TABLE IF NOT EXISTS Crachas_Obtidos (
    id SERIAL PRIMARY KEY,
    id_jogador INT NOT NULL,
    id_cracha INT NOT NULL,

    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id),
    CONSTRAINT fk_cracha FOREIGN KEY (id_cracha) REFERENCES Crachas(id)
);

CREATE TABLE IF NOT EXISTS Partida (
    id SERIAL PRIMARY KEY,
    id_jogo INT NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    regiao VARCHAR(255) NOT NULL,

    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id),
    CONSTRAINT fk_regiao FOREIGN KEY (regiao) REFERENCES Regioes(nome)
);

CREATE TABLE IF NOT EXISTS Partida_Normal (
    id SERIAL PRIMARY KEY,
    id_partida INT NOT NULL,
    dificuldade INT NOT NULL,

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES Partida(id)
);

DROP TYPE IF EXISTS ESTADO_PARTIDA;
CREATE TYPE ESTADO_PARTIDA AS ENUM ('em curso', 'terminada');

CREATE TABLE IF NOT EXISTS Partida_Multi_Jogador (
    id SERIAL PRIMARY KEY,
    id_partida INT NOT NULL,
    estado ESTADO_PARTIDA NOT NULL DEFAULT 'em curso',

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES Partida(id)
);

CREATE TABLE IF NOT EXISTS Pontuacao (
    id SERIAL PRIMARY KEY,
    id_partida INT NOT NULL,
    id_jogador INT NOT NULL,
    pontos INT NOT NULL,

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES Partida(id),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id)
);
