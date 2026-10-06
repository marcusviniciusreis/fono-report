package br.com.fonoreport.app;

import br.com.fonoreport.model.Paciente;
import br.com.fonoreport.repository.PacienteRepository;
import br.com.fonoreport.repository.PacienteRepositoryEmMemoria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        PacienteRepository repository = new PacienteRepositoryEmMemoria();
        repository.salvar(new Paciente("Marcus Vinicius Reis", LocalDate.parse("07/02/2004", formatter), "Nadia Lasmar"));
        repository.salvar(new Paciente("Susan Ramos Lima", LocalDate.parse("07/03/2004", formatter), "Kelly Regina"));
        List<Paciente> encontrados = repository.buscarPorNome("marcus ");
        if (encontrados.isEmpty()){
            System.out.println("Nenhum paciente encontrado");
        }
        for (Paciente paciente : encontrados){
            System.out.println("Nome: " + paciente.getNome());
            System.out.println("Idade: " + paciente.getIdade());
            System.out.println("Data de nascimento: " + paciente.getDataNascimento());
            System.out.println("Responsavel: " + paciente.getNomeResponsavel());
            System.out.println();
        }
    }
}
