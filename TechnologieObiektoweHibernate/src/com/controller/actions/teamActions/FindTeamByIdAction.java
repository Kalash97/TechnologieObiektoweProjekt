package com.controller.actions.teamActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamByIdAction implements Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {
		Team t = RepoUtil.getValidTeam(view, repo);

		ViewHelper.printResults(Arrays.asList(t), view);
		view.print("");
		
		view.print("-----¯o³nierze:");
		ViewHelper.printResults(t.getSoldiers(), view);	
		view.print("");
	}	

	@Override
	public String getName() {
		return "FindTeamById";
	}

}
