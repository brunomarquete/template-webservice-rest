package br.com.marquete.templaterest.service.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

import com.google.common.net.HttpHeaders;

import br.com.marquete.templaterest.core.ajudante.AutenticacaoAjudante;
import br.com.marquete.templaterest.core.ajudante.LogAjudante;
import br.com.marquete.templaterest.dao.core.LogDAO;
import br.com.marquete.templaterest.entidade.core.Log;
import br.com.marquete.templaterest.enumeracao.core.ResultadoValidacaoTokenEnum;
import br.com.marquete.templaterest.enumeracao.core.TipoLogEnum;

public class LogService {

	private SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");

	public void registraLogRequisicao(ContainerRequestContext requestContext) {

		Date dataHora = new Date();
		String metodo = requestContext.getMethod();
		String uri = requestContext.getUriInfo().getPath();
		String formato = requestContext.getHeaderString(HttpHeaders.CONTENT_TYPE);
		String tamanho = requestContext.getHeaderString(HttpHeaders.CONTENT_LENGTH);
		String descricao = LogAjudante.montarDescricaoLogRequisicao(formatadorData.format(dataHora), metodo, uri,
				formato, tamanho);

		System.out.println(descricao);
		persisteLog(dataHora, TipoLogEnum.REQUISICAO, metodo, uri, formato, tamanho, null);
	}

	public void registraLogResposta(ContainerResponseContext responseContext, ContainerRequestContext requestContext) {

		Date dataHora = new Date();
		String codigo = String.valueOf(responseContext.getStatus());
		String uri = requestContext.getUriInfo().getPath();
		if (responseContext.getLocation() != null) {
			uri = responseContext.getLocation().getPath();
		}
		String formato = null;
		if (responseContext.getMediaType() != null) {
			formato = responseContext.getMediaType().getType() + "/" + responseContext.getMediaType().getSubtype();
		}
		String tamanho = responseContext.getHeaderString(HttpHeaders.CONTENT_LENGTH);
		String mensagemResultadoValidacao = String.valueOf(responseContext.getEntity());
		ResultadoValidacaoTokenEnum resultadoValidacao = ResultadoValidacaoTokenEnum.obterResultadoValidacaoToken(mensagemResultadoValidacao);
		String descricao = LogAjudante.montarDescricaoLogResposta(formatadorData.format(dataHora), codigo, uri, formato,
				tamanho, resultadoValidacao);
		
		System.out.println(descricao);
		persisteLog(dataHora, TipoLogEnum.RESPOSTA, codigo, uri, formato, tamanho, resultadoValidacao);

	}

	private void persisteLog(Date dataHora, TipoLogEnum tipo, String metodoOuCodigo, String uri, String formato,
			String tamanho, ResultadoValidacaoTokenEnum resultadoValidacao) {

		LogDAO dao = new LogDAO();
		Log log = new Log(dataHora, tipo, metodoOuCodigo, uri, formato, tamanho, resultadoValidacao);
		dao.inserir(log);

	}

}
