package com.saborexpress.saborexpress.repository;


import com.saborexpress.saborexpress.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByNome(String nome);
}
