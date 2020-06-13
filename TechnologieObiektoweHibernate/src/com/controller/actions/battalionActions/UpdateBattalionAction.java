package com.controller.actions.battalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateBattalionAction extends Action{

	private View view;
	private BattalionRepo repo;
	
	@Override
	public void launch() {
		Battalion b = RepoUtil.getValidBattalion(view, repo);	
		b.setNumber(view.getValidNumber("Podaj Id"));
		
		repo.update(b);
	}
	
	@Override
	public String getName() {
		return "UpdateBattalion";
	}

}
