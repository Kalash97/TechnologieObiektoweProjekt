package com.Actions.CompanyActions;

import java.util.List;

import com.Actions.Action;
import com.Entities.Company;
import com.Repos.CompanyRepo;
import com.Utils.ViewHelper;
import com.View.View;

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
