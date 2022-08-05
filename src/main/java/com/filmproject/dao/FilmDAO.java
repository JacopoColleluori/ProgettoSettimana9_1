package com.filmproject.dao;

import java.util.List;

import com.filmproject.model.Film;

public interface FilmDAO {

		public void save(Film f);
		
		public void delete(Long id);
		
		public void update(Film f,Long id);
		
		public Film getFilmById(Long id);
		
		public List<Film> getAllFilms();
		
		public List<Film> findByDirector(String director);
}
