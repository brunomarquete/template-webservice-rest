package br.com.marquete.templaterest.servidor.core;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.marquete.templaterest.interceptor.core.RequestInterceptor;
import br.com.marquete.templaterest.interceptor.core.ResponseInterceptor;

public class ServidorGrizzly {

	public static void main(String[] args) {

		try {

			HttpServer server = inicializaServidor();
			System.out.println("Servidor Grizzly rodando...");
			System.in.read();
			server.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static HttpServer inicializaServidor() {
		URI uri = URI.create("http://localhost:8080");
		ResourceConfig config = new ResourceConfig().packages("br.com.marquete.pcsrest.resource")
				.register(RequestInterceptor.class).register(ResponseInterceptor.class);
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}

}
