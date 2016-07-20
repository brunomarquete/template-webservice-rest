package br.com.marquete.templaterest.enumeracao.core;

public enum MetodoHTTPEnum {

	GET, POST, PUT, DELETE;
	
	public static MetodoHTTPEnum obterMetodoHttpEnum(String nome) {
		
		nome = nome.trim().toUpperCase();
		
		if (nome.equals("GET")) {
			return GET;
		} else if (nome.equals("POST")) {
			return POST;
		} else if (nome.equals("PUT")) {
			return PUT;
		} else if (nome.equals("DELETE")) {
			return DELETE;
		} else {
			return null;
		}
		
		
	}
}
