package com.matera.domain;

import com.matera.dto.ContaDto;
import com.matera.exceptions.SaldoInsuficienteException;
import com.matera.exceptions.ValorInvalidoException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Slf4j
@Entity
@Getter @Setter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int agencia;
    private int numero = new Random().nextInt(100000);
    private BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne
    private Banco banco;

    @CreationTimestamp
    private LocalDateTime dataCriação;

    @UpdateTimestamp
    private LocalDateTime dataUltimaAtualização;

    @ManyToOne
    @JoinColumn(name = "titular_id")
    private Titular titular;


    @ManyToMany
    @JoinTable(name = "conta_tipos_tarifa",
        joinColumns = @JoinColumn(name = "conta_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_tarifa_id")
    )
    private List<TipoTarifa> tipostarifa = new ArrayList<>();

    public Conta(){}

    public void credito(BigDecimal valor) {
        this.validar(valor);
       saldo = saldo.add(valor);
       log.info("Conta {}/{} foi creditada com {} valor.", this.agencia, this.numero, valor);
    }

    public void debito(BigDecimal valor) {
        this.validar(valor);


        if (valor.compareTo(saldo) > 0) {
            throw new SaldoInsuficienteException("Conta não tem saldo para atender a solicitacao");
        }
        saldo = saldo.subtract(valor);
        log.info("Conta {}/{} foi debitada com {} valor.", this.agencia, this.numero, valor);
    }

    private void validar(BigDecimal valor) {
        final String mensagem = String.format("O valor %s é inválido.", valor);
        if (valor == null) {

           throw new ValorInvalidoException(mensagem);
        }

        if (this.valorIncorreto(valor)) {

            throw new ValorInvalidoException(mensagem);
        }
    }


    private boolean valorIncorreto(BigDecimal valor) {
        //NPE
        return valor.compareTo(BigDecimal.ZERO) <= 0;
    }


    public ContaDto toContaDto(){
        ContaDto dto = new ContaDto();
        dto.setAgencia(this.getAgencia());
        dto.setNumero(this.getNumero());
        dto.setSaldo(this.getSaldo());
        return dto;
    }
}
