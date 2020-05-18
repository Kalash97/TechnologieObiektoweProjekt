package com.controller.actions.companyActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllCompaniesAction implements Action{

	private CompanyRepo repo;
	private View view;
	
	@Override
	public void launch() {
		List<Company> companies = repo.findAllCompanies();
		ViewHelper.printResults(companies, view);
		view.print("");
	}

	@Override
	public String getName() {
		return "FindAllCompanies";
	}

}
