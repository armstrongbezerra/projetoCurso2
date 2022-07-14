package br.com.castgroup.projeto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;

@Audited
@Data
@Entity
@Table(name="categorias")
public class Categoria {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name= "id", nullable=false)
	private Long id;
	@Column(name= "descricao", length=300, nullable=false)
	private String descricao;

}
