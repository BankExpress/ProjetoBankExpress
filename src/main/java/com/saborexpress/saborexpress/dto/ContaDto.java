package com.saborexpress.saborexpress.dto;

import com.saborexpress.saborexpress.domain.TipoDeConta;
import com.saborexpress.saborexpress.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {

    @NotBlank(message = "Nome não pode estar vazia")
    private TipoDeConta tipoDeConta;


    @Enumerated(EnumType.ORDINAL)
    private BigDecimal saldo;

    @NotBlank(message = "A Conta não pode estar vazia")
    private Conta conta;


    @NotBlank(message = "Nome da Conta não pode estar vazia")
    private String numeroDaConta;


    @NotBlank(message = "A agencia não pode estar vazia")
    private String agencia;


}
