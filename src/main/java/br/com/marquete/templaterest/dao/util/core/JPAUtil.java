package br.com.marquete.templaterest.dao.util.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
   
	private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;

    public static EntityManagerFactory getEntityManagerFactory() {
    	
          if (entityManagerFactory == null) {
               entityManagerFactory = Persistence.createEntityManagerFactory("pcs_mysql");
          }
          
          return entityManagerFactory;
    }

     public static EntityManager getEntityManager() {
    	 
           if (entityManager != null && entityManager.isOpen()) {
                 return entityManager;
           } else {
                 entityManager = getEntityManagerFactory().createEntityManager();
                 return entityManager;
           }
     }

}
