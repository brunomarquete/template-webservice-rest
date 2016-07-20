package br.com.marquete.templaterest.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.marquete.templaterest.dao.DominioDAO;
import br.com.marquete.templaterest.entidade.Dominio;
import br.com.marquete.templaterest.resource.base.Resource;

@Path("{hash}/dominio")
public class DominioResource extends Resource {

	private DominioDAO dao;

	public DominioResource() {
		this.dao = new DominioDAO();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscar(@PathParam("id") Long id, @QueryParam("formato") String formato) {

		Dominio dominio = dao.buscar(id);
		return this.getResponseOk(new GenericEntity<Dominio>(dominio) {
		}, formato);

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listar(@QueryParam("formato") String formato) {

		ArrayList<Dominio> listaDominios = (ArrayList<Dominio>) dao.listar();
		return this.getResponseOk(new GenericEntity<List<Dominio>>(listaDominios) {
		}, formato);

	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response inserir(Dominio dominio) {

		dao.inserir(dominio);
		URI uri = URI.create("/dominio/" + dominio.getId());

		return this.getResponseCreated(uri);
	}

	@DELETE
	@Path("{id}")
	public Response remover(@PathParam("id") Long id) {

		dao.remover(id);
		return this.getResponseOk();
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response atualizar(@PathParam("id") Long id, Dominio dominio) {

		Dominio dominioBd = dao.buscar(id);

		if (dominioBd != null) {

			dominio.setId(dominioBd.getId());
			dao.atualizar(dominio);

			return this.getResponseOk();

		} else {
			return this.getResponseOk();
		}

	}

}
