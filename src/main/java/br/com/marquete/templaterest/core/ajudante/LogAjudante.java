package br.com.marquete.templaterest.core.ajudante;

import br.com.marquete.templaterest.enumeracao.core.ResultadoValidacaoTokenEnum;

public class LogAjudante {

	public static String montarDescricaoLogRequisicao(String dataHora, String metodo, String uri, String formato,
			String tamanho) {

		String descricao = dataHora + ": uma requisição HTTP " + metodo + " foi recebida no recurso " + uri;

		if (formato != null && tamanho != null) {
			descricao += " (Formato: " + formato + " Tamanho: " + tamanho + " bytes)";
		} else if (formato != null && tamanho == null) {
			descricao += " (Formato: " + formato + ")";
		} else if (formato == null && tamanho != null) {
			descricao += " (Tamanho: " + tamanho + " bytes)";
		}

		descricao += ".";
		return descricao;
	}

	public static String montarDescricaoLogResposta(String dataHora, String codigo, String uri, String formato,
			String tamanho, ResultadoValidacaoTokenEnum resultadoValidacao) {
		String descricao = dataHora + ": uma resposta HTTP " + codigo + " foi enviada pelo recurso " + uri;

		if (formato != null && tamanho != null) {
			descricao += " (Formato: " + formato + " Tamanho: " + tamanho + " bytes)";
		} else if (formato != null && tamanho == null) {
			descricao += " (Formato: " + formato + ")";
		} else if (formato == null && tamanho != null) {
			descricao += " (Tamanho: " + tamanho + " bytes)";
		}

		descricao += ".";
		
		if (resultadoValidacao != ResultadoValidacaoTokenEnum.TOKEN_VALIDO) {
			descricao += resultadoValidacao.getMensagem();
		}
		
		return descricao;
	}

	

}
