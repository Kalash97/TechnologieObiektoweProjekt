package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;

@Entity
public class Battalion extends Unit implements Persistable{

	@Getter
	@OneToMany(mappedBy = "battalion")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Company> companies = new ArrayList<Company>();
	
}
