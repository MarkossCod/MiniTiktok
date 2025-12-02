package DAO;

import model.usuario;

import java.util.List;

public interface usuarioDAO {

    void salvar(usuario usuario);
    void atualizar(usuario usuario);
    void deletar(int id);
    usuario buscarPorId(int id);
    List<usuario> listarTodos();
}