package br.com.marquete.templaterest.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import br.com.marquete.templaterest.dao.base.GenericDAO;
import br.com.marquete.templaterest.dao.util.core.JPAUtil;
import br.com.marquete.templaterest.entidade.Cidade;
import br.com.marquete.templaterest.entidade.Estado;

public class CidadeDAO extends GenericDAO<Cidade> {

	private EntityManager em;

	public CidadeDAO() {
		super(Cidade.class);
		em = JPAUtil.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Cidade> listar(Estado estado) {

		return (ArrayList<Cidade>) em.createQuery("SELECT c FROM Cidade c WHERE c.estado = :estado")
				.setParameter("estado", estado).getResultList();

	}

}
