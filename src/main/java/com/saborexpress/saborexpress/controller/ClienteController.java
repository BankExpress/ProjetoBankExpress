package com.saborexpress.saborexpress.controller;

import com.saborexpress.saborexpress.dto.ClienteDto;
import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.saborexpress.saborexpress.mapper.ClienteMapper.toDto;
import static com.saborexpress.saborexpress.mapper.ClienteMapper.toEntity;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        return ResponseEntity.ok(toDto(clienteService.findAll()));
    }

    @GetMapping(params = {"nome"})
    public ResponseEntity<List<ClienteDto>> findByNome(@RequestParam("nome") final String nome) {
        log.info(nome);
        return ResponseEntity.ok(toDto(clienteService.findByNome(nome)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") final Long id) {
        final Cliente cliente = clienteService.findById(id).orElse(null);
        return ResponseEntity.ok(toDto(cliente));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final ClienteDto clienteDto) {
        clienteService.save(toEntity(clienteDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable("id") final Long id,
                                         @Valid @RequestBody final ClienteDto clienteAtualizado) {

        final Optional<Cliente> optionalCliente = clienteService.update(id, toEntity(clienteAtualizado));
        if (optionalCliente.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
