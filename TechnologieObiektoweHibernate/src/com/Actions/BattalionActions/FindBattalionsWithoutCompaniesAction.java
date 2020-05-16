package com.Actions.BattalionActions;

import java.util.List;

import com.Actions.Action;
import com.Entities.Battalion;
import com.Repos.BattalionRepo;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionsWithoutCompaniesAction implements Action{

	private View view;
	private BattalionRepo battalionRepo;
	
	@Override
	public void launch() {
		List<Battalion> battalions = battalionRepo.findBattalionsWithoutCompanies();
		ViewHelper.printResults(ViewHelper.battalionsToPersistable(battalions), view);
	}

	@Override
	public String getName() {
		return "FindBattalionsWithoutCompanies";
	}

}
