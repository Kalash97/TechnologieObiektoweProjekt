package com.model.repos;

import java.util.List;

import com.model.entities.Company;
import com.model.entities.Persistable;
import com.model.entities.Soldier;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

import lombok.AllArgsConstructor;

public class CompanyRepo extends EntityRepo<Company> {

	public CompanyRepo(HibernatePersistanceManager persistence) {
		super(persistence);
	}

	public Company findById(long id) {
		return (Company) persistence.findById(id, Company.class);
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
		List<Persistable> results = persistence.findByQuery(query, Company.class);
		List<Company> companies = ParseUtil.parseCompanyList(results);
		return companies;
	}
	
	public List<Company> findCompanyOfCommander(Soldier soldier){
		String query = "SELECT C FROM Company C, Soldier S WHERE C.commander.id = " + soldier.getId() + " AND S.id = " + soldier.getId();
		return findCompanyByQuery(query);
	}
	
	public List<Company> findAllCompanies(){
		String query = "SELECT C FROM Company C";
		return findCompanyByQuery(query);
	}
}
