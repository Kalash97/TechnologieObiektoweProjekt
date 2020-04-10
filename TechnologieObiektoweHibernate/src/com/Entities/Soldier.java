package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Enums.BloodType;
import com.Enums.Rank;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Soldier implements Persistable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private BloodType bloodType;
	
	@Getter
	@Setter
	@Column(name = "rankOfSoldier")
	private Rank rank;
	
	@Getter
	@OneToMany(mappedBy = "soldier")
	private List<Weapon> weapons = new ArrayList<Weapon>();
	
	@Getter
	@Setter
	@ManyToOne
	private Team team;
}
