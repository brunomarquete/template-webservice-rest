package br.com.marquete.templaterest.entidade.core;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.marquete.templaterest.entidade.base.Entidade;
import br.com.marquete.templaterest.enumeracao.core.MetodoHTTPEnum;

@Entity
@Table(name = "permissao")
public class Permissao extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_permissao_pk")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cadastro_permissao_dt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "token_permissao_fk", nullable=false)
	private Token token;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servicoweb_permissao_fk", nullable=false)
	private ServicoWeb servicoWeb;

	@Column(name = "metodohttp_permissao_txt", nullable = false)
	@Enumerated(EnumType.STRING)
	private MetodoHTTPEnum metodoHttp;

	public Permissao() {
		this.dataCadastro = new Timestamp(new Date().getTime());
	}

	public Permissao(Token token, ServicoWeb servico, MetodoHTTPEnum metodoHttp) {
		this.dataCadastro = new Timestamp(new Date().getTime());
		this.token = token;
		this.servicoWeb = servico;
		this.metodoHttp = metodoHttp;
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

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public ServicoWeb getServico() {
		return servicoWeb;
	}

	public void setServico(ServicoWeb servicoWeb) {
		this.servicoWeb = servicoWeb;
	}

	public MetodoHTTPEnum getMetodoHttp() {
		return metodoHttp;
	}

	public void setMetodoHttp(MetodoHTTPEnum metodoHttp) {
		this.metodoHttp = metodoHttp;
	}

}
