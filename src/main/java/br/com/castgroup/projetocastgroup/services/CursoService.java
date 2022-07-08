package br.com.castgroup.projetocastgroup.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.projeto.entities.Curso;
import br.com.castgroup.projeto.repository.ICursoRepository;



@Service
public class CursoService {

	@Autowired
	ICursoRepository repository;

	@Transactional
	public void cadastrar(Curso curso) {
		validaData(curso);
		repository.save(curso);

	}

	private void validaData(Curso cursos) {
		if(cursos.getDtInicio().isAfter(cursos.getDtTermino())) {
			throw new RuntimeException();

		}
	}
	
	public List<Curso> listar() {
		return repository.findAll();
	}

}
