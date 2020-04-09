package com.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
public class Battalion extends Unit{

	@Getter
	@OneToMany(mappedBy = "battalion")
	private List<Company> companies = new ArrayList<Company>();
	
}
