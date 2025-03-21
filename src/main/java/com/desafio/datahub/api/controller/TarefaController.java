package com.desafio.datahub.api.controller;

import com.desafio.datahub.api.dto.TarefaRequestDTO;
import com.desafio.datahub.api.dto.TarefaResponseDTO;
import com.desafio.datahub.api.model.Tarefa;
import com.desafio.datahub.api.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;


    @PostMapping("/criar")
    public ResponseEntity<TarefaResponseDTO> criarTarefa(@RequestBody @Validated TarefaRequestDTO tarefaRequestDTO) {
        Tarefa tarefaCriada = tarefaService.criarTarefa(tarefaRequestDTO);
        TarefaResponseDTO responseDTO = new TarefaResponseDTO(
                tarefaCriada.getId(),
                tarefaCriada.getTitulo(),
                tarefaCriada.getDescricao(),
                tarefaCriada.getVencimento(),
                tarefaCriada.getStatus()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Listar todas as tarefas
    @GetMapping("/listar")
    public ResponseEntity<List<TarefaResponseDTO>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        List<TarefaResponseDTO> responseDTOList = tarefas.stream()
                .map(tarefa -> new TarefaResponseDTO(
                        tarefa.getId(),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getVencimento(),
                        tarefa.getStatus()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }


    // Buscar tarefas por nome de usuário
    @GetMapping("/usuario/{nome}")
    public ResponseEntity<List<TarefaResponseDTO>> buscarTarefasPorNomeUsuario(@PathVariable String nome) {
        List<Tarefa> tarefas = tarefaService.buscarTarefasPorNomeUsuario(nome);
        List<TarefaResponseDTO> responseDTOList = tarefas.stream()
                .map(tarefa -> new TarefaResponseDTO(
                        tarefa.getId(),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getVencimento(),
                        tarefa.getStatus()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }


    // Obter uma tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> obterTarefa(@PathVariable long id) {
        Tarefa tarefa = tarefaService.obterTarefa(id);
        TarefaResponseDTO responseDTO = new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getVencimento(),
                tarefa.getStatus()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable long id, String nome) {
        try {
            tarefaService.deletarTarefa(id, nome);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Você não tem permissão para excluir esta tarefa")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}