package com.saborexpress.saborexpress.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saborexpress.saborexpress.domain.TipoDeCliente;
import com.saborexpress.saborexpress.model.Cliente;

import com.saborexpress.saborexpress.service.ClienteService;
import com.saborexpress.saborexpress.FixtureClienteTest;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.saborexpress.saborexpress.mapper.ClienteMapper.toDto;
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
        String clientesJson = mapper.writeValueAsString(toDto(clientes)); // Convertendo para DTO
        when(clienteService.findAll()).thenReturn(clientes);

        mockMvc.perform(get("/cliente/todos"))
                .andExpect(status().isOk())
                .andExpect(content().json(clientesJson));
    }

    @Test
    public void deveRetornarNaoEncontradosSeClienteNaoExiste() throws Exception {
        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isNotFound());
    }


    @Test
    void deveEncontrarClientePorId() throws Exception {
        Cliente cliente = FixtureClienteTest.cliente();
        String clienteJson = mapper.writeValueAsString(toDto(cliente));
        when(clienteService.findById(1)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(cliente.getNome())))
                .andExpect(jsonPath("$.email", is(cliente.getEmail())))
                .andExpect(jsonPath("$.tipoDeCliente", is(cliente.getTipoDeCliente().toString())));
    }

    @ParameterizedTest
    @MethodSource("gerarDadosInvalidosParaClientes")
    public void deveValidarClienteAntesDeInserir(Cliente cliente, String atributo, String erroEsperado) throws Exception {
        String clienteJson = mapper.writeValueAsString(cliente);
        String caminho = "$.errors." + atributo;

        mockMvc.perform(post("/cliente")
                        .content(clienteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(caminho).value(erroEsperado));
    }

    public static Stream<Arguments> gerarDadosInvalidosParaClientes() {
        return Stream.of(
                Arguments.of(
                        new Cliente(1, "Israel", "israel@gmail.com", TipoDeCliente.PESSOAFISICA),
                        "nome",
                        "Erro esperado para o campo nome"
                ),
                Arguments.of(
                        new Cliente(2, "Gabriel", "gabriel@gmail.com", TipoDeCliente.PESSOAFISICA),
                        "email",
                        "Erro esperado para o campo email"
                ),
                Arguments.of(
                        new Cliente(3, "Jhenny", "jhen@gmail.com", TipoDeCliente.PESSOAJURIDICA),
                        "tipoDeCliente",
                        "Erro esperado para o campo tipoDeCliente"
                )
        );
    }


    @Test
    public void deletarCliente() throws Exception {
        mockMvc.perform(delete("/cliente/1"))
                .andExpect(status().isNoContent());
        verify(clienteService).delete(1);
    }
}