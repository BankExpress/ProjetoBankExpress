package com.saborexpress.saborexpress.service;

import com.saborexpress.saborexpress.domain.TipoDeCliente;
import com.saborexpress.saborexpress.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface  ClienteService {

    List<Cliente> findAll();

    Optional<Cliente> findByNome(final String nome);

    Optional<Cliente> findById(final Long id);

    Cliente save(final Cliente cliente);

    Optional<Cliente> update(final String nome, final String email, final TipoDeCliente tipoDeCliente, final Cliente clienteAtualizado);

    void delete(final String nome);
}
