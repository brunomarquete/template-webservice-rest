package br.com.marquete.templaterest.dao;

import br.com.marquete.templaterest.dao.base.GenericDAO;
import br.com.marquete.templaterest.entidade.Estado;

public class EstadoDAO extends GenericDAO<Estado> {
	
	public EstadoDAO() {
		super(Estado.class);
	}	

}
