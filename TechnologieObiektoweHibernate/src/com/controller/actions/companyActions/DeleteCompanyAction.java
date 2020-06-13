package com.controller.actions.companyActions;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCompanyAction extends Action {

	private View view;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;

	@Override
	public void launch() {
		Company c = RepoUtil.getValidCompany(view, companyRepo);
	
		RepoUtil.removeComanderFromCompany(c, companyRepo);
		RepoUtil.removePlatoonsFromCompany(c, platoonRepo, companyRepo);

		companyRepo.delete(c);
	}

	
	
	
	@Override
	public String getName() {
		return "DeleteCompany";
	}

}
