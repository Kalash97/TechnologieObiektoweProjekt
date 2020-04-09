package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Platoon extends Unit{

	@Getter
	@Setter
	@ManyToOne
	private Company company;
	
	@Getter
	@OneToMany(mappedBy = "platoon")
	private List<Team> teams = new ArrayList<Team>();
}
