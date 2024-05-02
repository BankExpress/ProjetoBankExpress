package com.saborexpress.saborexpress.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.saborexpress.saborexpress.domain.TipoDeCliente;
import com.saborexpress.saborexpress.model.Cliente;

import com.saborexpress.saborexpress.service.ClienteService;
import com.saborexpress.saborexpress.FixtureClienteTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void deveListarClientes() throws Exception {
        List<Cliente> clientes = FixtureClienteTest.listaDeClientes();
        String clientesJson = mapper.writeValueAsString(clientes);
        when(clienteService.findAll()).thenReturn(clientes);

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().json(clientesJson));
    }
    @Test
    public void deveRetornarNaoEncontradosSeClienteNaoExiste() throws Exception {
        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void deveEncontrarClientePorId() throws Exception {
        Cliente cliente = FixtureClienteTest.cliente();
        String clienteJson = mapper.writeValueAsString(cliente);

        when(clienteService.findById(1)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(clienteJson));
    }
    public static Stream<Arguments> gerarDadosInvalidosParaAlunos() {
        return Stream.of(
                arguments(
                        new Cliente(1, "","", TipoDeCliente.PESSOAFISICA),
                        "nome",
                        "must not be blank"
                ),
                arguments(
                        new Cliente(1, "Gabriel","", null),
                        "cpf",
                        "must not be blank"
                ),
                arguments(
                        new Cliente(1, "Jhenny","", TipoDeCliente.PESSOAJURIDICA),
                        "cpf",
                        "invalid Brazilian individual taxpayer registry number (CPF)"
                )
        );
    }
//    @Test
//    public void deveRetornarListaDeClientes() throws Exception {
//        List<Cliente> clientes = FixtureClienteTest.listaDeClientes();
//        String clienteJson = mapper.writeValueAsString(clientes);
//        when(clienteService.findAll()).thenReturn(clientes);
//
//        mockMvc.perform(get("/cliente"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(clienteJson));
//    }



//    @Test
//    public void deveInserirCliente() throws Exception {
//        Cliente cliente = FixtureClienteTest.cliente();
//        String clienteJson = mapper.writeValueAsString(cliente);
//
//        mockMvc.perform(post("/cliente")
//                .content(clienteJson)
//                .contentType("application/json")
//        )
//                .andExpect(status().isCreated());
//
//        verify(clienteService).save(any(Cliente.class));
//    }

    @ParameterizedTest
    @MethodSource("gerarDadosInvalidosParaClientes")
    public void deveValidarClienteAntesDeInserir(Cliente cliente, String atributo, String erroEsperado) throws Exception {
        String clienteJson = mapper.writeValueAsString(cliente);
        String caminho = "$." + atributo;

        mockMvc.perform(post("/cliente")
                .content(clienteJson)
                .contentType("application/json")
        )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath(caminho).value(erroEsperado));
        verifyNoInteractions(clienteService);
    }

    @Test
    public void deletarCliente() throws Exception {
        mockMvc.perform(delete("/cliente/1"))
                .andExpect(status().isOk());
        verify(clienteService).delete(1);
    }
}