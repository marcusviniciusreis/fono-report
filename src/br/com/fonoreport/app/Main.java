package br.com.fonoreport.app;

import br.com.fonoreport.model.Atendimento;
import br.com.fonoreport.model.Paciente;
import br.com.fonoreport.repository.AtendimentoRepository;
import br.com.fonoreport.repository.AtendimentoRepositoryEmMemoria;
import br.com.fonoreport.repository.PacienteRepository;
import br.com.fonoreport.repository.PacienteRepositoryEmMemoria;
import br.com.fonoreport.ui.MenuConsole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PacienteRepository pacientes = new PacienteRepositoryEmMemoria();
        AtendimentoRepository atendimentos = new AtendimentoRepositoryEmMemoria();

        try (Scanner scanner = new Scanner(System.in)) {
            MenuConsole menu = new MenuConsole(pacientes, scanner, atendimentos);
            menu.iniciar();
        }
    }
}
