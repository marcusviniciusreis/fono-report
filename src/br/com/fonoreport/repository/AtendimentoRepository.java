package br.com.fonoreport.repository;

import br.com.fonoreport.model.Atendimento;
import br.com.fonoreport.model.Paciente;

import java.util.List;

public interface AtendimentoRepository {
    void salvar(Atendimento atendimento);
    List<Atendimento> listarAtendimentoPorPaciente(Paciente paciente);
}
