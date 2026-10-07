package br.com.fonoreport.repository;

import br.com.fonoreport.model.Atendimento;
import br.com.fonoreport.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class AtendimentoRepositoryEmMemoria implements AtendimentoRepository{
    private final List<Atendimento> atendimentos = new ArrayList<>();
    @Override
    public void salvar(Atendimento atendimento) {
        if (atendimento == null){
            throw new IllegalArgumentException("O atendimento é obrigatório.");
        }
        atendimentos.add(atendimento);
    }

    @Override
    public List<Atendimento> listarAtendimentoPorPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Selecione um paciente.");
        }
        List<Atendimento> encontrados = new ArrayList<>();
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getPaciente() == paciente) {
                encontrados.add(atendimento);
            }
        }
        return List.copyOf(encontrados);
    }
}
