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

import br.com.marquete.templaterest.dao.CidadeDAO;
import br.com.marquete.templaterest.entidade.Cidade;
import br.com.marquete.templaterest.resource.base.Resource;

@Path("{hash}/cidade")
public class CidadeResource extends Resource {

	private CidadeDAO dao;

	public CidadeResource() {
		this.dao = new CidadeDAO();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscar(@PathParam("id") Long id, @QueryParam("formato") String formato) {

		Cidade cidade = dao.buscar(id);
		return this.getResponseOk(new GenericEntity<Cidade>(cidade) {
		}, formato);

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listar(@QueryParam("formato") String formato) {

		ArrayList<Cidade> listaCidades = (ArrayList<Cidade>) dao.listar();
		return this.getResponseOk(new GenericEntity<List<Cidade>>(listaCidades) {
		}, formato);

	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response inserir(Cidade cidade) {

		dao.inserir(cidade);
		URI uri = URI.create("/cidade/" + cidade.getId());

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
	public Response atualizar(@PathParam("id") Long id, Cidade cidade) {

		Cidade cidadeBd = dao.buscar(id);

		if (cidadeBd != null) {

			cidade.setId(cidadeBd.getId());
			dao.atualizar(cidade);

			return this.getResponseOk();

		} else {
			return this.getResponseOk();
		}

	}

}
