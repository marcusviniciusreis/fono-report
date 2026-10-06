package br.com.fonoreport.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Paciente {
    private final String nome;
    private final LocalDate dataNascimento;
    private final String nomeResponsavel;

    public Paciente(String nome, LocalDate dataNascimento, String nomeResponsavel) {
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }
        this.nome = nome.trim();
        this.dataNascimento = dataNascimento;
        this.nomeResponsavel = nomeResponsavel == null ? "" : nomeResponsavel.trim();
    }

    public int getIdade(){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatter);
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
}
