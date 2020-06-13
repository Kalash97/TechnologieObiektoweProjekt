package com.controller.actions.companyActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompaniesWithoutPlatoonsAction extends Action{

	private View view;
	private CompanyRepo companyRepo;
	
	@Override
	public void launch() {
		List<Company> companies = companyRepo.findCompaniesWithoutPlatoons();
		ViewHelper.printResults(companies, view);
	}

	@Override
	public String getName() {
		return "FindCompaniesWithoutPlatoons";
	}

}
