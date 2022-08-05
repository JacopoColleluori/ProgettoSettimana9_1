package com.filmproject.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
			String directorToLC=f.getDirector().toLowerCase();   //here we're gonna set all the directors to lowercase to enable an easier searching experience
			f.setDirector(directorToLC);
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
		
		List<Film> films=null;
		
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
	
		try {
			/* Salviamo la entity Cittadino */
			entityTransaction.begin();
			Query q=em.createQuery("Select f from Film f where f.director = :director");
			q.setParameter("director", director.toLowerCase()); //director is in the database in lower case for easier search, so we switch the search to LC too 
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


	public void delete(Long id) {
		em=JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTrans = em.getTransaction();
		
		
		try {
			entityTrans.begin();
			Film f=em.find(Film.class, id);
			if(Objects.equals(f.getId(), id)) {
				em.remove(f);
				entityTrans.commit();	
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			entityTrans.rollback();
		}finally {
			em.close();
		}
		
	}


	public void update(Film f,Long id) {
		em=JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTrans = em.getTransaction();
		try {
			entityTrans.begin();
			f.setId(id);
			String boxOffice=BCrypt.hashpw(f.getBoxOffice(), BCrypt.gensalt());
			f.setDirector(f.getDirector().toLowerCase());
			f.setBoxOffice(boxOffice);
				em.merge(f);
				entityTrans.commit();	
			
			
		} catch (Exception e) {
			e.printStackTrace();
			entityTrans.rollback();
		}finally {
			em.close();
		}
		
	}


	public Film getFilmById(Long id) {
		em=JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTrans = em.getTransaction();
		Film f =null;
		try {
			entityTrans.begin();
			f=em.find(Film.class, id);
			entityTrans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTrans.rollback();
		}finally {
			em.close();
		}
		return f;
		
	}


	@SuppressWarnings("unchecked")
	public List<Film> getAllFilms() {
	List<Film> films=null;
		
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
	
		try {
			/* Salviamo la entity Cittadino */
			entityTransaction.begin();
			Query q=em.createQuery("Select f from Film f");
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
