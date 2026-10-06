package br.com.fonoreport.repository;

import br.com.fonoreport.model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PacienteRepositoryEmMemoria implements PacienteRepository {
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void salvar(Paciente paciente) {
        if (paciente == null){
            throw new IllegalArgumentException("O paciente é obrigatorio.");
        }
        pacientes.add(paciente);
    }

    @Override
    public List<Paciente> listar() {
        return List.copyOf(pacientes);
    }

    @Override
    public List<Paciente> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Informe um nome para ser encontrado.");
        }
        String pesquisa = nome.trim().toLowerCase();
        List<Paciente> encontrados = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().toLowerCase().contains(pesquisa)) {
                encontrados.add(paciente);
            }
        }
        return List.copyOf(encontrados);
    }
}
