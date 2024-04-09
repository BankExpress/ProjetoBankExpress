package com.saborexpress.saborexpress.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.saborexpress.saborexpress.domain.TipoDeCliente;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClienteDto {


    @NotBlank(message = "Nome não pode estar vazia")
    private String nome;

    @NotBlank(message = "Email não pode estar vazia")
    @Size(min = 5)
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private TipoDeCliente tipoDeCliente;

//    @NotNull
//    private List<ContaDto> contas;
}
