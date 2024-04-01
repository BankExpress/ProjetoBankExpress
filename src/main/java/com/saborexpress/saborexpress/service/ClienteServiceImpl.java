package com.saborexpress.saborexpress.service;

import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ClienteServiceImpl implements ClienteService {
    private List<Cliente> clienteList = new ArrayList<>();

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findByNome(final String nome) {
        final Optional<Cliente> clienteOptional = clienteList.stream()
                .filter(cliente -> cliente.getNome().equals(nome))
                .findFirst();
        return clienteOptional;
    }

    @Override
    public Optional<Cliente> findById(String nome) {
        return Optional.empty();
    }

    @Override
    public Cliente save(final Cliente cliente) {
        clienteList.add(cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> update(String nome, Cliente clienteAtualizado) {
        final Optional<Cliente> clienteOptional= findById(nome);
        if (clienteOptional.isPresent()){
            final Cliente clienteEncotrado = clienteOptional.get();
            clienteEncotrado.setNome(clienteEncotrado.getNome());
            clienteEncotrado.setEmail(clienteEncotrado.getEmail());
            clienteEncotrado.setDocumento(clienteAtualizado.getDocumento());
        }

        return Optional.empty();
    }

    @Override
    public void delete(final String nome) {
        findById(nome).ifPresent(cliente -> clienteList.remove(cliente));
    }
}
