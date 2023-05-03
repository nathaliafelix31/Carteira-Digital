package com.matera.repository;

import com.matera.domain.TipoTarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTarifaRepository extends JpaRepository<TipoTarifa, Long> {

    TipoTarifa nome(String nome);
    TipoTarifa findByNome(String nome);

    TipoTarifa findByNomeContaining(String nome);


    @Query("select tipo From TipoTarifa tipo where tipo.nome = :nome")
    TipoTarifa procuraPorNome(@Param("nome") String nomeDaNathália);
}
