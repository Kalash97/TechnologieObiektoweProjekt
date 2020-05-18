package com.controller.actions.battalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteBattalionAction implements Action {

	private View view;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {
		Battalion b = RepoUtil.getValidBattalion(view, battalionRepo);

		removeCommanderFromBattalion(b);
		removeCompaniesFromBattalion(b);

		battalionRepo.deleteBattalion(b);
	}

	private void removeCommanderFromBattalion(Battalion b) {
		if(b.getCommander()!=null) {
			b.setCommander(null);
			battalionRepo.updateBattalion(b);
		}
	}
	
	private void removeCompaniesFromBattalion(Battalion b) {
		if(b.getCompanies().size()>0) {
			for(int i=0; i<b.getCompanies().size();i++) {
				Company c=b.getCompanies().get(i);
				c.setBattalion(null);
				companyRepo.updateCompany(c);
			}
			battalionRepo.updateBattalion(b);
		}
	}
	
	@Override
	public String getName() {
		return "DeleteBattalion";
	}

}
