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
AFTER INSERT OR UPDATE ON Pontuacao
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