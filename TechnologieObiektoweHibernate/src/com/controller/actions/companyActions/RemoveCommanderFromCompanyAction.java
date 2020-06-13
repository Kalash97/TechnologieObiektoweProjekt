package com.controller.actions.companyActions;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromCompanyAction extends Action{

	private View view;
	private CompanyRepo repo;
	
	@Override
	public void launch() {
		Company c = RepoUtil.getValidCompany(view, repo);
		
		RepoUtil.removeComanderFromCompany(c, repo);
	}

	@Override
	public String getName() {
		return "RemoveCommanderFromCompany";
	}

}
