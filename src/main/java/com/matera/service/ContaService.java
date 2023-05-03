package com.matera.service;

import com.matera.domain.Conta;
import com.matera.dto.ContaRequestDto;
import com.matera.exceptions.ContaExistenteException;
import com.matera.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta criarConta(ContaRequestDto requestDto){
        var conta = new Conta();
        conta.setAgencia(requestDto.getAgencia());
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
}
