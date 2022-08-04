package com.filmproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.filmproject.dao.FilmDAO;
import com.filmproject.model.Film;
import com.filmproject.util.JpaUtil;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class FilmDAOImpl  implements FilmDAO{

	EntityManager em;

	/** 
	 * Instantiates the film object in the database with  the parameter:
	 * 
	 * @param f the film from the {@link Film} class
	 * 
	 * 
	 * */
	public void save(Film f) {
		em=JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTrans = em.getTransaction();
		
		try {
			entityTrans.begin();
			String boxOffice=BCrypt.hashpw(f.getBoxOffice(), BCrypt.gensalt());
			f.setBoxOffice(boxOffice);
			em.persist(f);
			entityTrans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTrans.rollback();
		}finally {
			em.close();
		}
		
	}


	/** 
	 * Search the film List in the database
	 * 
	 * @param  director it's an attributes from the {@link Film} class and according to it 
	 * 
	 * this method 
	 * 
	 * @return the {@link ArrayList} of {@link Film} with the exact director chosen 
	 * */
	
	@SuppressWarnings("unchecked")
	public List<Film> findByDirector(String director) {
		
		List<Film> films=new ArrayList<Film>();
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
	
		try {
			/* Salviamo la entity Cittadino */
			entityTransaction.begin();
			Query q=em.createQuery("Select f from Film f where f.director = :director");
			q.setParameter("director", director);
			films =q.getResultList();
			entityTransaction.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			entityTransaction.rollback();
		} finally {
			em.close();
		}
		return films;
	}
	
}
