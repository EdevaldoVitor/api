package com.desafio.datahub.api.dto;

import com.desafio.datahub.api.model.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record TarefaRequestDTO(

        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "A data de vencimento é obrigatória")
        Date vencimento,

        @NotNull(message = "O status é obrigatório")
        Status status,

        @NotNull(message = "O ID do usuário é obrigatório")
        String usuario
) { }
