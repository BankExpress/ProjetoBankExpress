package com.saborexpress.saborexpress.controller;

import com.saborexpress.saborexpress.dto.ClienteDto;
import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.saborexpress.saborexpress.mapper.ClienteMapper.toDto;
import static com.saborexpress.saborexpress.mapper.ClienteMapper.toEntity;
import static java.awt.Container.log;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        return ResponseEntity.ok(toDto(clienteService.findAll()));
    }

    @GetMapping(params = {"desc"})
    public ResponseEntity<List<ClienteDto>> findByDescricao(@RequestParam("desc") final String nome) {
        log.info(nome);
        return ResponseEntity.ok(toDto(clienteService.findByNome(nome)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") final String nome) {
        final ClienteDto cliente = clienteService.findById(nome).orElse(null);
        return ResponseEntity.ok(toDto(cliente));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final ClienteDto clienteDto) {
        clienteService.save(toEntity(clienteDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable("id") final String placa,
                                         @Valid @RequestBody final ClienteDto carroAtualizado) {

        final Optional<Cliente> optionalCar = clienteService.update(placa, toEntity(carroAtualizado));
        if (optionalCar.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String nome) {
        clienteService.delete(nome);
        return ResponseEntity.noContent().build();
    }
}
