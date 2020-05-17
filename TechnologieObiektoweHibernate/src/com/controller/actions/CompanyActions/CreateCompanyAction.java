package com.controller.actions.CompanyActions;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.ValidUtil;
import com.view.View;

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
