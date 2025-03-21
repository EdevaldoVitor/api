package com.desafio.datahub.api.repository;

import com.desafio.datahub.api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuarioNome(String nome);
}
