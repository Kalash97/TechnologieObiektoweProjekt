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
import lombok.ToString;

@ToString(callSuper = true, exclude = {"battalion", "plattons"})
@Entity
public class Company extends Unit implements Persistable{

	@Getter
	@Setter
	@ManyToOne
	private Battalion battalion;
	
	@Getter
	@OneToMany(mappedBy = "company")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Platoon> plattons = new ArrayList<Platoon>();
	
}
