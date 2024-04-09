//package com.saborexpress.saborexpress.model;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "Produto")
//@Builder
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class Produto {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull(message = "O Atributo Nome do Produto é Obrigatório!")
//    private String nomeProduto;
//
//    @Column(length = 100)
//    private BigDecimal preco;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private int quantidade;
//}
