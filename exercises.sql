-- Exercise D

-- Exercise E

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

-- Exercise L

-- Exercise M

-- Exercise N
