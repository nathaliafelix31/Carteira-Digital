package com.matera.controller;

import com.matera.domain.Conta;
import com.matera.dto.ContaDto;
import com.matera.dto.ContaRequestDto;
import com.matera.repository.ContaRepository;
import com.matera.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/contas")
public class ContaController {

    private final ContaService contaService;

    @PostMapping
    public ContaDto criarConta(@RequestBody ContaRequestDto requestDto){

       Conta conta = contaService.criarConta(requestDto);
       return conta.toContaDto();


    }
}
