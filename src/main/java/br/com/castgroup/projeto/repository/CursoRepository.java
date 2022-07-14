package br.com.castgroup.projeto.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.castgroup.projeto.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	@Query("SELECT c from Curso c where c.dtInicio <= :data_inicio AND c.dtTermino >= :data_fim")
	Optional<Curso> exists(@Param("data_inicio") LocalDate data_inicio, @Param("data_fim") LocalDate data_fim);
	
	//@Query("SELECT c FROM Curso c WHERE c.descricao LIKE CONCAT('%', CONCAT(:descricao, '%'))")
  //  public Curso getByDescricao(@Param("descricao") String descricao);
	
	//@Query("SELECT c from Curso c  where c.dtInicio >= :data_inicio AND c.dtTermino <= :data_fim AND c.descricao :descricao")
	//public List<Curso> filtrarCursos (@Param("data_inicio") LocalDate data_inicio, @Param("data_fim") LocalDate data_fim);
	
	//@Query("SELECT c from Curso c where c.descricao LIKE CONCAT('%', CONCAT(:descricao, '%')) AND c.dtInicio <= :data_inicio AND c.dtTermino >= :data_fim")
}

