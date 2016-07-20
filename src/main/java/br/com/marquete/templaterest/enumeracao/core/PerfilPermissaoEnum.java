package br.com.marquete.templaterest.enumeracao.core;

public enum PerfilPermissaoEnum {

	GET, GET_PUT, GET_POST, GET_PUT_POST, GET_PUT_POST_DELETE, MANUAL;

	public static PerfilPermissaoEnum obterPerfilPermissaoEnum(String nome) {

		nome = nome.trim().toUpperCase();

		if (nome.equals("GET")) {
			return GET;
		} else if (nome.equals("GET_PUT")) {
			return GET_PUT;
		} else if (nome.equals("GET_POST")) {
			return GET_POST;
		} else if (nome.equals("GET_PUT_POST")) {
			return GET_PUT_POST;
		} else if (nome.equals("GET_PUT_POST_DELETE")) {
			return GET_PUT_POST_DELETE;
		} else if (nome.equals("MANUAL")) {
			return MANUAL;
		} else {
			return null;
		}

	}

}
