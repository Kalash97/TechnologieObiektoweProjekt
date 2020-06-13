package com.controller.actions.battalionActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionsWithoutCompaniesAction extends Action{

	private View view;
	private BattalionRepo battalionRepo;
	
	@Override
	public void launch() {
		List<Battalion> battalions = battalionRepo.findBattalionsWithoutCompanies();
		ViewHelper.printResults(battalions, view);
	}

	@Override
	public String getName() {
		return "FindBattalionsWithoutCompanies";
	}

}
