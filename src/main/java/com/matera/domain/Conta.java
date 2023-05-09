package com.matera.domain;

import com.matera.dto.ContaDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Embedded
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

    public ContaDto toContaDto(){
        ContaDto dto = new ContaDto();
        dto.setAgencia(this.getAgencia());
        dto.setNumero(this.getNumero());
        dto.setSaldo(this.getSaldo());
        return dto;
    }
}
