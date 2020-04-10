package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Team extends Unit implements Persistable{

	@Getter
	@Setter
	@ManyToOne
	private Platoon platoon;
	
	@Getter
	@OneToMany(mappedBy = "team")
	private List<Soldier> soldiers = new ArrayList<Soldier>();
}
