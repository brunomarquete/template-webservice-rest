package br.com.marquete.templaterest.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.marquete.templaterest.entidade.base.Entidade;

@Entity
@Table(name = "categoria_dominio")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriaDominio extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	public CategoriaDominio() {
		super();
	}
	
	public CategoriaDominio(String nome) {
		super();
		this.nome = nome;
	}

	@Column(name = "id_categoriadominio_pk")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_categoriadominio_txt", length = 250, nullable = false)
	private String nome;

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
	
}
