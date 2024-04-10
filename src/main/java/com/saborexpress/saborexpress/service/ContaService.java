package com.saborexpress.saborexpress.service;

import com.saborexpress.saborexpress.model.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaService {
    List<Conta> findAll();

    Optional<Conta> findById(String numeroDaConta);

    Conta save(final Conta conta);

    Optional<Conta> update(final String numeroDaConta, final Conta contaAtualizado);

    void delete(final String numeroDaConta);
}
