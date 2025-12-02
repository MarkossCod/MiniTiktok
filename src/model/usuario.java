package model;

import java.time.LocalDate;

public class usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataCadastro;

    public usuario() {
        this.dataCadastro = LocalDate.now();
    }

    public usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDate.now();
    }


    public void cadastrarUsuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDate.now();
    }


    public void atualizarUsuario(String nome, String email, String senha) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        if (senha != null && !senha.isEmpty()) {
            this.senha = senha;
        }
    }

    public void excluirUsuario() {
        this.id = 0;
        this.nome = null;
        this.email = null;
        this.senha = null;
        this.dataCadastro = null;
    }

    public void exibirInformacoes() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Data de Cadastro: " + dataCadastro);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}