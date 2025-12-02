package model;

import java.time.LocalDate;

public class curtida {

    private int id;
    private int remetenteId;
    private int destinatarioId;
    private LocalDate dataCurtida;

    // Contador estático de curtidas
    private static int totalCurtidas = 0;

    public curtida(int id, int remetenteId, int destinatarioId) {
        this.id = id;
        this.remetenteId = remetenteId;
        this.destinatarioId = destinatarioId;
        this.dataCurtida = LocalDate.now();
        totalCurtidas++;
    }

    // Registrar nova model.curtida
    public void curtir(int id, int remetenteId, int destinatarioId) {
        this.id = id;
        this.remetenteId = remetenteId;
        this.destinatarioId = destinatarioId;
        this.dataCurtida = LocalDate.now();
        totalCurtidas++;
    }

    // Remover model.curtida
    public void descurtir() {
        this.id = 0;
        this.remetenteId = 0;
        this.destinatarioId = 0;
        this.dataCurtida = null;

        if (totalCurtidas > 0) {
            totalCurtidas--;
        }
    }

    public static int contarCurtidas() {
        return totalCurtidas;
    }

    // -----------------------------
    //     GETTERS E SETTERS
    // -----------------------------
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

    public LocalDate getDataCurtida() {
        return dataCurtida;
    }

    public void setDataCurtida(LocalDate dataCurtida) {
        this.dataCurtida = dataCurtida;
    }
}