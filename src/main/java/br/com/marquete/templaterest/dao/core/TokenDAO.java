package br.com.marquete.templaterest.dao.core;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.marquete.templaterest.dao.base.GenericDAO;
import br.com.marquete.templaterest.dao.util.core.JPAUtil;
import br.com.marquete.templaterest.entidade.core.Token;

public class TokenDAO extends GenericDAO<Token> {

	private EntityManager em;

	public TokenDAO() {
		super(Token.class);
		em = JPAUtil.getEntityManager();
	}

	public Token buscar(String hash) {

		try {

			TypedQuery<Token> query = em.createQuery("SELECT t FROM Token t WHERE t.hash = :hash", Token.class);
			query.setParameter("hash", hash);
			Token token = query.getSingleResult();
			
			return token;

		} catch (NoResultException e) {
			return null;
		}

	}

}
