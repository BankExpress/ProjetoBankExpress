package com.saborexpress.saborexpress.controller;


import com.saborexpress.saborexpress.dto.ContaDto;
import com.saborexpress.saborexpress.model.Conta;
import com.saborexpress.saborexpress.service.ContaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.saborexpress.saborexpress.mapper.ContaMapper.toDtoConta;
import static com.saborexpress.saborexpress.mapper.ContaMapper.toEntityConta;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<List<ContaDto>> findAll() {
        return ResponseEntity.ok(toDtoConta(contaService.findAll()));
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<ContaDto> findById(@PathVariable("id") final String numeroDaConta) {
        final Conta conta= contaService.findById(numeroDaConta).orElse(null);
        return ResponseEntity.ok(toDtoConta(conta));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final ContaDto contaDto) {
        contaService.save(toEntityConta(contaDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDto> update(@PathVariable("id") final String numeroDaConta,
                                             @Valid @RequestBody final ContaDto contaAtualizado) {

        final Optional<Conta> optionalConta = contaService.update(numeroDaConta, toEntityConta(contaAtualizado));
        if (optionalConta.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String numeroDaConta) {
        contaService.delete(numeroDaConta);
        return ResponseEntity.noContent().build();
    }
}
