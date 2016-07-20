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

import br.com.marquete.templaterest.dao.CategoriaDominioDAO;
import br.com.marquete.templaterest.dao.DominioDAO;
import br.com.marquete.templaterest.entidade.CategoriaDominio;
import br.com.marquete.templaterest.entidade.Dominio;
import br.com.marquete.templaterest.resource.base.Resource;

@javax.annotation.Resource
@Path("{hash}/categoriadominio")
public class CategoriaDominioResource extends Resource {

	private CategoriaDominioDAO dao;
	private DominioDAO dominioDao;

	public CategoriaDominioResource() {
		this.dao = new CategoriaDominioDAO();
		this.dominioDao = new DominioDAO();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscar(@PathParam("hash") String hash, @PathParam("id") Long id,
			@QueryParam("formato") String formato) {

		CategoriaDominio categoriaDominio = dao.buscar(id);
		return this.getResponseOk(new GenericEntity<CategoriaDominio>(categoriaDominio) {
		}, formato);

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listar(@PathParam("hash") String hash, @QueryParam("formato") String formato) {

		ArrayList<CategoriaDominio> listaCategoriaDominios = (ArrayList<CategoriaDominio>) dao.listar();
		return this.getResponseOk(new GenericEntity<List<CategoriaDominio>>(listaCategoriaDominios) {
		}, formato);

	}

	@GET
	@Path("{id}/dominios")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listarDominiosDaCategoriaDominio(@PathParam("hash") String hash, @PathParam("id") Long id,
			@QueryParam("formato") String formato) {

		CategoriaDominio categoriaDominio = dao.buscar(id);
		ArrayList<Dominio> listaDominios = (ArrayList<Dominio>) dominioDao.listar(categoriaDominio);

		return this.getResponseOk(new GenericEntity<List<Dominio>>(listaDominios) {
		}, formato);

	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response inserir(@PathParam("hash") String hash, CategoriaDominio categoriaDominio) {

		dao.inserir(categoriaDominio);
		URI uri = URI.create("/categoriaCategoriaDominio/" + categoriaDominio.getId());

		return this.getResponseCreated(uri);
	}

	@DELETE
	@Path("{id}")
	public Response remover(@PathParam("hash") String hash, @PathParam("id") Long id) {

		dao.remover(id);
		return this.getResponseOk();
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response atualizar(@PathParam("hash") String hash, @PathParam("id") Long id,
			CategoriaDominio categoriaDominio) {

		CategoriaDominio categoriaDominioBd = dao.buscar(id);

		if (categoriaDominioBd != null) {

			categoriaDominio.setId(categoriaDominioBd.getId());
			dao.atualizar(categoriaDominio);

			return this.getResponseOk();

		} else {
			return this.getResponseOk();
		}

	}

}
