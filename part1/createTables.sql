CREATE TABLE IF NOT EXISTS Regioes (
    nome VARCHAR(255) NOT NULL PRIMARY KEY
);

DROP TYPE IF EXISTS ESTADO_JOGADOR;
CREATE TYPE ESTADO_JOGADOR AS ENUM ('ativo', 'inativo', 'banido');

CREATE TABLE IF NOT EXISTS Jogadores (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
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
    data_de_compra TIMESTAMP NOT NULL DEFAULT NOW(),
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

CREATE TABLE IF NOT EXISTS Partidas (
    id INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP,
    regiao VARCHAR(255) NOT NULL,
    isNormal BOOLEAN NOT NULL,

    CONSTRAINT fk_jogo FOREIGN KEY (id_jogo) REFERENCES Jogos(id),
    CONSTRAINT fk_regiao FOREIGN KEY (regiao) REFERENCES Regioes(nome),
    CONSTRAINT pk_jogo_partidas PRIMARY KEY (id, id_jogo)
);

CREATE TABLE IF NOT EXISTS Partidas_Normais (
    id INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    dificuldade INT NOT NULL,

    CONSTRAINT fk_partidasNorm FOREIGN KEY (id, id_jogo) REFERENCES Partidas(id, id_jogo),
    CONSTRAINT pk_partidas PRIMARY KEY (id, id_jogo),
	
	CONSTRAINT dificuldade_1_a_5 CHECK (dificuldade BETWEEN 1 AND 5)
);

DROP TYPE IF EXISTS ESTADO_PARTIDA;
CREATE TYPE ESTADO_PARTIDA AS ENUM ('em curso', 'terminada');

CREATE TABLE IF NOT EXISTS Partidas_Multi_Jogador (
    id INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    estado ESTADO_PARTIDA NOT NULL DEFAULT 'em curso',

    CONSTRAINT fk_partidas FOREIGN KEY (id, id_jogo) REFERENCES Partidas(id, id_jogo),
    CONSTRAINT pk_partidasMult PRIMARY KEY (id, id_jogo)
);

CREATE TABLE IF NOT EXISTS Pontuacoes_Normais (
    id_partida INT NOT NULL,
    id_jogador INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    pontos INT NOT NULL,

    CONSTRAINT fk_partidas FOREIGN KEY (id_partida, id_jogo) REFERENCES Partidas_Normais(id, id_jogo),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id),
    CONSTRAINT pk_pontuaçao_normal PRIMARY KEY (id_partida, id_jogador, id_jogo)
);

CREATE TABLE IF NOT EXISTS Pontuacoes_Multi_Jogador (
    id_partida INT NOT NULL,
    id_jogador INT NOT NULL,
    id_jogo VARCHAR(10) NOT NULL,
    pontos INT NOT NULL,

    CONSTRAINT fk_partidas FOREIGN KEY (id_partida, id_jogo) REFERENCES Partidas_Multi_Jogador(id, id_jogo),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES Jogadores(id),
    CONSTRAINT pk_pontuaçao_multi PRIMARY KEY (id_partida, id_jogador, id_jogo)
);

-- Update automático das estatisticas
CREATE OR REPLACE FUNCTION atualizar_estatisticas()
    RETURNS TRIGGER AS $$
    BEGIN
        -- Atualize as estatísticas do jogador correspondente
        UPDATE Estatisticas_Jogador
        SET
            num_jogos = num_jogos + 1,
            num_partidas = num_partidas + 1,
            total_pontos = total_pontos + NEW.pontos
        WHERE id_jogador = NEW.id_jogador;

        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_estatisticas
AFTER INSERT OR UPDATE ON Pontuacoes_Multi_Jogador
FOR EACH ROW
EXECUTE FUNCTION atualizar_estatisticas();

CREATE TRIGGER trigger_atualizar_estatisticas
AFTER INSERT OR UPDATE ON Pontuacoes_Normais
FOR EACH ROW
EXECUTE FUNCTION atualizar_estatisticas();


-- Criação das estatisticas com trigger
CREATE OR REPLACE FUNCTION criacao_estatisticas_jogador()
    RETURNS TRIGGER AS $$
    BEGIN
        -- Atualize as estatísticas do jogador correspondente
        INSERT INTO Estatisticas_Jogador
		VALUES (NEW.id, 0, 0, 0);
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_criacao_estatisticas_jogador
AFTER INSERT ON jogadores
FOR EACH ROW
EXECUTE FUNCTION criacao_estatisticas_jogador();