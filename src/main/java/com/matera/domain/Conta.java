package com.matera.domain;

import com.matera.dto.ContaDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Random;

@Entity
@Getter @Setter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int agencia;
    private int numero = new Random().nextInt(100000);
    private BigDecimal saldo = BigDecimal.ZERO;

    public Conta(){}

    public ContaDto toContaDto(){
        ContaDto dto = new ContaDto();
        dto.setAgencia(this.getAgencia());
        dto.setNumero(this.getNumero());
        dto.setSaldo(this.getSaldo());
        return dto;
    }
}
