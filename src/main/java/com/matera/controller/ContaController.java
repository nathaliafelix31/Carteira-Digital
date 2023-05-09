package com.matera.controller;

import com.matera.domain.Conta;
import com.matera.dto.ContaDto;
import com.matera.dto.ContaRequestDto;
import com.matera.repository.ContaRepository;
import com.matera.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
}
