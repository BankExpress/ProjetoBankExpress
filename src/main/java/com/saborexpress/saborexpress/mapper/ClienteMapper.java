package com.saborexpress.saborexpress.mapper;

import com.saborexpress.saborexpress.dto.ClienteDto;
import com.saborexpress.saborexpress.model.Cliente;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClienteMapper {
    public static List<ClienteDto> toDto(final List<Cliente> entities) {

//        final List<CarDto> response = new ArrayList<>();
//
//        if (Objects.nonNull(entities)) {  // (entities != null)
//            for(Car entity : entities) {
//                response.add(toDto(entity));
//            }
//        }
//
//        return response;

        return entities.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

    public static ClienteDto toDto(final Cliente entity) {
        if (Objects.nonNull(entity)) {
            return ClienteDto.builder()
                    .nome(entity.getNome())
                    .tipoDeCliente(entity.getTipoDeCliente())
                    .email(entity.getEmail())
                    .build();
        } else {
            return ClienteDto.builder().build();
        }
    }

    public static Cliente toEntity(final ClienteDto dto) {
        return Cliente.builder()
                .nome(dto.getNome())
                .tipoDeCliente(dto.getTipoDeCliente())
                .email(dto.getEmail())
                .build();
    }

    public static void copy(final Cliente source, final Cliente destiny){
        destiny.setId(source.getId());
        destiny.setNome(source.getNome());
        destiny.getEmail(source.getEmail());
        destiny.getTipoDeCliente(source.getTipoDeCliente());
    }
}
