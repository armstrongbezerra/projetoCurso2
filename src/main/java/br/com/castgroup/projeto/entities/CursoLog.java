package br.com.castgroup.projeto.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "curso_log")
@Entity
public class CursoLog {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idLog")
	private Long idLog;
	@Column(name = "idCurso")
	private Long idCurso;
	@Column(name = "idUsuario")
	private Long idUsuario;
	@Column(name = "dtInclusao", nullable = false)
	private LocalDate dtInclusao;
	@Column(name = "ultimaAlteracao", nullable = false)
	private LocalDate ultimaAlteracao;

}
