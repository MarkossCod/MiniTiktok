package DAO;

import java.sql.*;
import java.time.LocalDate;

public class curtidaDAO {

    private Connection connection;

    public curtidaDAO(Connection connection) {
        this.connection = connection;
    }

    // ---------------------------------------------------------
    // CURTIR UMA POSTAGEM
    // ---------------------------------------------------------
    public boolean curtir(int usuarioId, int postagemId) {

        // Verifica se já curtiu
        if (verificarSeJaCurtiu(usuarioId, postagemId)) {
            return false; // já existe model.curtida
        }

        String sql = "INSERT INTO model.curtida (remetente_id, destinatario_id, data_curtida) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, postagemId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // ---------------------------------------------------------
    // REMOVER CURTIDA
    // ---------------------------------------------------------
    public boolean descurtir(int usuarioId, int postagemId) {

        String sql = "DELETE FROM model.curtida WHERE remetente_id = ? AND destinatario_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, postagemId);

            int rows = stmt.executeUpdate();
            return rows > 0; // true se deletou ao menos 1 linha

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // ---------------------------------------------------------
    // VERIFICAR SE O USUÁRIO JÁ CURTIU A POSTAGEM
    // ---------------------------------------------------------
    public boolean verificarSeJaCurtiu(int usuarioId, int postagemId) {

        String sql = "SELECT id FROM model.curtida WHERE remetente_id = ? AND destinatario_id = ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, postagemId);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // existe uma model.curtida

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // ---------------------------------------------------------
    // CONTAR CURTIDAS DE UMA POSTAGEM
    // ---------------------------------------------------------
    public int contarCurtidas(int postagemId) {

        String sql = "SELECT COUNT(*) FROM model.curtida WHERE destinatario_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, postagemId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

