package com.saborexpress.saborexpress.repository;

import com.saborexpress.saborexpress.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findById(String numeroDaConta);

}
