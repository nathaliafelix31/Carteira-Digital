package com.matera.service;

import com.matera.domain.Conta;
import com.matera.domain.Titular;
import com.matera.dto.ContaRequestDto;
import com.matera.exceptions.ContaExistenteException;
import com.matera.repository.ContaRepository;
import com.matera.repository.TitularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final TitularRepository titularRepository;

    public Conta criarConta(ContaRequestDto requestDto){

        final Titular titular = new Titular();
        titular.setCpf(requestDto.getCpf());
        titular.setNome(requestDto.getNome());
        titularRepository.save(titular);

        var conta = new Conta();
        conta.setAgencia(requestDto.getAgencia());
        conta.setTitular(titular);
        validaContaExistente(conta);
        return contaRepository.save(conta);

    }

    private void validaContaExistente(Conta conta){

       Optional<Conta> contaOptional =
               contaRepository.findByAgenciaAndNumero(conta.getAgencia(), conta.getNumero());

       if (contaOptional.isPresent()){
           throw new ContaExistenteException();
       }
    }

    public List<Conta> procuraContas() {
        return (List<Conta>) contaRepository.findAll();
    }
}
