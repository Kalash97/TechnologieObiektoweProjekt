package com.controller.actions.CompanyActions;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.entities.Platoon;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCompanyAction implements Action {

	private View view;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;

	@Override
	public void launch() {
		Company c = RepoUtil.getValidCompany(view, companyRepo);
	
		removeComanderFromCompany(c);
		removePlatoonsFromCompany(c);

		companyRepo.deleteCompany(c);
	}

	private void removeComanderFromCompany(Company c) {
		if(c.getCommander()!=null) {
			c.setCommander(null);
			companyRepo.updateCompany(c);
		}
	}
	
	private void removePlatoonsFromCompany(Company c) {
		Platoon p;
		if(c.getPlattons().size()>0) {
			for(int i=0; i<c.getPlattons().size();i++) {
				p=c.getPlattons().get(i);
				p.setCompany(null);
				platoonRepo.updatePlatoon(p);
			}
			companyRepo.updateCompany(c);
		}
	}
	@Override
	public String getName() {
		return "DeleteCompany";
	}

}
