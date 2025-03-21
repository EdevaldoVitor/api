package com.desafio.datahub.api.service;

import com.desafio.datahub.api.dto.TarefaRequestDTO;
import com.desafio.datahub.api.model.Tarefa;
import com.desafio.datahub.api.model.User;
import com.desafio.datahub.api.repository.TarefaRepository;
import com.desafio.datahub.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Tarefa criarTarefa(TarefaRequestDTO tarefaRequestDTO) {
        User usuario = userRepository.findById(tarefaRequestDTO.usuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaRequestDTO.titulo());
        tarefa.setDescricao(tarefaRequestDTO.descricao());
        tarefa.setVencimento(tarefaRequestDTO.vencimento());
        tarefa.setStatus(tarefaRequestDTO.status());
        tarefa.setUsuario(usuario);

        return tarefaRepository.save(tarefa);
    }


    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> buscarTarefasPorNomeUsuario(String nome) {
        return tarefaRepository.findByUsuarioNome(nome);
    }


    public Tarefa obterTarefa(long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    @Transactional
    public Tarefa atualizarTarefa(long id, TarefaRequestDTO tarefaRequestDTO) {
        Tarefa tarefaExistente = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        User usuario = userRepository.findById(tarefaRequestDTO.usuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        tarefaExistente.setTitulo(tarefaRequestDTO.titulo());
        tarefaExistente.setDescricao(tarefaRequestDTO.descricao());
        tarefaExistente.setVencimento(tarefaRequestDTO.vencimento());
        tarefaExistente.setStatus(tarefaRequestDTO.status());
        tarefaExistente.setUsuario(usuario);

        return tarefaRepository.save(tarefaExistente);
    }

    @Transactional
    public boolean deletarTarefa(long id, String nome) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!tarefa.getUsuario().getNome().equals(nome)) {
            throw new RuntimeException("A tarefa não pertence ao usuário.");
        }
        tarefaRepository.deleteById(id);
        return true;
    }

}
