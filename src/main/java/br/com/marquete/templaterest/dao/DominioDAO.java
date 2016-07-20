package br.com.marquete.templaterest.dao;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.marquete.templaterest.dao.base.GenericDAO;
import br.com.marquete.templaterest.dao.util.core.JPAUtil;
import br.com.marquete.templaterest.entidade.CategoriaDominio;
import br.com.marquete.templaterest.entidade.Dominio;

@Named
@RequestScoped
public class DominioDAO extends GenericDAO<Dominio> {

	private EntityManager em;

	public DominioDAO() {
		super(Dominio.class);
		em = JPAUtil.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Dominio> listar(CategoriaDominio categoriaDominio) {

		return (ArrayList<Dominio>) em.createQuery("SELECT d FROM Dominio d WHERE d.categoria = :categoriaDominio")
				.setParameter("categoriaDominio", categoriaDominio).getResultList();

	}

}
