package br.com.fonoreport.app;

import br.com.fonoreport.model.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nascimento = LocalDate.parse("20/02/2005", formatter);
        Paciente paciente = new Paciente("Roberta Vasco", nascimento, "Leonardo Vasco");

        System.out.println("Nome: " + paciente.getNome());
        System.out.println("Nascimento: " + paciente.getDataNascimento());
        System.out.println("Responsavel: "+ paciente.getNomeResponsavel());
    }
}
