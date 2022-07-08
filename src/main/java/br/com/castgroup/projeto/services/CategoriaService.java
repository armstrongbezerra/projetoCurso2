package br.com.castgroup.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.projeto.entities.Categoria;
import br.com.castgroup.projeto.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	public List<Categoria> listar() {
		return repository.findAll();
	}

	
	

}
