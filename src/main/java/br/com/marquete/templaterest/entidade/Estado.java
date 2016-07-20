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
@Table(name = "estado")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Estado extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	public Estado() {
		super();
	}

	public Estado(String nome, String uf) {
		super();
		this.nome = nome;
		this.uf = uf;
	}

	@Column(name = "id_estado_pk")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_estado_txt", length = 250, nullable = false)
	private String nome;

	@Column(name = "uf_estado_txt", length = 2, nullable = false)
	private String uf;

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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
