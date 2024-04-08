package com.saborexpress.saborexpress.service.serviceImpl;

import com.saborexpress.saborexpress.domain.TipoDeCliente;
import com.saborexpress.saborexpress.exception.ClienteJaExisteException;
import com.saborexpress.saborexpress.mapper.ClienteMapper;
import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.repository.ClienteRepository;
import com.saborexpress.saborexpress.service.ClienteService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;



    @Override
    public List<Cliente> findAll() {

        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findByNome(final String nome) {

        return clienteRepository.findByNome(nome);
    }

    @Override
    public Optional<Cliente> findById(Long id) {

        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(final Cliente entity) {

        if (clienteRepository.findById(entity.getId()).isPresent()) {
            throw new ClienteJaExisteException("O cliente com id " + entity.getId() + " já existe!")
        }
        return clienteRepository.save(entity);
    }

    @Override
    public Optional<Cliente> update(final String nome, final String email, final TipoDeCliente tipoDeCliente, final Cliente clienteAtualizado) {
        final Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            final Cliente entity = clienteOptional.get();
            ClienteMapper.copy(clienteAtualizado, entity);
            clienteRepository.save(entity);
            return Optional.of(clienteAtualizado);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        Optional<Cliente> entity = clienteRepository.findById(id);

        if (entity.isPresent()) {
            clienteRepository.delete(entity.get());
        }
        throw new ClienteJaExisteException("O cliente com id " + id + " não existe!");
    }
}
