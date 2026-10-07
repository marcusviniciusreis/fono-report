package br.com.fonoreport.app;

import br.com.fonoreport.model.Atendimento;
import br.com.fonoreport.model.Paciente;
import br.com.fonoreport.model.SituacaoAtendimento;
import br.com.fonoreport.repository.AtendimentoRepository;
import br.com.fonoreport.repository.AtendimentoRepositoryEmMemoria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TesteAtendimento {
    static void main(String[] args) {
        AtendimentoRepository repository = new AtendimentoRepositoryEmMemoria();

        Paciente p1 = new Paciente("Marcus Vinicius Reis", LocalDate.of(2015, 5, 10), "Nadia Lasmar", true, "Rodrigo", "Teve passagem por outras fonos");
        Paciente paciente2 = new Paciente("Ana", LocalDate.of(2022, 8, 20), "Carlos", false, "", "");

        repository.salvar(new Atendimento(p1,LocalDate.now(), SituacaoAtendimento.REALIZADO, "Teve melhora na fala"));
        repository.salvar(new Atendimento(paciente2,LocalDate.now(), SituacaoAtendimento.FALTA, "Ausencia da criança"));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        System.out.println("Histórico paciente 1:");
        for (Atendimento atendimento : repository.listarAtendimentoPorPaciente(p1)) {
            System.out.println("Paciente: " + atendimento.getPaciente().getNome());
            System.out.println("Data da sessão: " + atendimento.getData().format(formato));
            System.out.println("Situação: " + atendimento.getSituacao());
            System.out.println("Observação: " + atendimento.getObservacao());
            System.out.println();
        }

        System.out.println("Histórico paciente 2:");
        for (Atendimento atendimento : repository.listarAtendimentoPorPaciente(paciente2)) {
            System.out.println("Paciente: " + atendimento.getPaciente().getNome());
            System.out.println("Data da sessão: " + atendimento.getData().format(formato));
            System.out.println("Situação: " + atendimento.getSituacao());
            System.out.println("Observação: " + atendimento.getObservacao());
            System.out.println();
        }
    }
}
