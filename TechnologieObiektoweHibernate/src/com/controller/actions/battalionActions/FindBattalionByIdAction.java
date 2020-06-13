package com.controller.actions.battalionActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.RepoUtil;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionByIdAction extends Action {

	private View view;
	private BattalionRepo repo;

	@Override
	public void launch() {
		Battalion b = RepoUtil.getValidBattalion(view,repo);
		ViewHelper.printResults(Arrays.asList(b), view);
		ViewHelper.printResults("Companies:", b.getCompanies(), view);
		view.printDelimeter();
	}

	@Override
	public String getName() {
		return "FindBattalionById";
	}

}
