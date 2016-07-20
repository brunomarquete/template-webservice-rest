package br.com.marquete.templaterest.interceptor.core;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import br.com.marquete.templaterest.service.core.LogService;

@Provider
public class ResponseInterceptor implements ContainerResponseFilter {

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		new LogService().registraLogResposta(responseContext, requestContext);
	}

}
