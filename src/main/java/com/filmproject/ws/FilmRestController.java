package com.filmproject.ws;

import java.util.ArrayList;

import com.filmproject.dao.FilmDAO;
import com.filmproject.dao.impl.FilmDAOImpl;
import com.filmproject.model.Film;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/films")
public class FilmRestController {

	
	@PostMapping("/addFilm")
	public ResponseEntity<String> save(@RequestBody Film f){
		FilmDAO filmDao=new FilmDAOImpl();
		filmDao.save(f);
		
		return new ResponseEntity<String>("Inserimento Film Avvenuto",HttpStatus.CREATED);
		
	}
	
	@GetMapping("/findbydirector/{director}")
	public ResponseEntity<ArrayList<Film>> findByDirector(@PathVariable String director){
		FilmDAO filmDao=new FilmDAOImpl();
		ArrayList<Film> films=(ArrayList<Film>) filmDao.findByDirector(director);
		
		return new ResponseEntity<ArrayList<Film>>(films,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getfilms")
	public ResponseEntity<ArrayList<Film>> findAllFilms(){
		FilmDAO filmDao=new FilmDAOImpl();
		ArrayList<Film> films=(ArrayList<Film>) filmDao.getAllFilms();
		
		return new ResponseEntity<ArrayList<Film>>(films,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getfilm/{id}")
	public ResponseEntity<Film> findFilmById(@PathVariable Long id){
		FilmDAO filmDao=new FilmDAOImpl();
		Film f = filmDao.getFilmById(id);
		
		return new ResponseEntity<Film>(f,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFilm(@PathVariable Long id){
		FilmDAO filmDao=new FilmDAOImpl();
		filmDao.delete(id);
		return new ResponseEntity<String>("Eliminazione effettuata",HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateFilm(@RequestBody Film f,@PathVariable Long id){
		FilmDAO filmDao=new FilmDAOImpl();
		filmDao.update(f,id);
		return new ResponseEntity<String>("Aggiornamento effettuato",HttpStatus.ACCEPTED);
		
	}
}
