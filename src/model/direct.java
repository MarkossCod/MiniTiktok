package model;

import java.time.LocalDate;

public class direct {

    private int id;
    private int remetenteId;
    private int destinatarioId;
    private String mensagem;
    private LocalDate dataEnvio;

    // Construtor vazio
    public direct() {}

    // Construtor completo
    public direct(int id, int remetenteId, int destinatarioId, String mensagem, LocalDate dataEnvio) {
        this.id = id;
        this.remetenteId = remetenteId;
        this.destinatarioId = destinatarioId;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
    }

    // -------------------------
    // GETTERS E SETTERS
    // -------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(int remetenteId) {
        this.remetenteId = remetenteId;
    }

    public int getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(int destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public String toString() {
        return "Direct{" +
                "id=" + id +
                ", remetenteId=" + remetenteId +
                ", destinatarioId=" + destinatarioId +
                ", mensagem='" + mensagem + '\'' +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}
