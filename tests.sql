-- Exercise D

-- Exercise E

-- Exercise F

DO
LANGUAGE plpgsql 
$$
DECLARE
    id_jogador INT;
    resultado INT;
BEGIN
    raise notice 'Testing F';
    ROLLBACK;
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    -- Criar um jogador
    INSERT INTO Jogadores (username, email, regiao) VALUES ('TestUser', 'testuser@gmail.com', 'Portugal');
    SELECT id INTO id_jogador FROM Jogadores WHERE username = 'TestUser';
    -- Criar um jogo
    INSERT INTO Jogos (id, nome, url) VALUES ('0123456789', 'TestGame00', 'www.testgame.com');
    -- Criar 5 partidas
    INSERT INTO Partidas (id, id_jogo, data_inicio, data_fim, regiao) VALUES (1, '0123456789', '2017-01-01 12:31:59', '2017-01-01 12:31:59', 'Portugal');
    -- Criar as 5 pontuações
    INSERT INTO Pontuacoes (id_partida, id_jogo, id_jogador, pontos) VALUES (1, '0123456789', id_jogador, 100);

    -- Chamar a função totalJogosJogador e verificar o resultado
    SELECT totalJogosJogador(id_jogador) INTO resultado;
    IF resultado = 1 THEN
        RAISE NOTICE 'Teste F passou!';
    ELSE
        RAISE NOTICE 'Teste F falhou!';
    END IF;

    ROLLBACK;
END; $$;

-- Exercise G

-- Exercise H

-- Exercise I

DO
LANGUAGE plpgsql
$$
#variable_conflict use_variable
DECLARE
    id_jogador INT;
    id_conversa INT;
BEGIN
    raise notice 'Testing I';
    ROLLBACK;
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    -- Criar um jogador
    INSERT INTO Jogadores (username, email, regiao) VALUES ('TestUser', 'testuser@gmail.com', 'Portugal');
    SELECT id INTO id_jogador FROM Jogadores WHERE username = 'TestUser';
    
    -- Chamar a procedure iniciarConversa e verificar o resultado
    CALL iniciarConversa(id_jogador, 'Conversa Test', id_conversa);
    -- Verificar se a conversa foi criada vendo se existe a mensagem de boas vindas
    IF EXISTS (SELECT * FROM Mensagens m WHERE m.id_conversa = id_conversa AND texto = 'TestUser criou esta conversa') THEN
        RAISE NOTICE 'Teste I passou!';
    ELSE
        RAISE NOTICE 'Teste I falhou!';
    END IF;
	
    ROLLBACK;
END; $$;

-- Exercise J

-- Exercise K

-- Exercise L

-- Exercise M

-- Exercise N
