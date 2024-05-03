////package com.saborexpress.saborexpress.controller;
////
////public class ProdutoController {
////}
//``` diff--git a/src/main/java/com/saborexpress/saborexpress/controller/ClienteController.java b/src/main/java/com/saborexpress/saborexpress/controller/ClienteController.java index 079c56d..793dad9 100644---a/src/main/java/com/saborexpress/saborexpress/controller/ClienteController.java+++b/src/main/java/com/saborexpress/saborexpress/controller/ClienteController.java@ @ -7,10+7,16@@import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;+import org.springframework.validation.FieldError;+import org.springframework.validation.ObjectError;+import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.*;+import org.springframework.web.server.ResponseStatusException;import javax.validation.Valid;+import java.util.HashMap;import java.util.List;+import java.util.Map;import java.util.Optional;import static com.saborexpress.saborexpress.mapper.ClienteMapper.toDto;@ @ -37,8+43,13@@
//
//public class ClienteController {
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ClienteDto> findById(@PathVariable("id") final Integer id) {
//        final Cliente cliente = clienteService.findById(id).orElse(null);
//         return ResponseEntity.ok(toDto(cliente));
//
//
//
//        final Optional<Cliente> clienteOptional = clienteService.findById(id);
//        if (clienteOptional.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }  return ResponseEntity.ok(toDto(clienteOptional.get()));
//    }
//
//    @PostMapping
//    public class ClienteController { clienteService.delete(id); return ResponseEntity.noContent().build();
//    }
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) +
//    @ExceptionHandler(MethodArgumentNotValidException.class) +
//
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
//        Map < String, String > erros = new HashMap<>();
//         for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
//            +String atributo = ((FieldError) objectError).getField();
//            erros.put(atributo, objectError.getDefaultMessage());
//
//        } return erros;
//
//    }
//}
//public class ClienteControllerTest {
//    @Test
//    void deveListarClientes() throws Exception {
//        List<Cliente> clientes = FixtureClienteTest.listaDeClientes();
//        -String clientesJson = mapper.writeValueAsString(clientes);
//        +List < ClienteDto > clienteDtos = clientes.stream() + .map(ClienteMapper::toDto) + .toList();
//        + +String clientesJson = mapper.writeValueAsString(clienteDtos);
//        when(clienteService.findAll()).thenReturn(clientes);
//        -mockMvc.perform(get("/clientes")) + mockMvc.perform(get("/cliente")).andExpect(status().isOk()).andExpect(content().json(clientesJson));
//    }
//
//
//
//
//    public class ClienteControllerTest {
//        @Test
//        public void deveEncontrarClientePorId() throws Exception {
//            Cliente cliente = FixtureClienteTest.cliente();
//            -String clienteJson = mapper.writeValueAsString(cliente);
//            +String clienteJson = mapper.writeValueAsString(ClienteMapper.toDto(cliente));
//            when(clienteService.findById(1)).thenReturn(Optional.of(cliente));
//            @ @ -68, 17 + 75, 17
//
//
//            @@public class ClienteControllerTest { .
//
//                andExpect(status().
//
//                isOk()).
//
//                andExpect(content().
//
//                json(clienteJson));
//            } - public static Stream<Arguments> gerarDadosInvalidosParaAlunos () {
//                + public static Stream<Arguments> gerarDadosInvalidosParaClientes () {
//                    return Stream.of(arguments(new Cliente(1, "", "", TipoDeCliente.PESSOAFISICA), "nome", -"must not be blank" + "Nome não pode estar vazia"), arguments(new Cliente(1, "Gabriel", "", null), "cpf", -"must not be blank" + "Nome não pode estar vazia"), arguments(new Cliente(1, "Jhenny", "", TipoDeCliente.PESSOAJURIDICA),
//
//
//
//                    @ @ -132, 7 + 139, 7 @@public class ClienteControllerTest {
//
//
//
//                        @Test
//                        public void deletarCliente() throws Exception {
//                            mockMvc.perform(delete("/cliente/1")) - .andExpect(status().isOk());
//                            + .andExpect(status().isNoContent());
//                            verify(clienteService).delete(1);
//                        }
//                    }