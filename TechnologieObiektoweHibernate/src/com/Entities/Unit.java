package com.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;
	
	@Getter
	@Setter
	private long number;
	
	@Getter
	@Setter
	@OneToOne
	private Soldier commander;
}
