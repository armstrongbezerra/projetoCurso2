package br.com.castgroup.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.castgroup.projeto.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {



}
