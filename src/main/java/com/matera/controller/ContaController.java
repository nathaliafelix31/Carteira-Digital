package com.matera.controller;

import com.matera.domain.Conta;
import com.matera.dto.ContaDto;
import com.matera.dto.ContaRequestDto;
import com.matera.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/contas")
public class ContaController {

    private final ContaService contaService;

    @PostMapping
    public ContaDto criarConta(@RequestBody ContaRequestDto requestDto){

       Conta conta = contaService.criarConta(requestDto);
       conta.setNumero(6543);
       return conta.toContaDto();


    }

    @GetMapping
    public List<Conta> procuraContas(){
        return contaService.procuraContas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDto> procuraContaPorId(@PathVariable Long id){
        Conta conta = contaService.procuraConta(id);
        return ResponseEntity.ok(conta.toContaDto());
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<ContaResponseDto> procuraContaPorId(@PathVariable Long id) {
        Conta conta = contaService.procuraConta(id);
        return ResponseEntity.ok(conta.toContaDto());
    }*/
    @PostMapping("/{idConta}/credito/{valor}")
    public ResponseEntity<ContaDto> creditarConta(@PathVariable Long idConta, @PathVariable BigDecimal valor) {
        Conta conta = contaService.creditarConta(idConta, valor);
        return ResponseEntity.ok(conta.toContaDto());
    }

    @PostMapping("/{idConta}/debito/{valor}")
    public ResponseEntity<ContaDto> debitaConta(@PathVariable Long idConta, @PathVariable BigDecimal valor) {
        Conta conta = contaService.debitaConta(idConta, valor);
        return ResponseEntity.ok(conta.toContaDto());
    }

    @PostMapping("/{idContaDebitada}/{idContaCreditada}/{valor}")
    public ResponseEntity debitaConta(@PathVariable Long idContaDebitada,
                                      @PathVariable Long idContaCreditada,
                                      @PathVariable BigDecimal valor) {
        contaService.transferencia(idContaDebitada, idContaCreditada, valor);
        return ResponseEntity.ok("Transferencia realizada com sucesso");
    }

}
