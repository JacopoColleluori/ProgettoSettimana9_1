package com.filmproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Formula;

@Entity
public class Film {

	private String title;

	private String director;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date year;

	private String movieType;

	private String boxOffice;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	
	



	public Film() {

	}


	
	public String getTitle() {
		return title;
	}

	public void setTitle(String titolo) {
		this.title = titolo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String regista) {
		this.director = regista;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date anno) {
		this.year = anno;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String tipo) {
		this.movieType = tipo;
	}

	public String getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(String incasso) {
		this.boxOffice = incasso;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
