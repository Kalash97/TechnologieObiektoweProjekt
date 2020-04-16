package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Platoon extends Unit implements Persistable{

	@Getter
	@Setter
	@ManyToOne
	private Company company;
	
	@Getter
	@OneToMany(mappedBy = "platoon")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Team> teams = new ArrayList<Team>();
}
