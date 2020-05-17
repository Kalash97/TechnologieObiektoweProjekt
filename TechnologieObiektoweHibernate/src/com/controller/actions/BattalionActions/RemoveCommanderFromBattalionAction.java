package com.controller.actions.BattalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromBattalionAction implements Action{

	private View view;
	private BattalionRepo repo;
	
	@Override
	public void launch() {
		Battalion b = RepoUtil.getValidBattalion(view, repo);
		
		b.setCommander(null);
		repo.updateBattalion(b);
	}
	
	@Override
	public String getName() {
		return "RemoveCommanderFromBattalion";
	}

}
