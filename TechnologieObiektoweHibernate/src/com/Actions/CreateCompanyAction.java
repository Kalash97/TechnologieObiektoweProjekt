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
				
		String companyNumber = getCompanyNumber();
		
		c.setNumber(Long.parseLong(companyNumber));
		
		repo.createCompany(c);
	}

	private String getCompanyNumber() {
		String companyNumber;
		do {
			view.print("Podaj numer kompanii");
			companyNumber=view.read();
		}while(!ValidUtil.isLongInstance(companyNumber));
		return companyNumber;
	}

	@Override
	public String getName() {
		return "CreateCompany";
	}

}
