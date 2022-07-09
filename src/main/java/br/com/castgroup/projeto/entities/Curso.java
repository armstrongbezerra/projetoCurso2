package br.com.castgroup.projeto.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "curso")
@Entity
public class Curso {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idcurso")
	private Long idCurso;
	@Column(name = "descricao", length = 300, nullable = false)
	private String descricao;
	@Column(name = "dtInicio", nullable = false)
	@CreatedDate
	private LocalDate dtInicio;
	@Column(name = "dtTermino", nullable = false)
	@LastModifiedDate
	private LocalDate dtTermino;
	private Integer qtdAluno;
	@Column(name = "categoria", length = 50, nullable = false)
	private String categoria;
	
	@JoinColumn(name="fkCategoria")
	@ManyToOne
	private Categoria categorias;
	

}
