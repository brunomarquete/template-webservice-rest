package br.com.marquete.templaterest.dao.base;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.marquete.templaterest.dao.util.core.JPAUtil;

public class GenericDAO<T> {

	private EntityManager em;

	@SuppressWarnings("rawtypes")
	private Class classe;

	@SuppressWarnings("rawtypes")
	public GenericDAO(Class classe) {
		this.classe = classe;
		em = JPAUtil.getEntityManager();
	}

	/**
	 * Insere um registro no banco de dados a partir de um objeto.
	 * 
	 * @param entidade
	 *            Entidade a ser adicionada.
	 */
	public void inserir(T entidade) {

		try {
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.toString());
		} 

	}

	/**
	 * Atualiza um registro no banco de dados a partir de um objeto.
	 * 
	 * @param entidade
	 *            Entidade a ser atualizada.
	 */
	public void atualizar(T entidade) {

		try {
			em.getTransaction().begin();
			em.merge(entidade);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.toString());
		} 

	}

	/**
	 * Obtém um registro no banco de dados a partir de seu ID.
	 * 
	 * @param id
	 *            ID do objeto a ser retornado.
	 * @return Objeto com o ID passado como parâmetro.
	 */
	@SuppressWarnings("unchecked")
	public T buscar(Long id) {
		return (T) em.find(classe, id);
	}

	/**
	 * Remove um objeto do banco de dados apartir do seu ID.
	 * 
	 * @param id
	 *            ID do objeto a ser removido.
	 */
	public void remover(Long id) {

		try {

			T entidade = buscar(id);
			
			if (entidade != null) {
				em.getTransaction().begin();
				em.remove(entidade);
				em.getTransaction().commit();
			}

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.toString());
		} 
	}

	/**
	 * Obtém todos os registros do banco de dados.
	 * 
	 * @return Lista de todos registros do banco de dados.
	 */
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		return em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e").getResultList();
	}
}