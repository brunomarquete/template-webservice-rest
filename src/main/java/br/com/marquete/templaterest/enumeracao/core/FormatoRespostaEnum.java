package br.com.marquete.templaterest.enumeracao.core;

import javax.ws.rs.core.MediaType;

public enum FormatoRespostaEnum {

	JSON(MediaType.APPLICATION_JSON + ";charset=utf-8"),
	XML(MediaType.APPLICATION_XML + ";charset=utf-8"), 
	TEXT_PLAIN(MediaType.TEXT_PLAIN + ";charset=utf-8");

	private String codificacao;

	private FormatoRespostaEnum(String codificacao) {
		this.codificacao = codificacao;
	}

	public String getCodificacao() {
		return codificacao;
	}

	public void setCodificação(String codificação) {
		this.codificacao = codificação;
	}

	public static FormatoRespostaEnum obterFormatoRespostaEnum(String nome) {

		nome = nome.trim().toUpperCase();

		if (nome.equals(JSON.toString())) {
			return JSON;
		} else if (nome.equals(XML.toString())) {
			return XML;
		} else if (nome.equals(TEXT_PLAIN.toString())) {
			return TEXT_PLAIN;
		} else {
			return null;
		}

	}

}
