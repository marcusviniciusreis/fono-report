package br.com.fonoreport.ui;

import br.com.fonoreport.model.Atendimento;
import br.com.fonoreport.model.Paciente;
import br.com.fonoreport.model.SituacaoAtendimento;
import br.com.fonoreport.repository.AtendimentoRepository;
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
    private final AtendimentoRepository atendimentoRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public MenuConsole(PacienteRepository repository, Scanner scanner, AtendimentoRepository atendimentoRepository) {
        this.repository = repository;
        this.scanner = scanner;
        this.atendimentoRepository = atendimentoRepository;
    }

    public void iniciar() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n----MENU----");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Listar paciente");
            System.out.println("3 - Buscar por nome");
            System.out.println("4 - Registrar atendimento");
            System.out.println("5 - Consultar histórico");
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
                case "4":
                    registrarAtendimento();
                    break;
                case "5":
                    consultarHistorico();
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

        System.out.print("Possui encaminhamento médico? (s/n): ");
        String resposta = scanner.nextLine().trim();

        while (!resposta.equalsIgnoreCase("s") && !resposta.equalsIgnoreCase("n")) {
            System.out.print("Digite s ou n: ");
            resposta = scanner.nextLine().trim();
        }

        boolean encaminhamentoMedico = resposta.equalsIgnoreCase("s");
        String nomeMedico = "";
        if (encaminhamentoMedico) {
            System.out.print("Nome do médico (opcional): ");
            nomeMedico = scanner.nextLine();
        }

        System.out.print("Observações gerais do paciente (opcional): ");
        String observacao = scanner.nextLine();

        try {
            LocalDate nascimento = LocalDate.parse(dataNasc.trim(), formatter);
            Paciente paciente = new Paciente(nome, nascimento, nomeResp, encaminhamentoMedico, nomeMedico, observacao);
            repository.salvar(paciente);
            System.out.println("Paciente cadastrado!");
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
            System.out.println("Nascimento: " + paciente.getDataNascimentoFormatada());
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
    private void consultarHistorico() {
        Paciente paciente = selecionarPaciente();
        if (paciente == null) {
            return;
        }

        List<Atendimento> atendimentos = atendimentoRepository.listarAtendimentoPorPaciente(paciente);
        System.out.println("\nHistórico de " + paciente.getNome());
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento registrado.");
            return;
        }
        for (Atendimento atendimento : atendimentos) {
            System.out.println("\nData da sessão: " + atendimento.getData().format(formatter));
            System.out.println("Situação: " + atendimento.getSituacao());
            String observacao = atendimento.getObservacao();
            System.out.println("Observação: " + (observacao.isBlank() ? "Não informada" : observacao));
        }
    }

    private void registrarAtendimento() {
        Paciente paciente = selecionarPaciente();
        if (paciente == null) {
            return;
        }

        System.out.print("Data da sessão (dd/MM/aaaa): ");
        String dataInformada = scanner.nextLine().trim();

        SituacaoAtendimento[] situacoes = SituacaoAtendimento.values();
        for (int i = 0; i < situacoes.length; i++) {
            System.out.println((i + 1) + " - " + situacoes[i]);
        }

        System.out.print("Selecione a situação: ");
        try {
            int numero = Integer.parseInt(scanner.nextLine().trim());
            if (numero < 1 || numero > situacoes.length) {
                System.out.println("Situação inválida.");
                return;
            }

            SituacaoAtendimento situacao = situacoes[numero - 1];

            System.out.print("Observação (evolução obrigatória se realizado): ");
            String observacao = scanner.nextLine();

            LocalDate data = LocalDate.parse(dataInformada, formatter);
            Atendimento atendimento = new Atendimento(
                    paciente, data, situacao, observacao);

            atendimentoRepository.salvar(atendimento);
            System.out.println("Atendimento registrado para " + paciente.getNome());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido para a situação.");
        } catch (DateTimeParseException e) {
            System.out.println("Informe uma data válida no formato dd/MM/aaaa.");
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível registrar: " + e.getMessage());
        }
    }

    private Paciente selecionarPaciente(){
        System.out.println("Nome ou parte do nome:");
        String nome = scanner.nextLine().trim();

        if (nome.isBlank()) {
            System.out.println("Informe um nome para buscar.");
            return null;
        }

        List<Paciente> encontrados = repository.buscarPorNome(nome);
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum paciente encontrado.");
            return null;
        }

        for (int i = 0; i < encontrados.size(); i++) {
            Paciente paciente = encontrados.get(i);
            String responsavel = paciente.getNomeResponsavel();

            System.out.println((i + 1) + " - " + paciente.getNome()
                    + " | Nascimento: " + paciente.getDataNascimentoFormatada()
                    + " | Responsável: " + (responsavel.isBlank() ? "Não informado" : responsavel));
        }
        System.out.print("Selecione o número do paciente (0 para cancelar): ");
        try {
            int numero = Integer.parseInt(scanner.nextLine().trim());
            if (numero == 0) {
                return null;
            }
            if (numero < 1 || numero > encontrados.size()) {
                System.out.println("Opção inválida.");
                return null;
            }
            return encontrados.get(numero - 1);
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            return null;
        }


    }

}


