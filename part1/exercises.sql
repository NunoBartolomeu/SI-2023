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

SELECT totalJogosJogador(4);

-- Exercise G
CREATE OR REPLACE FUNCTION PontosJogoPorJogador(referencia_jogo text) RETURNS TABLE(id_jogador INT, total_pontos bigint) as $$
BEGIN
    RETURN QUERY
    select P.id_jogador, sum(pontos)
    from pontuacoes as P
    where P.id_jogo = referencia_jogo
    group by P.id_jogador;

end
$$ LANGUAGE plpgsql;

SELECT * FROM PontosJogoPorJogador('LOL1234567');


-- Exercise H

CREATE OR REPLACE PROCEDURE associarCracha(jogador_id INT, jogo_id VARCHAR(10), cracha_nome VARCHAR(255)) AS $$
DECLARE
    limite_pontos_cracha INT;
    pontos_jogador INT;
BEGIN
    -- Verificar se o jogador atingiu o limite de pontos para obter o crachá
    SELECT limite_pontos INTO limite_pontos_cracha
    FROM Crachas
    WHERE id_jogo = jogo_id AND nome = cracha_nome;

	-- Pontos totais do jogador nesse jogo
    SELECT SUM(pontos) INTO pontos_jogador
    FROM Pontuacoes
    WHERE id_jogador = jogador_id AND id_jogo = jogo_id;

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
	SELECT nome_jogador || ' criou esta conversa' INTO mensagem_criacao;
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

--DROP VIEW jogadorTotalInfo;
CREATE OR REPLACE VIEW jogadorTotalInfo AS
	SELECT 
		id, 
		estado, 
		email, 
		username, 
		count(DISTINCT p.id_jogo) as totalJogos,
		COUNT(p.id_partida) as totalPartidas,
		SUM(p.pontos) as totalPontos
	FROM Jogadores
		INNER JOIN Pontuacoes p on id=p.id_jogador
	WHERE estado != 'banido'
	GROUP BY jogadores.id;
	
SELECT * FROM jogadorTotalInfo;

-- Exercise M
CREATE OR REPLACE FUNCTION atribuirCrachaAutomatico() RETURNS TRIGGER AS
$$
DECLARE
    pontuacao pontuacoes%ROWTYPE;
    cracha crachas%ROWTYPE;
BEGIN
    FOR pontuacao in SELECT * FROM pontuacoes LOOP
        FOR cracha in SELECT * FROM crachas LOOP
            IF cracha.limite_pontos > pontuacao.pontos then
                INSERT INTO crachas_obtidos(id_jogador, nome_cracha, id_jogo)
                VALUES(pontuacao.id_jogador, cracha.nome, pontuacao.id_jogo);
            END IF;
        END LOOP;
    END LOOP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER cracha_automatico_trigger
    AFTER UPDATE on partidas
    FOR EACH ROW
    EXECUTE PROCEDURE atribuirCrachaAutomatico();
-- Exercise N

CREATE OR REPLACE FUNCTION atualizar_estado_jogador_banido()
RETURNS TRIGGER AS $$
BEGIN
	UPDATE jogadores 
	SET estado = 'banido'
	where id = OLD.id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER banir_jogador 
INSTEAD OF DELETE ON jogadorTotalInfo
FOR EACH ROW
EXECUTE FUNCTION atualizar_estado_jogador_banido();