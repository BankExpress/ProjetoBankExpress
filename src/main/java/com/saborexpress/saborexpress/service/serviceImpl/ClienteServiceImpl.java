package com.saborexpress.saborexpress.service.serviceImpl;

import com.saborexpress.saborexpress.exception.ClienteJaExisteException;
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
    public List<Cliente> findByNome(final String nome) {

        return clienteRepository.findByNome(nome);
    }

    @Override
    public Optional<Cliente> findById(Long id) {

        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(final Cliente entity) {

        if (clienteRepository.findById(entity.getId()).isPresent()) {
            throw new ClienteJaExisteException("O cliente com id " + entity.getId() + " já existe!");
        }
        return clienteRepository.save(entity);
    }

    @Override
    public Optional<Cliente> update(final Long id, final Cliente clienteAtualizado) {
        final Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            final Cliente clienteEncontrado = clienteOptional.get();
          clienteEncontrado.setId(clienteAtualizado.getId());
          clienteEncontrado.setNome(clienteAtualizado.getNome());
          clienteEncontrado.setEmail(clienteAtualizado.getEmail());
          clienteEncontrado.setTipoDeCliente(clienteAtualizado.getTipoDeCliente());
            clienteRepository.save(clienteEncontrado);
            return Optional.of(clienteAtualizado);
        }
        return clienteOptional;
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
