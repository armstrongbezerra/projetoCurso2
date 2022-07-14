package br.com.castgroup.projeto.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.castgroup.projeto.entities.Curso;
import br.com.castgroup.projeto.repository.CursoRepository;

@Service
public class CursoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CursoService.class);

	@PersistenceContext
	EntityManager entity;

	private final CursoRepository cursoRespository;

	public CursoService(CursoRepository repository) {
		this.cursoRespository = repository;
	}

	@Transactional
	public void cadastrar(Curso curso) {
		validaData(curso);
		cursoRespository.save(curso);
	}

	private void validaData(Curso cursos) {

		// Verifica se tem curso no periodo informado
		Optional<Curso> curso = cursoRespository.exists(cursos.getDtInicio(), cursos.getDtTermino());

		if (curso != null && !curso.isEmpty()) {
			throw new RuntimeException("Existe(m) Curso(s) Planejados(s) Dentro do Período Informado.");

		}
		// Se o inicio do curso for menor que a data atual
		if (cursos.getDtInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("A Data de Inicio Deve Ser Maior ou Igual a Atual");
		}
		if (cursos.getDtInicio().isAfter(cursos.getDtTermino())) {
			throw new RuntimeException("A Data de Inicio Deve Ser Menor que a Data de Término do Curso");
		}
	}
	
	@Transactional
	public void atualizar(Curso curso) {
		Optional <Curso> cursoOptional = cursoRespository.findById(curso.getIdCurso());
		if(cursoOptional.isEmpty()) {
			throw new RuntimeException("Curso Não Encontrado.");
		}
		validaData(curso);
		cursoRespository.save(curso);
	}

	/*
	 * public void atualizar(Curso curso) {
	 * cursoRespository.findById(curso.getIdCurso());
	 * LOGGER.info("Curso Atualizado com Sucesso"); cursoRespository.save(curso); }
	 */

	public List<Curso> listar(String descricao, LocalDate dtInicio, LocalDate dtTermino) {

		CriteriaBuilder cb = entity.getCriteriaBuilder();
		CriteriaQuery<Curso> cq = cb.createQuery(Curso.class);

		Root<Curso> curso = cq.from(Curso.class);

		List<Predicate> predList = new ArrayList<>();

		if (descricao != null && descricao != "") {
			Predicate descricaoPredicate = cb.like(curso.get("descricao"), "%" + descricao + "%");
			predList.add(descricaoPredicate);
		}

		if (dtInicio != null) {
			Predicate dtInicioPredicate = cb.greaterThanOrEqualTo(curso.get("dtInicio"), dtInicio);
			predList.add(dtInicioPredicate);
		}

		if (dtTermino != null) {
			Predicate dtTerminoPredicate = cb.lessThanOrEqualTo(curso.get("dtTermino"), dtTermino);
			predList.add(dtTerminoPredicate);
		}

		Predicate[] predicateArray = new Predicate[predList.size()];
		predList.toArray(predicateArray);
		cq.where(predicateArray);

		TypedQuery<Curso> query = entity.createQuery(cq);
		LOGGER.info("Curso Encontrado ");
		return query.getResultList();
	}

	public Curso consultarPorId(Long idCurso) {
		Optional<Curso> consultar = cursoRespository.findById(idCurso);
		if (!consultar.isPresent()) {
			LOGGER.info("Curso não encontrado");
			throw new RuntimeException("Curso não encontrado.");
		}
		return consultar.get();
	}

	public Curso salvar(Curso curso) {
		LOGGER.info("Curso Salvo");
		return this.cursoRespository.save(curso);
	}

	@Transactional
	public void excluirCurso(Long idCurso) {
		Optional<Curso> curso = cursoRespository.findById(idCurso);
		if (curso != null || !curso.isEmpty()) {
			if (curso.get().getDtTermino().isBefore(LocalDate.now())) {
				throw new RuntimeException("Não é Possivel Excluir Cursos já Realizados");
			} else {
				cursoRespository.save(curso.get());
			}
		}
		cursoRespository.deleteById(idCurso);
	}
}
