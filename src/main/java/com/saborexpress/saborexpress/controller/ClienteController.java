package com.saborexpress.saborexpress.controller;

import com.saborexpress.saborexpress.dto.ClienteDto;
import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.saborexpress.saborexpress.mapper.ClienteMapper.toDto;
import static com.saborexpress.saborexpress.mapper.ClienteMapper.toEntity;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDto>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(toDto(clientes));
    }

    @GetMapping(params = {"nome"})
    public ResponseEntity<List<ClienteDto>> findByNome(@RequestParam("nome") final String nome) {
        log.info(nome);
        return ResponseEntity.ok(toDto(clienteService.findByNome(nome)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") final Integer id) {
        final Optional<Cliente> optionalCliente = clienteService.findById(id);
        if (optionalCliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(optionalCliente.get()));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody final ClienteDto clienteDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        } else {
            clienteService.save(toEntity(clienteDto));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable("id") final Integer id,
                                         @Valid @RequestBody final ClienteDto clienteAtualizado) {

        final Optional<Cliente> optionalCliente = clienteService.update(id, toEntity(clienteAtualizado));
        if (optionalCliente.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
