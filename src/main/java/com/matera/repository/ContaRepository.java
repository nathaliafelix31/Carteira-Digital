package com.matera.repository;

import com.matera.domain .Conta;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByAgenciaAndNumero(int agencia, int numero);

    Optional<Conta> findByPix(String chavePix);
}
