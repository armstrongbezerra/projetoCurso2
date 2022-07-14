package br.com.castgroup.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class ProjetoApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoApplication.class);
	

	public static void main(String[] args) {
		LOGGER.info("Inicializando o Método Main");
		SpringApplication.run(ProjetoApplication.class, args);
		
		LOGGER.info("Método Main Funcinando");
	}

}
