package com.controller.actions.CompanyActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompaniesWithoutBattalionAction implements Action{

	private View view;
	private CompanyRepo companyRepo;
	
	@Override
	public void launch() {
		List<Company> companies = companyRepo.findCompaniesWithoutBattalion();
		ViewHelper.printResults(ViewHelper.compainiesToPersistable(companies), view);
	}

	@Override
	public String getName() {
		return "FindCompaniesWithoutBattalion";
	}

}
