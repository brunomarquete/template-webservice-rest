package br.com.marquete.templaterest.entidade.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.marquete.templaterest.entidade.base.Entidade;
import br.com.marquete.templaterest.enumeracao.core.ResultadoValidacaoTokenEnum;
import br.com.marquete.templaterest.enumeracao.core.TipoLogEnum;

@Entity
@Table(name = "log")
public class Log extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_log_pk")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cadastro_log_dt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "tipo_log_txt")
	@Enumerated(EnumType.STRING)
	private TipoLogEnum tipo;

	@Column(name = "metodocodigo_log_txt", nullable = false)
	private String metodoOuCodigo;

	@Column(name = "uri_log_txt", nullable = false)
	private String uri;

	@Column(name = "formato_log_txt")
	private String formato;

	@Column(name = "tamanho_log_txt")
	private String tamanho;

	@Enumerated(EnumType.STRING)
	@Column(name = "resultadovalidacao_log_txt")
	private ResultadoValidacaoTokenEnum resultadoValidacao;

	public Log() {
	}

	public Log(Date dataHora, TipoLogEnum tipo, String metodoOuCodigo, String uri, String formato, String tamanho,
			ResultadoValidacaoTokenEnum resultadoValidacao) {
		this.dataCadastro = dataHora;
		this.tipo = tipo;
		this.metodoOuCodigo = metodoOuCodigo;
		this.uri = uri;
		this.formato = formato;
		this.tamanho = tamanho;
		this.resultadoValidacao = resultadoValidacao;
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

	public TipoLogEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoLogEnum tipo) {
		this.tipo = tipo;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getMetodoOuCodigo() {
		return metodoOuCodigo;
	}

	public void setMetodoOuCodigo(String metodoOuCodigo) {
		this.metodoOuCodigo = metodoOuCodigo;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public ResultadoValidacaoTokenEnum getResultadoValidacao() {
		return resultadoValidacao;
	}

	public void setResultadoValidacao(ResultadoValidacaoTokenEnum resultadoValidacao) {
		this.resultadoValidacao = resultadoValidacao;
	}

}
