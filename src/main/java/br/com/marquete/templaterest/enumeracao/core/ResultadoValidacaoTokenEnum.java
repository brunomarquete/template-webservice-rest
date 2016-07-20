package br.com.marquete.templaterest.enumeracao.core;

public enum ResultadoValidacaoTokenEnum {

	TOKEN_VALIDO("Token válido. Requisição autorizada!"), 
	HASH_INVALIDO("Hash de autenticação inválido. Requisição não autorizada!"),
	TOKEN_INATIVO("Token de autenticação inativo. Requisição não autorizada!"), 
	TOKEN_VALIDADE_EXPIRADA("Token de autenticação com data de validade expirada. Requisição não autorizada!"), 
	TOKEN_SEM_PERMISSAO("Token de autenticação sem permissão para esta operação. Requisição não autorizada!");

	private String mensagem;

	private ResultadoValidacaoTokenEnum(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public static ResultadoValidacaoTokenEnum obterResultadoValidacaoToken(String mensagem) {
		
		mensagem = mensagem.trim();
		
		if (mensagem.equals(ResultadoValidacaoTokenEnum.HASH_INVALIDO.getMensagem())) {
			return ResultadoValidacaoTokenEnum.HASH_INVALIDO;
		} else if (mensagem.equals(ResultadoValidacaoTokenEnum.TOKEN_INATIVO.getMensagem())) {
			return ResultadoValidacaoTokenEnum.TOKEN_INATIVO;
		} else if (mensagem.equals(ResultadoValidacaoTokenEnum.TOKEN_VALIDADE_EXPIRADA.getMensagem())) {
			return ResultadoValidacaoTokenEnum.TOKEN_VALIDADE_EXPIRADA;
		} else if (mensagem.equals(ResultadoValidacaoTokenEnum.TOKEN_SEM_PERMISSAO.getMensagem())) {
			return ResultadoValidacaoTokenEnum.TOKEN_SEM_PERMISSAO;
		} else {
			return ResultadoValidacaoTokenEnum.TOKEN_VALIDO;
		}

	}

}
