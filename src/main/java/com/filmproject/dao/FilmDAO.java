package com.filmproject.dao;

import java.util.List;

import com.filmproject.model.Film;

public interface FilmDAO {

		public void save(Film f);
		
		public List<Film> findByDirector(String director);
}
