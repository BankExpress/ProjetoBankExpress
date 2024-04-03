package com.saborexpress.saborexpress.service.serviceImpl;

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
    public List<Cliente>clienteList findByNome(final String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public Cliente save(final Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> update(final String nome, final Cliente clienteAtualizado) {
        final Optional<Cliente> clienteOptional = findById(nome);

        if (clienteOptional.isPresent()) {
            final Cliente clienteAtualizado = clienteOptional.get();
            clienteAtualizado.setNome(clienteAtualizado.getNome());
            clienteAtualizado.setDocumento(clienteAtualizado.getDocumento());
            clienteAtualizado.setEmail(clienteAtualizado.getEmail());
            clienteRepository.save(clienteAtualizado);
        }

        return clienteOptional;
    }

    public void delete(final String nome  ) {
        final Optional<Cliente> clienteOptional = findById(nome);
        clienteOptional.ifPresent(clienteRepository::delete);
    }
}
