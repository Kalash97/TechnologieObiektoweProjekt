package com.controller.actions;

import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCompanyToBattalionAction extends Action {

	private View view;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {
		Battalion b = RepoUtil.getValidBattalion(view, battalionRepo);
		Company c = RepoUtil.getValidCompany(view, companyRepo);

		assignBattalionToCompany(b, c);	
	}

	public void assignBattalionToCompany(Battalion b, Company c) {
		if (!RepoUtil.battalionContainsCompany(b, c)) {
			c.setBattalion(b);
			b.getCompanies().add(c);
			battalionRepo.update(b);
			companyRepo.update(c);
		}else {
			view.print("Kompania o podanym numerze ju¿ istnieje w batalionie!");
		}
	}

	@Override
	public String getName() {
		return "AssignCompanyToBattalion";
	}

}
