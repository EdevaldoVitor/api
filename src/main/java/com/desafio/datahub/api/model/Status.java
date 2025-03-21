package com.desafio.datahub.api.model;

import lombok.Getter;

@Getter
public enum Status {

    PENDENTE("Pendente", "Status aguardando tratativa"),
    ANDAMENTO("Em Andamento", "Status em progresso"),
    CONCLUIDO("Concluído", "Status concluído");

    private final String titulo;
    private final String descricao;

    Status(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
