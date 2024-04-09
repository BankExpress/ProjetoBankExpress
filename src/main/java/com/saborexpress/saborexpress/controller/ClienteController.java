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
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDto> findAll() {

        return toDto(clienteService.findAll());
    }

    @GetMapping(params = {"nome"})
    public ResponseEntity<ClienteDto> findByNome(@RequestParam("nome") final String nome) {
        log.info(nome);
        return ResponseEntity.ok(toDto(clienteService.findByNome(nome)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") final Long id) {
        Optional<Cliente> cliente=clienteService.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(toDto(cliente.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final ClienteDto clienteDto) {
        clienteService.save(toEntity(clienteDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable("id") final String id,
                                         @Valid @RequestBody final ClienteDto dto) {

        final var cliente = clienteService.update(dto.getNome(), dto.getEmail(),dto.getTipoDeCliente(),toEntity(dto));

        if (cliente.isPresent()){
            return ResponseEntity.ok(toDto(cliente.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
