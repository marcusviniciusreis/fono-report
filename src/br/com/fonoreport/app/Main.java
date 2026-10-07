package br.com.fonoreport.app;

import br.com.fonoreport.model.Atendimento;
import br.com.fonoreport.model.Paciente;
import br.com.fonoreport.repository.PacienteRepository;
import br.com.fonoreport.repository.PacienteRepositoryEmMemoria;
import br.com.fonoreport.ui.MenuConsole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        PacienteRepository repository = new PacienteRepositoryEmMemoria();
        try (Scanner scanner = new Scanner(System.in)){
            MenuConsole menu = new MenuConsole(repository, scanner);
            menu.iniciar();
        }

    }
}
