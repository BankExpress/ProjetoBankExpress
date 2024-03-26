package com.saborexpress.saborexpress.service;

import com.saborexpress.saborexpress.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> findAll();

    Optional<Cliente> findByNome(final String nome);

    Optional<Cliente> findById(final String nome);

    Cliente save(final Cliente cliente);

    Optional<Cliente> update(final String nome, final Cliente clienteAtualizado);

    void delete(final String nome);
}
