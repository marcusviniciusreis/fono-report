package br.com.fonoreport.model;

import java.time.LocalDate;

public class Atendimento {
    private final Paciente paciente;
    private final LocalDate data;
    private final SituacaoAtendimento situacao;
    private final String observacao;
    private final int anoAtual = LocalDate.now().getYear();

    public Atendimento(Paciente paciente, LocalDate data, SituacaoAtendimento situacao, String observacao) {
        if (paciente == null) {
            throw new IllegalArgumentException("É necessario informar paciente.");
        }

        if (data == null || data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de atendimento inválida.");
        }

        if (data.getYear() < anoAtual) {
            throw new IllegalArgumentException("Data informada invalida.");
        }

        if (situacao == null){
            throw new IllegalArgumentException("A situação é obrigatória.");
        }

        if (observacao == null || observacao.isBlank()) {
            throw new IllegalArgumentException("A observação é obrigatória.");
        }

        this.paciente = paciente;
        this.data = data;
        this.situacao = situacao;
        this.observacao = observacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDate getData() {
        return data;
    }

    public SituacaoAtendimento getSituacao() {
        return situacao;
    }

    public String getObservacao() {
        return observacao;
    }
}
