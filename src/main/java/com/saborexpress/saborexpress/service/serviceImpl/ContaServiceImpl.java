package com.saborexpress.saborexpress.service.serviceImpl;


import com.saborexpress.saborexpress.exception.ContaJaExisteException;
import com.saborexpress.saborexpress.model.Conta;
import com.saborexpress.saborexpress.repository.ContaRepository;
import com.saborexpress.saborexpress.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;


    @Override
    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    @Override
    public Optional<Conta> findById(String numeroDaConta) {

        return contaRepository.findById(numeroDaConta);
    }

    @Override
    public Conta save(final Conta conta) {

        if (contaRepository.findById(conta.getNumeroDaConta()).isEmpty()) {
            throw new ContaJaExisteException("O Conta com id " + conta.getNumeroDaConta() + " já existe!");
        }
        return contaRepository.save(conta);
    }

    @Override
    public Optional<Conta> update(final String numeroDaConta, final Conta ContaAtualizado) {
        final Optional<Conta> ContaOptional = contaRepository.findById(numeroDaConta);
        if (ContaOptional.isPresent()) {
            final Conta ContaEncontrado = ContaOptional.get();
            ContaEncontrado.setId(ContaAtualizado.getId());
            ContaEncontrado.setTipoDeConta(ContaAtualizado.getTipoDeConta());
            ContaEncontrado.setSaldo(ContaAtualizado.getSaldo());
            ContaEncontrado.setCliente(ContaAtualizado.getCliente());
            ContaEncontrado.setNumeroDaConta(ContaAtualizado.getNumeroDaConta());
            ContaEncontrado.setAgencia(ContaAtualizado.getAgencia());
            contaRepository.save(ContaEncontrado);
            return Optional.of(ContaAtualizado);
        }
        return ContaOptional;
    }

    @Override
    public void delete(String numeroDaConta) {
        Optional<Conta> conta = contaRepository.findById(numeroDaConta);

        if (conta.isPresent()) {
            contaRepository.delete(conta.get());
        }
        throw new ContaJaExisteException("O Conta com id " + numeroDaConta + " não existe!");
    }
}
