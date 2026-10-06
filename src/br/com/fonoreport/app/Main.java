package br.com.fonoreport.app;

import br.com.fonoreport.repository.PacienteRepository;
import br.com.fonoreport.repository.PacienteRepositoryEmMemoria;
import br.com.fonoreport.ui.MenuConsole;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        PacienteRepository repository = new PacienteRepositoryEmMemoria();
        try (Scanner scanner = new Scanner(System.in)){
            MenuConsole menu = new MenuConsole(repository, scanner);
            menu.iniciar();
        }

    }
}
