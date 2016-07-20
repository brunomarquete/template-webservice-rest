package br.com.marquete.templaterest.interceptor.core;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.marquete.templaterest.enumeracao.core.FormatoRespostaEnum;
import br.com.marquete.templaterest.enumeracao.core.MetodoHTTPEnum;
import br.com.marquete.templaterest.enumeracao.core.ResultadoValidacaoTokenEnum;
import br.com.marquete.templaterest.service.core.AutenticacaoService;
import br.com.marquete.templaterest.service.core.LogService;

@Provider
@PreMatching
public class RequestInterceptor implements ContainerRequestFilter {

	public void filter(ContainerRequestContext requestContext) throws IOException {

		new LogService().registraLogRequisicao(requestContext);

		String uri = requestContext.getUriInfo().getPath();
		MetodoHTTPEnum metodoHttp = MetodoHTTPEnum.obterMetodoHttpEnum(requestContext.getMethod().trim().toUpperCase());

		ResultadoValidacaoTokenEnum resultadoValidacao = new AutenticacaoService().verificaToken(uri, metodoHttp);

		if (resultadoValidacao != ResultadoValidacaoTokenEnum.TOKEN_VALIDO) {

			Response response = obterResponseNaoAutorizado(resultadoValidacao.getMensagem());
			requestContext.abortWith(response);
			return;

		}

	}

	private Response obterResponseNaoAutorizado(String mensagem) {

		Response response = Response.status(Response.Status.UNAUTHORIZED)
				.type(FormatoRespostaEnum.TEXT_PLAIN.getCodificacao()).entity(mensagem).build();

		return response;
	}

}
