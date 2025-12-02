package model;

import java.time.LocalDate;

public class postagem {

    private int id;
    private int usuarioId;
    private String conteudo;
    private LocalDate dataPostagem;

    // Construtor padrão
    public postagem() {
        this.dataPostagem = LocalDate.now();
    }

    public postagem(int id, int usuarioId, String conteudo) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.conteudo = conteudo;
        this.dataPostagem = LocalDate.now();
    }

    public void criarPostagem(int id, int usuarioId, String conteudo) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.conteudo = conteudo;
        this.dataPostagem = LocalDate.now();
    }

    public void atualizarPostagem(String novoConteudo) {
        if (novoConteudo != null && !novoConteudo.isEmpty()) {
            this.conteudo = novoConteudo;
        }
    }

    public void excluirPostagem() {
        this.id = 0;
        this.usuarioId = 0;
        this.conteudo = null;
        this.dataPostagem = null;
    }

    // Exibir model.postagem
    public void exibirPostagem() {
        System.out.println("ID da Postagem: " + id);
        System.out.println("ID do Usuário: " + usuarioId);
        System.out.println("Conteúdo: " + conteudo);
        System.out.println("Data da Postagem: " + dataPostagem);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }
}