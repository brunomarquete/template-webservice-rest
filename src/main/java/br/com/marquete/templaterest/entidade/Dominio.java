package br.com.marquete.templaterest.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.marquete.templaterest.entidade.base.Entidade;

@Entity
@Table(name = "dominio")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dominio extends Entidade implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Dominio() {
		super();
	}
	
	public Dominio(String nome, CategoriaDominio categoria) {
		super();
		this.nome = nome;
		this.categoria = categoria;
	}

	@Column(name = "id_dominio_pk")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_dominio_txt", length = 250, nullable = false)
	private String nome;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_dominio_fk", nullable=false)
	private CategoriaDominio categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaDominio getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDominio categoria) {
		this.categoria = categoria;
	}
}
