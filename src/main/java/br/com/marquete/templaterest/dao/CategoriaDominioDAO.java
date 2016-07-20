package br.com.marquete.templaterest.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.marquete.templaterest.dao.base.GenericDAO;
import br.com.marquete.templaterest.entidade.CategoriaDominio;

@Named
@RequestScoped
public class CategoriaDominioDAO extends GenericDAO<CategoriaDominio> {

	public CategoriaDominioDAO() {
		super(CategoriaDominio.class);
	}

}
