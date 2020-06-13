package com.controller.actions.companyActions;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCompanyAction extends Action{

	private View view;
	private CompanyRepo repo;
	
	@Override
	public void launch() {
		Company c = new Company();
		c.setNumber(view.getValidNumber("Podaj Id"));
		
		repo.create(c);
	}


	@Override
	public String getName() {
		return "CreateCompany";
	}

}
