package br.com.castgroup.projeto.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.castgroup.projeto.entities.Curso;


@Repository
public interface ICursoRepository extends JpaRepository<Curso, Integer> {
	
	@Query("SELECT c FROM Curso c WHERE c.nome LIKE %?1%")
	List<Curso> findCursoByName(String nome);


}
