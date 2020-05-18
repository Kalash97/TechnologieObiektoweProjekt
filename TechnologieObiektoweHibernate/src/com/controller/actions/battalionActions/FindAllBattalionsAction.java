package com.controller.actions.battalionActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllBattalionsAction implements Action{

	private BattalionRepo repo;
	private View view;
	
	@Override
	public void launch() {
		List<Battalion> battalions = repo.findAllBattalions();
		ViewHelper.printResults(battalions, view);
		view.print("");
	}

	@Override
	public String getName() {
		return "FindAllBattalions";
	}

}
