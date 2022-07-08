package br.com.castgroup.projeto.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.castgroup.projeto.entities.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	//@Query("SELECT c FROM Curso c WHERE c.nome LIKE %?1%")
	//List<Curso> findCursoByName(String nome);


}
