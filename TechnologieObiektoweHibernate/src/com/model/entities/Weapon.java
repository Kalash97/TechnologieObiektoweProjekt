package com.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.utils.enums.WeaponType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "soldier")
@Entity
public class Weapon implements Persistable{

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
