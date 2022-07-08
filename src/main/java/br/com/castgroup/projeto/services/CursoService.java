package br.com.castgroup.projeto.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.projeto.entities.Curso;
import br.com.castgroup.projeto.repository.CursoRepository;



@Service
public class CursoService {

	@Autowired
	CursoRepository cursoRespository;

	@Transactional
	public void cadastrar(Curso curso) {
		validaData(curso);
		cursoRespository.save(curso);

	}

	private void validaData(Curso cursos) {
		if(cursos.getDtInicio().isAfter(cursos.getDtTermino())) {
			throw new RuntimeException();

		}
	}
	
	public List<Curso> listar() {
		return cursoRespository.findAll();
	}

}
