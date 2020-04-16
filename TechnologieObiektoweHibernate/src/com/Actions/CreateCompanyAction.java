package com.Actions;

import com.Entities.Company;
import com.Repos.CompanyRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCompanyAction implements Action{

	private View view;
	private CompanyRepo repo;
	
	@Override
	public void launch() {
		Company c = new Company();
		
		String companyNumber;
		do {
			view.print("Podaj numer kompanii");
			companyNumber=view.read();
		}while(!ValidUtil.isValid(companyNumber));
		
		c.setNumber(Long.parseLong(companyNumber));
		
		repo.createCompany(c);
	}

	@Override
	public String getName() {
		return "CreateCompany";
	}

}