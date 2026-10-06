package br.com.fonoreport.repository;

import br.com.fonoreport.model.Paciente;

import java.util.List;

public interface PacienteRepository {
        void salvar(Paciente paciente);
        List<Paciente> listar();
        List<Paciente> buscarPorNome(String nome);
}
