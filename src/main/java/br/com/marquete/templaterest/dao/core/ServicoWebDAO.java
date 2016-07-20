package br.com.marquete.templaterest.dao.core;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.marquete.templaterest.dao.base.GenericDAO;
import br.com.marquete.templaterest.dao.util.core.JPAUtil;
import br.com.marquete.templaterest.entidade.core.ServicoWeb;

public class ServicoWebDAO extends GenericDAO<ServicoWeb> {

	EntityManager em;

	public ServicoWebDAO() {
		super(ServicoWeb.class);
		em = JPAUtil.getEntityManager();
	}

	public ServicoWeb buscar(String identificadorUri) {

		try {

			TypedQuery<ServicoWeb> query = em.createQuery(
					"SELECT s FROM ServicoWeb s WHERE s.identificadorUri = :identificadorUri", ServicoWeb.class);
			query.setParameter("identificadorUri", identificadorUri);
			ServicoWeb servicoWeb = query.getSingleResult();

			return servicoWeb;

		} catch (NoResultException e) {
			return null;
		}

	}

}
