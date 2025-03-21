package com.desafio.datahub.api.dto;

import com.desafio.datahub.api.model.Status;

import java.util.Date;

public record TarefaResponseDTO(

        long id,
        String titulo,
        String descricao,
        Date vencimento,
        Status status
) { }
