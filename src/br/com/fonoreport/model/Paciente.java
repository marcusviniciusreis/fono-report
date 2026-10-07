package br.com.fonoreport.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Paciente {
    private final String nome;
    private final LocalDate dataNascimento;
    private final String nomeResponsavel;
    private final boolean encaminhamentoMedico;
    private final String nomeMedico;
    private final String observacao;


    public Paciente(String nome, LocalDate dataNascimento, String nomeResponsavel, boolean encaminhamentoMedico, String nomeMedico, String observacao) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }
        this.nome = nome.trim();
        this.dataNascimento = dataNascimento;
        this.nomeResponsavel = nomeResponsavel == null ? "" : nomeResponsavel.trim();
        this.observacao = observacao == null ? "" : observacao.trim();
        this.encaminhamentoMedico = encaminhamentoMedico;
        this.nomeMedico = encaminhamentoMedico && nomeMedico != null ? nomeMedico.trim() : "";

    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getDataNascimentoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return dataNascimento.format(formatter);
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public boolean isEncaminhamentoMedico() {
        return encaminhamentoMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getObservacao() {
        return observacao;
    }


}
