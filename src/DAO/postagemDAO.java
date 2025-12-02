import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class postagemDAO {

    private Connection connection;

    public postagemDAO(Connection connection) {
        this.connection = connection;
    }

    // Salvar nova postagem
    public boolean salvar(postagem postagem) {
        String sql = "INSERT INTO postagem (usuario_id, conteudo, data_postagem) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, postagem.getUsuarioId());
            stmt.setString(2, postagem.getConteudo());
            stmt.setDate(3, Date.valueOf(postagem.getDataPostagem()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            // Recuperar o id gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    postagem.setId(generatedKeys.getInt(1));
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Atualizar postagem existente
    public boolean atualizar(postagem postagem) {
        String sql = "UPDATE postagem SET conteudo = ?, data_postagem = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, postagem.getConteudo());
            stmt.setDate(2, Date.valueOf(postagem.getDataPostagem()));
            stmt.setInt(3, postagem.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deletar postagem pelo id
    public boolean deletar(int id) {
        String sql = "DELETE FROM postagem WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar postagem pelo id
    public postagem buscarPorId(int id) {
        String sql = "SELECT * FROM postagem WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                postagem p = new postagem();
                p.setId(rs.getInt("id"));
                p.setUsuarioId(rs.getInt("usuario_id"));
                p.setConteudo(rs.getString("conteudo"));
                p.setDataPostagem(rs.getDate("data_postagem").toLocalDate());
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Listar postagens de um usuário
    public List<postagem> listarPorUsuario(int usuarioId) {
        List<postagem> lista = new ArrayList<>();

        String sql = "SELECT * FROM postagem WHERE usuario_id = ? ORDER BY data_postagem DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                postagem p = new postagem();
                p.setId(rs.getInt("id"));
                p.setUsuarioId(rs.getInt("usuario_id"));
                p.setConteudo(rs.getString("conteudo"));
                p.setDataPostagem(rs.getDate("data_postagem").toLocalDate());

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
