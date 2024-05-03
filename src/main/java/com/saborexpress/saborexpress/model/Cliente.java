package com.saborexpress.saborexpress.model;


import com.saborexpress.saborexpress.domain.TipoDeCliente;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;



@Table(name = "Cliente")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotNull(message = "O Atributo Nome é Obrigatório!")
    @NotBlank
    private String nome;
//
//    @Column(length = 100)
//    @NotNull(message = "O Atributo Email é Obrigatório!")
    @NotBlank
    private String email;


//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)

    private TipoDeCliente tipoDeCliente;



}
