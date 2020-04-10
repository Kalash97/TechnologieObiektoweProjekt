package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Company extends Unit implements Persistable{

	@Getter
	@Setter
	@ManyToOne
	private Battalion battalion;
	
	@Getter
	@OneToMany(mappedBy = "company")
	private List<Platoon> plattons = new ArrayList<Platoon>();
	
}
