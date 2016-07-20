package br.com.marquete.templaterest.entidade.core;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.marquete.templaterest.entidade.base.Entidade;

@Entity
@Table(name="servico_web")
public class ServicoWeb extends Entidade implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Column(name = "id_servicoweb_pk")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cadastro_servicoweb_dt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "nome_servicoweb_txt", nullable=false)
	private String nome;
	
	@Column(name = "identificadoruri_servicoweb_txt", nullable=false, unique=true)
	private String identificadorUri;
	
	@Column(name = "templateuri_servicoweb_txt", nullable=false)
	private String templateUri;
	
	@Column(name = "descricao_servicoweb_txt", length = 5000)
	private String descricao;
	
	public ServicoWeb() {
		this.dataCadastro = new Timestamp(new Date().getTime());
	}

	
	public ServicoWeb(String nome, String identificadorUri, String templateUri) {
		this.dataCadastro = new Timestamp(new Date().getTime());
		this.nome = nome;
		this.identificadorUri = identificadorUri;
		this.templateUri = templateUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificadorUri() {
		return identificadorUri;
	}


	public void setIdentificadorUri(String identificadorUri) {
		this.identificadorUri = identificadorUri;
	}


	public String getTemplateUri() {
		return templateUri;
	}


	public void setTemplateUri(String templateUri) {
		this.templateUri = templateUri;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
