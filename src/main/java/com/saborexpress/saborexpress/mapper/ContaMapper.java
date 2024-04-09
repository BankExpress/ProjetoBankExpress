package com.saborexpress.saborexpress.mapper;


import com.saborexpress.saborexpress.dto.ContaDto;
import com.saborexpress.saborexpress.model.Conta;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContaMapper {
    public static List<ContaDto> toDtoConta(final List<Conta> contas) {

        return contas.stream()
                .map(entity -> toDtoConta(entity))
                .collect(Collectors.toList());
    }

    public static ContaDto toDtoConta(final Conta conta) {
        if (Objects.nonNull(conta)) {
            return ContaDto.builder()
                    .tipoDeConta(conta.getTipoDeConta())
                    .saldo(conta.getSaldo())
                    .cliente(conta.getCliente())
                    .numeroDaConta(conta.getNumeroDaConta())
                    .agencia(conta.getAgencia())
                    .build();
        } else {
            return ContaDto.builder().build();
        }
    }

    public static Conta toEntityConta(final ContaDto contaDto) {
        return Conta.builder()
                .tipoDeConta(contaDto.getTipoDeConta())
                .saldo(contaDto.getSaldo())
                .cliente(contaDto.getCliente())
                .numeroDaConta(contaDto.getNumeroDaConta())
                .agencia(contaDto.getAgencia())
                .build();
    }
}
