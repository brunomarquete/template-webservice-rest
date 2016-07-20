package br.com.marquete.templaterest.entidade.core;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.marquete.templaterest.entidade.base.Entidade;
import br.com.marquete.templaterest.enumeracao.core.PerfilPermissaoEnum;
import br.com.marquete.templaterest.enumeracao.core.StatusTokenEnum;

@Entity
@Table(name = "token")
@Cacheable(value = false)
public class Token extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_token_pk")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cadastro_token_dt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "hash_token_txt", unique = true, nullable = false)
	private String hash;

	@Column(name = "status_token_txt", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTokenEnum statusToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "validade_token_dt")
	private Date validade;

	@Column(name = "observacao_token_txt")
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "perfilpermissao_token_txt")
	private PerfilPermissaoEnum perfilPermissao;

	@OneToMany(mappedBy = "token")
	private List<Permissao> permissoes;

	public Token() {
		this.dataCadastro = new Timestamp(new Date().getTime());
		this.statusToken = StatusTokenEnum.ATIVO;
		this.perfilPermissao = PerfilPermissaoEnum.GET;
		this.permissoes = new ArrayList<Permissao>();
	}

	public Token(String hash) {
		this.dataCadastro = new Timestamp(new Date().getTime());
		this.statusToken = StatusTokenEnum.ATIVO;
		this.perfilPermissao = PerfilPermissaoEnum.GET;
		this.permissoes = new ArrayList<Permissao>();
		this.hash = hash;
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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public StatusTokenEnum getStatusToken() {
		return statusToken;
	}

	public void setStatusToken(StatusTokenEnum statusToken) {
		this.statusToken = statusToken;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PerfilPermissaoEnum getPerfilPermissao() {
		return perfilPermissao;
	}

	public void setPerfilPermissao(PerfilPermissaoEnum perfilPermissao) {
		this.perfilPermissao = perfilPermissao;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}
