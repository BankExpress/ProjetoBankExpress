package com.saborexpress.saborexpress.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.saborexpress.saborexpress.model.Cliente;

import com.saborexpress.saborexpress.service.ClienteService;
import com.saborexpress.saborexpress.FixtureClienteTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

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

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void preparar() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void deveRetornarListaVazia() throws Exception {
        mockMvc.perform(get("/cliente"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    @Test
    public void deveRetornarListaDeClientes() throws Exception {
        List<Cliente> clientes = FixtureClienteTest.listaDeClientes();
        String clienteJson = mapper.writeValueAsString(clientes);
        when(clienteService.findAll()).thenReturn(clientes);

        mockMvc.perform(get("/cliente"))
                .andExpect(status().isOk())
                .andExpect(content().json(clienteJson));
    }

    @Test
    public void deveRetornarNaoEncontradosSeClienteNaoExiste() throws Exception {
        mockMvc.perform(get("/cliente"))
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

    @Test
    public void deveInserirCliente() throws Exception {
        Cliente cliente = FixtureClienteTest.cliente();
        String clienteJson = mapper.writeValueAsString(cliente);

        mockMvc.perform(post("/cliente")
                .content(clienteJson)
                .contentType("application/json")
        )
                .andExpect(status().isCreated());

        verify(clienteService).save(any(Cliente.class));
    }

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