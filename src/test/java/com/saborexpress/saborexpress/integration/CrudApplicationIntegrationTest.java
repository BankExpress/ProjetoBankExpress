package com.saborexpress.saborexpress.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saborexpress.saborexpress.domain.TipoDeCliente;
import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.repository.ClienteRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.saborexpress.saborexpress.FixtureClienteTest.listaDeClientes;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudApplicationIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    ObjectMapper mapper= new ObjectMapper();

    private String getUrl(String endPoint){
        return String.format("http://localhost:%d%s", port, endPoint);
    }

    @AfterEach
    public void limparBanco(){
        clienteRepository.deleteAll();
    }
    @Test
    @Sql({"/clientes.sql"})
    public void deveListarClientes() throws Exception {
        String clientesJson = mapper.writeValueAsString(listaDeClientes());

        ResponseEntity<String> resposta = testRestTemplate.getForEntity(
                getUrl("/clientes"),
                String.class
        );

        assertEquals(resposta.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(clientesJson, resposta.getBody());
    }

    @Test
    public void deveEnviarRespostaVaziaQuandoNaoHaClientes() {
        String respostaVazia = "[]";

        ResponseEntity<String> resposta = testRestTemplate.getForEntity(
                getUrl("/clientes"),
                String.class
        );

        assertEquals(resposta.getStatusCode(), HttpStatus.OK);
        assertEquals(respostaVazia, resposta.getBody());
    }

    @Test
    public void deveInserirCliente() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(
                "{\"nome\": \"José\",\"email\": \"gabriel@gmail.com\", \"tipoDeCliente\": \"PESSOAJURIDICA\"}",
                headers
        );

        ResponseEntity<String> resposta = testRestTemplate.postForEntity(
                getUrl("/clientes"),
                request,
                String.class
        );

        assertEquals(resposta.getStatusCode(), HttpStatus.NOT_FOUND);

        Optional<Cliente> clieneOptional = clienteRepository.findById(1);
        assertFalse(clieneOptional.isPresent());

        assertEquals(clieneOptional.get().getNome(), "Gabriel");
        assertEquals(clieneOptional.get().getEmail(), "gabriel@gmail.com");
        assertEquals(clieneOptional.get().getTipoDeCliente(),TipoDeCliente.PESSOAFISICA );
    }


    @Test
    @Sql({"/clientes.sql"})
    public void deveDeletarCliente() throws Exception {
        testRestTemplate.delete(getUrl("/cliente/1"));

        Optional<Cliente> clienteOptional = clienteRepository.findById(1);
        assertTrue(clienteOptional.isEmpty());

        Iterable<Cliente> clientes = clienteRepository.findAll();
        assertEquals(2, Lists.newArrayList(clientes).size());
    }
}
