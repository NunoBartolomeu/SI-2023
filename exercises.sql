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

-- Exercise I

-- Exercise J

-- Exercise K

-- Exercise L

-- Exercise M

-- Exercise N
