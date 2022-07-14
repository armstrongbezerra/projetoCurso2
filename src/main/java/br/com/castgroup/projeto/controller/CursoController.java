package br.com.castgroup.projeto.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/cursos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

	@Autowired
	CursoService cursoService;

	@ApiOperation(value = "Cadastro de Cursos")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> post(@RequestBody Curso curso) {
		try {			
			cursoService.cadastrar(curso);
			LOGGER.info("Curso Cadastrado");
			return ResponseEntity.status(HttpStatus.CREATED).body("Curso Cadastrado com Sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@ApiOperation(value = "Atualizar Cadastro de Cursos")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> put(@RequestBody Curso curso) {
		try {
			cursoService.atualizar(curso);
			LOGGER.info("Curso Atualizado");
			return ResponseEntity.status(HttpStatus.OK).body("Curso Atualizado com Sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@ApiOperation(value = "Listar Cursos")
	@GetMapping
	public ResponseEntity<List<Curso>> listar(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtInicio,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtTermino) {
		return ResponseEntity.ok(cursoService.listar(descricao, dtInicio, dtTermino));
	}

	@ApiOperation(value = "Listar Cursos Por Id")
	@GetMapping("{idCurso}")
	public ResponseEntity<?> consultarPorId(@PathVariable("idCurso") Long idCurso) {
		try {
			LOGGER.info("Curso Encontrado Por Id");
			return ResponseEntity.status(HttpStatus.OK).body(cursoService.consultarPorId(idCurso));
		} catch (Exception e) {
			LOGGER.error("Curso Não Encontrado Por Id");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não Foi Possível Encontrar Curso Por Id");
		}

	}

	@ApiOperation(value = "Deletar Cursos")
	@DeleteMapping(value = "/{idCurso}")
	public ResponseEntity<String> delete(@PathVariable("idCurso") Long idCurso) {
		try {
			cursoService.excluirCurso(idCurso);
			LOGGER.info("Curso Excluído");
			return new ResponseEntity<String>("Curso Excluído Com Sucesso!", HttpStatus.OK);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
