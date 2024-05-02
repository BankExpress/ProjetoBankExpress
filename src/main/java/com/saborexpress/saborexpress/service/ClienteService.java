package com.saborexpress.saborexpress.service;

import com.saborexpress.saborexpress.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ClienteService {

    List<Cliente> findAll();

    List<Cliente> findByNome(final String nome);

    Optional<Cliente> findById(final Integer id);

    Cliente save(final Cliente cliente);

    Optional<Cliente> update(final Integer id, final Cliente clienteAtualizado);

    void delete(final Integer id);
}
