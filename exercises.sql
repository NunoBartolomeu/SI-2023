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

-- Exercise J

-- Exercise K

-- Exercise L

-- Exercise M

-- Exercise N
