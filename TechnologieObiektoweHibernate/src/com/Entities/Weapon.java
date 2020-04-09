package com.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.Enums.WeaponType;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Weapon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String serialNumber;
	
	@Getter
	@Setter
	private WeaponType weaponType;
	
	@Getter
	@Setter
	@ManyToOne
	private Soldier soldier;
}
