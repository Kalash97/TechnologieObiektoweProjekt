package com.controller.actions;

import com.model.entities.Company;
import com.model.entities.Platoon;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignPlatoonToCompanyAction implements Action {

	private View view;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;

	@Override
	public void launch() {
		Company c = RepoUtil.getValidCompany(view, companyRepo);
		Platoon p = RepoUtil.getValidPlatoon(view, platoonRepo);

		assignPlatoonToCompany(c, p);		
	}

	public void assignPlatoonToCompany(Company c, Platoon p) {
		if (!RepoUtil.companyContainsPlatoon(c, p)) {
			p.setCompany(c);
			c.getPlattons().add(p);
			companyRepo.updateCompany(c);
			platoonRepo.updatePlatoon(p);
		}else {
			view.print("Pluton o podanym numerze ju¿ istnieje w kompanii!");
		}
	}

	

	@Override
	public String getName() {
		return "AssignPlatoonToCompany";
	}

}
