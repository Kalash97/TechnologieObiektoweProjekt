package com.controller.actions.battalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
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

		RepoUtil.removeCommanderFromBattalion(b, battalionRepo);
		RepoUtil.removeCompaniesFromBattalion(b, companyRepo, battalionRepo);

		battalionRepo.deleteBattalion(b);
	}


	
	
	
	@Override
	public String getName() {
		return "DeleteBattalion";
	}

}
