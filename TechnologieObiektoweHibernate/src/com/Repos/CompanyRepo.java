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
	
	public void deleteCompany(long id) {
		hpm.delete(id, Company.class);
	}
}
