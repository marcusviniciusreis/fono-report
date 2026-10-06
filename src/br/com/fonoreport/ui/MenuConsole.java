package br.com.fonoreport.ui;

import br.com.fonoreport.model.Paciente;
import br.com.fonoreport.repository.PacienteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private final PacienteRepository repository;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public MenuConsole(PacienteRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void iniciar() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n----MENU----");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Listar paciente");
            System.out.println("3 - Buscar por nome");
            System.out.println("0 - Sair");
            System.out.println("Informe a opção:");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    cadastrar();
                    break;
                case "2":
                    exibirPacientes(repository.listar());
                    break;
                case "3":
                    buscar();
                    break;
                case "0":
                    exec = false;
                    System.out.println("Programa encerrado.");
                default:
                    if (!opcao.equals("0")) {
                        System.out.println("Opcão invalida!");
                    }
                    break;

            }
        }

    }


    public void cadastrar() {
        System.out.println("Nome do paciente: ");
        String nome = scanner.nextLine();

        System.out.println("Data de nascimento do paciente (dd/MM/aaaa): ");
        String dataNasc = scanner.nextLine();

        System.out.println("Nome do responsavel do paciente: ");
        String nomeResp = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate nascimento = LocalDate.parse(dataNasc, formatter);
            repository.salvar(new Paciente(nome, nascimento, nomeResp));
        } catch (DateTimeParseException e) {
            System.out.println("Informe uma data válida no formato dd/MM/aaaa.");
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    public void exibirPacientes(List<Paciente> pacientes) {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente encontrado.");
            return;
        }
        for (Paciente paciente : pacientes) {
            System.out.println("\nNome: " + paciente.getNome());
            System.out.println("Nascimento: " + paciente.getDataNascimento());
            System.out.println("Idade: " + paciente.getIdade() + " anos");
            String responsavel = paciente.getNomeResponsavel();
            System.out.println("Responsável: " + (responsavel.isBlank() ? "Não informado" : responsavel));
        }
    }

    private void buscar() {
        System.out.print("Nome ou parte do nome: ");
        String nome = scanner.nextLine();
        try {
            exibirPacientes(repository.buscarPorNome(nome));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}


