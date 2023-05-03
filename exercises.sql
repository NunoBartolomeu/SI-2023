-- Exercise D

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

-- Exercise F

--DROP FUNCTION totalJogosJogador;
CREATE OR REPLACE FUNCTION totalJogosJogador(jogador INT)     
RETURNS INT
LANGUAGE plpgsql
AS $$    
DECLARE    
    total INT;    
BEGIN
    SELECT count(DISTINCT id_jogo) INTO total FROM Pontuacao p WHERE jogador = p.id_jogador;
    RETURN (total);    
END;   
$$;

SELECT totalJogosJogador(4);

-- Exercise G

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
    FROM Pontuacao
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
	SELECT nome_jogador || 'criou esta conversa' INTO mensagem_criacao;
	INSERT INTO mensagens (id_conversa, id_jogador, texto) VALUES (id_conversa, id_jogador, mensagem_criacao);
END;
$$;

DO 
$$
DECLARE
	result int;
BEGIN
	CALL iniciarConversa(1, 'Nova Conversa', result);
	RAISE NOTICE '%', result;
END;
$$;

-- Exercise J

-- Exercise K

CREATE OR REPLACE PROCEDURE enviarMensagem(jogador_id INT, conversa_id INT, mensagem_texto VARCHAR(255)) AS $$
BEGIN
    INSERT INTO Mensagens (id_conversa, id_jogador, texto, data)
    VALUES (conversa_id, jogador_id, mensagem_texto);
END;
$$ LANGUAGE plpgsql;

-- Exercise L

-- Exercise M

-- Exercise N
