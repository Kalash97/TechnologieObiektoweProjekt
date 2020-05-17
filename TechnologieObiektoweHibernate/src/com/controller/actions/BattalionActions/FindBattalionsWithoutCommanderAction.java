package com.controller.actions.BattalionActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionsWithoutCommanderAction implements Action{

	private View view;
	private BattalionRepo battalionRepo;
	
	@Override
	public void launch() {
		List<Battalion> battalions = battalionRepo.findBattalionsWithoutCommander();
		ViewHelper.printResults(battalions, view);
	}
	
	@Override
	public String getName() {
		return "FindBattalionsWithoutCommander";
	}

}
