package com.saborexpress.saborexpress.controller;

import com.saborexpress.saborexpress.model.Cliente;
import com.saborexpress.saborexpress.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll(){
        return  clienteService.findAll();

    }
    @GetMapping("/{id}")
    public Cliente findByAId(@PathVariable("id")final String nome){
        return clienteService.findById(nome).orElseGet(()-> new Cliente());
    }
    @PostMapping
    public void save(@RequestBody @Valid final Cliente cliente){
        clienteService.save(cliente);
    }
    @PutMapping
    public Cliente update(@PathVariable("id")final String nome, @RequestBody @Valid final Cliente clienteAtualizado){
        final var optional = clienteService.update(nome, clienteAtualizado);
        return optional.orElseGet(()->new Cliente());
    }
   @DeleteMapping("/{id}")
    public void delete (@PathVariable("id")final String nome){
        clienteService.delete(nome);
   }
}
