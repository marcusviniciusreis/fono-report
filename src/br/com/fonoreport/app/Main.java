package br.com.fonoreport.app;

import br.com.fonoreport.model.Paciente;
import br.com.fonoreport.repository.PacienteRepository;
import br.com.fonoreport.repository.PacienteRepositoryEmMemoria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        PacienteRepository repository = new PacienteRepositoryEmMemoria();
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Nome do paciente: ");
            String nome = scanner.nextLine();

            System.out.println("Data de nascimento do paciente (dd/MM/aaaa): ");
            String dataNasc = scanner.nextLine();

            System.out.println("Nome do responsavel do paciente: ");
            String nomeResp = scanner.nextLine();

            try{
                LocalDate nascimento = LocalDate.parse(dataNasc, formatter);
                Paciente paciente = new Paciente(nome, nascimento, nomeResp);
                repository.salvar(paciente);

                System.out.println("\nPaciente cadastrado!");
                System.out.println("Nome: " + paciente.getNome());
                System.out.println("Nascimento: " + paciente.getDataNascimento());
                System.out.println("Idade: " + paciente.getIdade() + " anos");
                System.out.println("Responsável: " + paciente.getNomeResponsavel());
            }catch (DateTimeParseException e) {
                System.out.println("Informe uma data válida no formato dd/MM/aaaa.");
            } catch (IllegalArgumentException e) {
                System.out.println("Não foi possível cadastrar: " + e.getMessage());
            }
        }
    }
}
