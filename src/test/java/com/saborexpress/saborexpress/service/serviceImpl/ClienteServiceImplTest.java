package com.saborexpress.saborexpress.service.serviceImpl;

import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.saborexpress.saborexpress.FixtureClienteTest.cliente;
import static com.saborexpress.saborexpress.FixtureClienteTest.listaDeClientes;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {

    private ClienteRepository clienteRepository;
    private ClienteServiceImpl clienteServiceImpl;

    @BeforeEach
    public void preparar() {
        clienteRepository = Mockito.mock(ClienteRepository.class);
        clienteServiceImpl = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    public void deveListarClientes() {
        List<Cliente> clientesEsperados = listaDeClientes();
        when(clienteRepository.findAll()).thenReturn(clientesEsperados);

        Iterable<Cliente> clientes = clienteServiceImpl.findAll();

        assertEquals(clientesEsperados, clientes);
    }

    public static Stream<Arguments> gerarClienteOptional() {
        return Stream.of(
                arguments(Optional.of(cliente())),
                arguments(Optional.empty())
        );
    }


    @Test
    void findByNome() {
    }

    @ParameterizedTest
    @MethodSource("gerarClienteOptional")
    public void deveBuscarClientePorId(Optional<Cliente> retornoEsperado) {
        when(clienteRepository.findById(1)).thenReturn(retornoEsperado);

        Optional<Cliente> clienteOptional = clienteServiceImpl.findById(1);

        assertEquals(retornoEsperado, clienteOptional);
        verify(clienteRepository).findById(1);
    }

    @Test
    void update() {
    }


    @Test
    public void deveInserirCliente() {
        Cliente cliente = cliente();
        clienteServiceImpl.save(cliente);

        verify(clienteRepository).save(cliente);
    }

    @Test
    public void deletarUmCliente() {
        Cliente cliente = cliente();

        clienteServiceImpl.delete(1);
        verify(clienteRepository).findById(1);

    }
}