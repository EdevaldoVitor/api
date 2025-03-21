package com.desafio.datahub.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tarefa")
@Table(name = "tarefa")
public class Tarefa {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(name = "titulo", columnDefinition = "TEXT")
   private String titulo;

   @Column(name = "descricao", columnDefinition = "TEXT")
   private String descricao;

   @Column(name = "vencimento", columnDefinition = "DATE")
   private Date vencimento;

   @Enumerated(EnumType.STRING)
   private Status status;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "usuario_id", nullable = false)
   private User usuario;

}
