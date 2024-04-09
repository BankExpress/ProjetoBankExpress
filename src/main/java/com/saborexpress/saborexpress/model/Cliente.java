package com.saborexpress.saborexpress.model;


import com.saborexpress.saborexpress.domain.TipoDeCliente;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cliente")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id

    private Long id;

    @NotNull(message = "O Atributo Nome é Obrigatório!")
    private String nome;

    @Column(length = 100)
    @NotNull(message = "O Atributo Email é Obrigatório!")
    private String email;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDeCliente tipoDeCliente;



}
