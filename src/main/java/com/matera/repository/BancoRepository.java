package com.matera.repository;

import com.matera.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Long> {

    Optional<Banco> findByCodigo(int codigo);
}
