package br.com.marquete.templaterest.resource.base;

import java.net.URI;

import javax.ws.rs.core.Response;

import br.com.marquete.templaterest.enumeracao.core.FormatoRespostaEnum;

public class Resource {

	/***
	 * Retorna uma resposta 200 e o conteúdo do objeto no formato indicado (json
	 * ou xml). Não realiza autenticação de hash.
	 * 
	 * @param objeto
	 *            Objeto a ser retornado.
	 * @param formato
	 *            Formato do objeto (json ou xml). Se passado valor diferente a
	 *            json ou xml, formato json é utilizado.
	 * @return Response de sucesso (200) com objeto no formato indicado.
	 */
	public Response getResponseOk(Object objeto, String formato) {

		formato = configurarCodificacaoFormato(formato);
		return Response.ok(objeto, formato).build();

	}

	/***
	 * Retorna uma resposta 200. Não realiza autenticação de hash.
	 * 
	 * @return Response de sucesso (200).
	 */
	public Response getResponseOk() {
		return Response.ok().build();
	}

	/***
	 * Cria um recurso na URI indicada e retorna uma resposta 201.
	 * 
	 * @param uri
	 *            URI do recurso a ser criado.
	 * @return Response da criação do recurso (201).
	 */
	public Response getResponseCreated(URI uri) {
		return Response.created(uri).build();
	}

	private String configurarCodificacaoFormato(String formato) {
		if (formato == null) {
			formato = FormatoRespostaEnum.JSON.getCodificacao();
		} else if (formato.trim().toLowerCase().equals("xml")) {
			formato = FormatoRespostaEnum.XML.getCodificacao();
		} else {
			formato = FormatoRespostaEnum.JSON.getCodificacao();
		}
		return formato;
	}

}
