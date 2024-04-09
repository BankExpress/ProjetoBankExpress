package com.saborexpress.saborexpress.service;

import com.saborexpress.saborexpress.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface  ClienteService {

    List<Cliente> findAll();

    List<Cliente> findByNome(final String nome);

    Optional<Cliente> findById(final Long id);

    Cliente save(final Cliente cliente);

    Optional<Cliente> update(final Long id, final Cliente clienteAtualizado);

    void delete(final Long id);
}
