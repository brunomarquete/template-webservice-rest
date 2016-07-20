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
import br.com.marquete.templaterest.dao.EstadoDAO;
import br.com.marquete.templaterest.entidade.Cidade;
import br.com.marquete.templaterest.entidade.Estado;
import br.com.marquete.templaterest.resource.base.Resource;

@Path("{hash}/estado")
public class EstadoResource extends Resource {

	private EstadoDAO dao;
	private CidadeDAO cidadeDao;

	public EstadoResource() {
		this.dao = new EstadoDAO();
		this.cidadeDao = new CidadeDAO();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscar(@PathParam("id") Long id, @QueryParam("formato") String formato) {

		Estado estado = dao.buscar(id);
		return this.getResponseOk(new GenericEntity<Estado>(estado) {
		}, formato);

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listar(@QueryParam("formato") String formato) {

		ArrayList<Estado> listaEstados = (ArrayList<Estado>) dao.listar();
		return this.getResponseOk(new GenericEntity<List<Estado>>(listaEstados) {
		}, formato);

	}

	@GET
	@Path("{id}/cidades")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listarCidadesDoEstado(@PathParam("id") Long id, @QueryParam("formato") String formato) {

		Estado estado = dao.buscar(id);
		ArrayList<Cidade> listaCidadesEstado = (ArrayList<Cidade>) cidadeDao.listar(estado);

		return this.getResponseOk(new GenericEntity<List<Cidade>>(listaCidadesEstado) {
		}, formato);

	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response inserir(Estado estado) {

		dao.inserir(estado);
		URI uri = URI.create("/estado/" + estado.getId());

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
	public Response atualizar(@PathParam("id") Long id, Estado estado) {

		Estado estadoBd = dao.buscar(id);

		if (estadoBd != null) {

			estado.setId(estadoBd.getId());
			dao.atualizar(estado);

			return this.getResponseOk();

		} else {
			return this.getResponseOk();
		}

	}

}
