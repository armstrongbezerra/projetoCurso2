package br.com.castgroup.projeto.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.com.castgroup.projeto.entities.Curso;
import br.com.castgroup.projeto.services.CursoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "cursos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	
	@ApiOperation(value = "Cadastro de Cursos")
	@PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> post(@RequestBody Curso curso) {
		try {
			cursoService.cadastrar(curso);
			return ResponseEntity.status(HttpStatus.CREATED).body("Curso Cadastrado com Sucesso!");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro " + e.getMessage());
		}
	}
	
	@ApiOperation(value = "Atualizar Cadastro de Cursos")
	@PutMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>put(@RequestBody Curso curso){
		cursoService.atualizar(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body("Curso Atualizado com Sucesso!");
	}
	
	@ApiOperation(value = "Listar Todos os Cursos")
	@GetMapping
	public ResponseEntity<List<Curso>> listar(){
		return ResponseEntity.ok(cursoService.listar());
	}
	
	
	@ApiOperation(value = "Deletar Cursos")
	@DeleteMapping("/{idCurso}")
	public ResponseEntity<String> delete(@RequestParam Long idCurso){
		cursoService.excluiCurso(idCurso);
		return new ResponseEntity<String>("Curso Deletado Com Sucesso!", HttpStatus.OK);
	}
	
}

