package com.controller.actions.CompanyActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompaniesWithoutPlatoonsAction implements Action{

	private View view;
	private CompanyRepo companyRepo;
	
	@Override
	public void launch() {
		List<Company> companies = companyRepo.findCompaniesWithoutPlatoons();
		ViewHelper.printResults(ViewHelper.compainiesToPersistable(companies), view);
	}

	@Override
	public String getName() {
		return "FindCompaniesWithoutPlatoons";
	}

}
