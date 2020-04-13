package com.Repos;

import com.Entities.Company;
import com.PersistanceManager.HibernatePersistanceManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyRepo {

	HibernatePersistanceManager hpm;
	
	public Company createCompany(Company company) {
		return (Company) hpm.create(company);
	}
	
	public void deleteCompany(Company company) {
		hpm.delete(company);
	}
	
	public Company findById(long id) {
		return (Company) hpm.findById(id, Company.class);
	}
}
