package com.matera;

import com.matera.domain.Banco;
import com.matera.domain.TipoTarifa;
import com.matera.repository.BancoRepository;
import com.matera.repository.TipoTarifaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@SpringBootApplication
public class CarteiraDigitalApplication {

	private final TipoTarifaRepository tipoTarifaRepository;

	private final BancoRepository bancoRepository;



	public static void main(String[] args) {
		SpringApplication.run(CarteiraDigitalApplication.class);
	}

	@PostConstruct
	public void insert(){
		TipoTarifa chequeEspecial = new TipoTarifa();
		chequeEspecial.setNome("Cheque Especial");
		tipoTarifaRepository.save(chequeEspecial);

		Banco nathaliaBanco = new Banco();
		nathaliaBanco.setNome("Nathalia Bank");
		bancoRepository.save(nathaliaBanco);

		Banco wesleyBanco = new Banco();
		wesleyBanco.setNome("Wesley Bank");
		bancoRepository.save(wesleyBanco);
	}
}