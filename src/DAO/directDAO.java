package DAO;

import model.direct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// IMPORTANTE: importar a classe model.direct corretamente


public class directDAO {

    private Connection connection;

    public directDAO(Connection connection) {
        this.connection = connection;
    }

    // -------------------------------------------------------------
    // ENVIAR MENSAGEM
    // -------------------------------------------------------------
    public boolean enviarMensagem(direct d) {

        String sql = "INSERT INTO model.direct (remetente_id, destinatario_id, mensagem, data_envio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, d.getRemetenteId());
            stmt.setInt(2, d.getDestinatarioId());
            stmt.setString(3, d.getMensagem());
            stmt.setDate(4, Date.valueOf(d.getDataEnvio()));

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // -------------------------------------------------------------
    // LISTAR MENSAGENS ENTRE DOIS USUÁRIOS
    // -------------------------------------------------------------
    public List<direct> listarMensagens(int usuario1Id, int usuario2Id) {

        List<direct> mensagens = new ArrayList<>();

        String sql = """
            SELECT * FROM model.direct 
            WHERE (remetente_id = ? AND destinatario_id = ?) 
               OR (remetente_id = ? AND destinatario_id = ?)
            ORDER BY data_envio ASC
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, usuario1Id);
            stmt.setInt(2, usuario2Id);
            stmt.setInt(3, usuario2Id);
            stmt.setInt(4, usuario1Id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                direct d = new direct(
                        rs.getInt("id"),
                        rs.getInt("remetente_id"),
                        rs.getInt("destinatario_id"),
                        rs.getString("mensagem"),
                        rs.getDate("data_envio").toLocalDate()
                );
                mensagens.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mensagens;
    }


    // -------------------------------------------------------------
    // SALVAR MENSAGEM (IDÊNTICO a enviarMensagem, mantido por compatibilidade)
    // -------------------------------------------------------------
    public boolean salvar(direct d) {

        String sql = "INSERT INTO model.direct (remetente_id, destinatario_id, mensagem, data_envio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, d.getRemetenteId());
            stmt.setInt(2, d.getDestinatarioId());
            stmt.setString(3, d.getMensagem());
            stmt.setDate(4, Date.valueOf(d.getDataEnvio()));

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
