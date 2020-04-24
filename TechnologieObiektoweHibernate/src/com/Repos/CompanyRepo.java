package com.Repos;

import java.util.List;

import com.Entities.Company;
import com.Entities.Persistable;
import com.PersistanceManager.HibernatePersistanceManager;
import com.Utils.ParseUtil;

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
	
	public void updateCompany(Company c) {
		hpm.update(c);
	}
	
	public List<Company> findCompaniesWithoutPlatoons(){
		String query = "SELECT C FROM Company C WHERE size(C.plattons)=0";
		return findCompanyByQuery(query);
	}
	
	public List<Company> findCompaniesWithoutBattalion(){
		String query = "SELECT C FROM Company C WHERE C.battalion.id=null";
		return findCompanyByQuery(query);
	}

	public List<Company> findCompaniesWithoutCommander(){
		String query = "SELECT C FROM Company C WHERE C.commander.id=null";
		return findCompanyByQuery(query);
	}
	
	private List<Company> findCompanyByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Company.class);
		List<Company> companies = ParseUtil.parseCompanyList(results);
		return companies;
	}
}
