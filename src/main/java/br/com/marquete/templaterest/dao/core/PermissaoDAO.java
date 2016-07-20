package br.com.marquete.templaterest.dao.core;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.marquete.templaterest.dao.base.GenericDAO;
import br.com.marquete.templaterest.dao.util.core.JPAUtil;
import br.com.marquete.templaterest.entidade.core.Permissao;
import br.com.marquete.templaterest.entidade.core.ServicoWeb;
import br.com.marquete.templaterest.entidade.core.Token;
import br.com.marquete.templaterest.enumeracao.core.MetodoHTTPEnum;

public class PermissaoDAO extends GenericDAO<Permissao> {

	private EntityManager em;

	public PermissaoDAO() {
		super(Permissao.class);
		em = JPAUtil.getEntityManager();
	}

	public Permissao buscar(Token token, ServicoWeb servicoWeb, MetodoHTTPEnum metodoHttp) {

		try {

			TypedQuery<Permissao> query = em.createQuery(
					"SELECT p FROM Permissao p WHERE p.token = :token AND p.servicoWeb = :servicoWeb AND p.metodoHttp = :metodoHttp",
					Permissao.class);

			query.setParameter("token", token);
			query.setParameter("servicoWeb", servicoWeb);
			query.setParameter("metodoHttp", metodoHttp);

			Permissao permissao = query.getSingleResult();

			return permissao;

		} catch (NoResultException e) {
			return null;
		}

	}

}
