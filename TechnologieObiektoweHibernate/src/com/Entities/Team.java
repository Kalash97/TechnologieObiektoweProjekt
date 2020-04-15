package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Soldier> soldiers = new ArrayList<Soldier>();
}
