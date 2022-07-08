package br.com.castgroup.projeto.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.projeto.entities.Curso;
import br.com.castgroup.projetocastgroup.services.CursoService;

@RestController
@RequestMapping(value = "cursos")
public class CursoController {

	@Autowired
	CursoService service;
	
	@PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> post(@RequestBody Curso curso) {
		try {
			service.cadastrar(curso);
			return ResponseEntity.status(HttpStatus.CREATED).body("Curso Cadastrado com Sucesso!");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro " + e.getMessage());
		}
	}
}
