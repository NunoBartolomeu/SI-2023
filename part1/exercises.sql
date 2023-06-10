-- Exercise D
CREATE OR REPLACE FUNCTION criarJogador(player_email TEXT, player_username TEXT) RETURNS VOID AS $$
BEGIN
    INSERT INTO Jogadores(id, username, email, estado, regiao)
    VALUES(DEFAULT, player_username, player_email, 'ativo', 'Portugal');
END;
$$ language plpgsql;

CREATE OR REPLACE FUNCTION desativarJogador(player_email TEXT, player_username TEXT) RETURNS VOID AS $$
BEGIN
    UPDATE Jogadores 
    SET estado = 'inativo'
    WHERE email = player_email AND username = player_username;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION banirJogador(player_email TEXT, player_username TEXT) RETURNS VOID AS $$
BEGIN
    UPDATE Jogadores 
    SET estado = 'banido'
    WHERE email = player_email AND username = player_username;
END;
$$ LANGUAGE plpgsql;

-- Exercise E

CREATE OR REPLACE FUNCTION totalPontosJogador(jogador_id INT) RETURNS INT AS $$
DECLARE
    total_pontos_jogador INT;
BEGIN
    SELECT total_pontos
    INTO total_pontos_jogador
    FROM estatisticas_jogador
    WHERE id_jogador = jogador_id;

    RETURN total_pontos_jogador;
END;
$$ LANGUAGE plpgsql;

--Exercise F

--DROP FUNCTION totalJogosJogador;
CREATE OR REPLACE FUNCTION totalJogosJogador(jogador_id INT)
    RETURNS INTEGER AS $$
    DECLARE
        total_jogos INTEGER;
    BEGIN
        SELECT COUNT(DISTINCT id_jogo)
        INTO total_jogos
        FROM (
            SELECT id_jogo FROM Partidas_Normais WHERE id_jogador = jogador_id
            UNION
            SELECT id_jogo FROM Partidas_Multi_Jogador WHERE EXISTS (
                SELECT 1 FROM Pontuacoes_Multi_Jogador WHERE id_jogador = jogador_id
            )
        ) AS jogos;

        RETURN total_jogos;
    END;
$$ LANGUAGE plpgsql;

SELECT totalJogosJogador(2);

-- Exercise G
-- DROP FUNCTION PontosJogoPorJogador

CREATE OR REPLACE FUNCTION PontosJogoPorJogador(jogo_id VARCHAR(10))
    RETURNS TABLE (jogador INT, pontos NUMERIC) AS $$
    BEGIN
        RETURN QUERY
        SELECT id_jogador, SUM(total_pontos) FROM (
            SELECT pn.id_jogador, SUM(pn.pontos) AS total_pontos
            FROM Partidas_Normais pn
            WHERE pn.id_jogo = jogo_id
            GROUP BY pn.id_jogador

            UNION ALL

            SELECT pm.id_jogador, SUM(pm.pontos) AS total_pontos
            FROM Pontuacoes_multi_jogador pm
            WHERE pm.id_jogo = jogo_id
            GROUP BY pm.id_jogador
        ) AS PontosPorJogo
        GROUP BY id_jogador;

        RETURN;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM PontosJogoPorJogador('LOL1234567');

-- Exercise H
CREATE OR REPLACE PROCEDURE associarCracha(jogador_id INT, jogo_id VARCHAR(10), cracha_nome VARCHAR(255)) AS $$
DECLARE
    limite_pontos_cracha INT;
    pontos_jogador bigint;
BEGIN
    -- Verificar se o jogador atingiu o limite de pontos para obter o crachá
    SELECT limite_pontos INTO limite_pontos_cracha
    FROM Crachas
    WHERE id_jogo = jogo_id AND nome = cracha_nome;

    -- Pontos totais do jogador nesse jogo
    SELECT pontos INTO pontos_jogador
    FROM PontosJogoPorJogador(jogo_id) AS pj
    WHERE pj.jogador = jogador_id;
	
	raise notice 'LIMITE : %', limite_pontos_cracha;
	raise notice 'PONTOS : %',pontos_jogador;

    IF pontos_jogador >= limite_pontos_cracha THEN
        -- Verificar se o jogador já possui o crachá
        IF NOT EXISTS (
            SELECT 1
            FROM Crachas_Obtidos
            WHERE id_jogador = jogador_id AND id_jogo = jogo_id AND nome_cracha = cracha_nome
        ) THEN
            INSERT INTO Crachas_Obtidos (id_jogador, id_jogo, nome_cracha)
            VALUES (jogador_id, jogo_id, cracha_nome);
            RAISE NOTICE 'Crachá Obtido!';
        ELSE
            RAISE NOTICE 'O jogador já possui esse crachá!';
        END IF;
    ELSE
        RAISE NOTICE 'O jogador não atingiu o limite de pontos para obter o crachá!';
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Exercise I

--DROP PROCEDURE iniciarConversa;
CREATE OR REPLACE PROCEDURE iniciarConversa(id_jogador INT, nome_conversa VARCHAR(255), id_conversa OUT INT)
LANGUAGE plpgsql
AS $$
DECLARE
	nome_jogador VARCHAR;
	mensagem_criacao VARCHAR;
BEGIN
	INSERT INTO conversas(nome) VALUES (nome_conversa);
	SELECT id INTO id_conversa FROM conversas WHERE nome = nome_conversa;
	SELECT username INTO nome_jogador FROM jogadores WHERE id = id_jogador;
	INSERT INTO participantes_conversa(id_conversa, id_jogador) VALUES (id_conversa, id_jogador);
	SELECT nome_jogador || ' criou a conversa ' || nome_conversa INTO mensagem_criacao;
	INSERT INTO mensagens (id_conversa, id_jogador, texto) VALUES (id_conversa, id_jogador, mensagem_criacao);
END;
$$;

-- Exercise J
CREATE OR REPLACE PROCEDURE juntarConversa(jogador_id INT, conversa_id INT) AS $$
BEGIN
    INSERT INTO Participantes_Conversa(id_conversa, id_jogador) 
    VALUES(conversa_id, jogador_id);
END;
$$ LANGUAGE plpgsql;

-- Exercise K

CREATE OR REPLACE PROCEDURE enviarMensagem(jogador_id INT, conversa_id INT, mensagem_texto VARCHAR(255)) AS $$
declare
	mensagem_id Integer;
BEGIN
	select max(numero) into mensagem_id
	from mensagens
	where id_conversa = conversa_id;
	if mensagem_id is null then mensagem_id = 0;
	end if;
	INSERT INTO Mensagens (numero, id_conversa, id_jogador, texto, data)
	VALUES (mensagem_id + 1, conversa_id, jogador_id, mensagem_texto, NOW()::timestamp(0));
END;
$$ LANGUAGE plpgsql;

-- Exercise L

CREATE OR REPLACE VIEW jogadorTotalInfo AS
	SELECT 
		id, 
		estado, 
		email, 
		username, 
		count(DISTINCT p.id_jogo) as totalJogos,
		COUNT(p.id_partida) as totalPartidas,
		SUM(p.pontos) as totalPontos
	FROM TotalPontos
		INNER JOIN Pontuacoes p on id=p.id_jogador
	WHERE estado != 'banido'
	GROUP BY jogadores.id;
	
SELECT * FROM jogadorTotalInfo;

CREATE OR REPLACE VIEW jogadorTotalInfo as
SELECT totalInfo.id_jogador, count(DISTINCT totalInfo.id_jogo) as totalJogos, COUNT(totalInfo.id) as totalPartidas, SUM(totalInfo.pontos) as totalPontos
FROM (
    SELECT pn.id_jogador, pn.id_jogo, pn.id, pn.pontos AS pontos
    FROM Partidas_Normais pn
    GROUP BY pn.id_jogador, pn.id_jogo, pn.id

    UNION ALL

    SELECT pm.id_jogador, pm.id_jogo, pm.id_partida, pm.pontos AS pontos
    FROM Pontuacoes_multi_jogador pm
    GROUP BY pm.id_jogador, pm.id_jogo, pm.id_partida
) AS totalInfo
INNER JOIN jogadores j ON totalInfo.id_jogador = j.id
WHERE j.estado != 'banido'
GROUP BY totalInfo.id_jogador;

-- Exercise M
CREATE OR REPLACE FUNCTION atribuir_crachas()
    RETURNS TRIGGER AS $$
    BEGIN
        -- Verificar se a partida está terminada
        IF NEW.data_fim IS NOT NULL THEN
            -- Verificar o jogo da partida
            DECLARE
                jogo_id VARCHAR(10);
				partida_id Int;
				isNormalCheck Boolean;
				pontosObtidos Int;
            BEGIN
				-- Obter a informação da partida
                SELECT id_jogo INTO jogo_id FROM Partidas WHERE id = NEW.id AND id_jogo = NEW.id_jogo;
				SELECT id INTO partida_id from partidas where id = new.id and id_jogo = jogo_id;
				SELECT isNormal INTO isNormalCheck from partidas where id = partida_id and id_jogo = jogo_id;
				
				IF isNormalCheck then
					Select pontos into pontosObtidos from partidas_normais where id = partida_id and id_jogo = jogo_id;
				ELSE
					Select pontos into pontosObtidos from pontuacoes_multi_jogador where id_partida = partida_id and id_jogo = jogo_id;
				END IF;
                -- Atribuir os crachás para o jogador da partida
                INSERT INTO Crachas_Obtidos (id_jogador, nome_cracha, id_jogo)
                SELECT NEW.id_jogador, nome, jogo_id
                FROM Crachas
                WHERE limite_pontos <= pontosObtidos;

            END;
        END IF;

        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER atribuir_crachas_trigger
AFTER INSERT OR UPDATE ON Partidas
FOR EACH ROW
WHEN (NEW.data_fim IS NOT NULL)
EXECUTE FUNCTION atribuir_crachas();
