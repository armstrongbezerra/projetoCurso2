package br.com.castgroup.projeto.services;

import java.util.List;
import java.util.Optional;

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
	public void atualizar(Curso curso) {
		cursoRespository.findById(curso.getIdCurso());
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
	
	public Curso consultarPorId(Long idCurso) {
		Optional<Curso> consultar = cursoRespository.findById(idCurso);
		if(!consultar.isPresent()) {
			throw new RuntimeException("Curso não encontrado.");
		}
		return consultar.get();
	}
	
	
	public Curso salvar(Curso curso) {
		return this.cursoRespository.save(curso);
	}
	
	@Transactional
	public void excluirCurso(Long idCurso) {
		cursoRespository.deleteById(idCurso);
	}

}
