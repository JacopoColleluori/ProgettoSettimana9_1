package com.filmproject.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final EntityManagerFactory entitymManagerFactory;

	static {
		entitymManagerFactory = Persistence.createEntityManagerFactory("filmproject"); //stiamo caricando quello scritto nel persistance,carica i
	}																					// dati di connessione al DB, codice case sensitive		

	public static EntityManagerFactory getEntityManagerFactory() {
		return entitymManagerFactory;

	}
}
