package br.com.castgroup.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.projeto.entities.Categoria;
import br.com.castgroup.projeto.services.CategoriaService;



@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		return ResponseEntity.ok(categoriaService.listar());
	}

}
