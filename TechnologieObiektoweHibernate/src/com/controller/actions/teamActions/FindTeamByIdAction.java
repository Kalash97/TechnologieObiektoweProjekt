package com.controller.actions.teamActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamByIdAction implements Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {
		Team t;
		t = findValidTeam();

		ViewHelper.printResults(Arrays.asList(t), view);
		view.printDelimeter();
		
		ViewHelper.printResults("-----¯o³nierze:",t.getSoldiers(), view);	
		view.printDelimeter();
	}

	private Team findValidTeam() {

		while (true) {
			long id = view.getValidNumberCancellable("Podaj ID dru¿yny");
			Team t = repo.findById(id);
			if(t!=null) {
				return t;
			}
		}
	}

	@Override
	public String getName() {
		return "FindTeamById";
	}

}
