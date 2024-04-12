package com.saborexpress.saborexpress.model;

import com.saborexpress.saborexpress.domain.TipoDeConta;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDeConta tipoDeConta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(length = 10)
    @NotNull(message = "O numero da conta não deve ser nula!")
    private String numeroDaConta;

    @Column(length = 5)
    @NotNull(message = "A agencia da conta do Cliente não deve ser nula!")
    private String agencia;
}
