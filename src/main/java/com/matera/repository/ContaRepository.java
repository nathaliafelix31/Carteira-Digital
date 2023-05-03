package com.matera.repository;

import com.matera.domain .Conta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Long> {

    Optional<Conta> findByAgenciaAndNumero(int agencia, int numero);
}
