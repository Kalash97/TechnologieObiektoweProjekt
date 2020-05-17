package com.controller.actions;

import com.model.entities.Platoon;
import com.model.entities.Team;
import com.model.repos.PlatoonRepo;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignTeamToPlatoonAction implements Action {

	private View view;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {
		Platoon p = RepoUtil.getValidPlatoon(view, platoonRepo);
		Team t = RepoUtil.getValidTeam(view, teamRepo);

		assignTeamToPlatoon(p, t);
		
	}

	private void assignTeamToPlatoon(Platoon p, Team t) {
		if (!RepoUtil.platoonContainsTeam(p, t)) {
			t.setPlatoon(p);
			p.getTeams().add(t);
			platoonRepo.updatePlatoon(p);
			teamRepo.updateTeam(t);
		}else {
			view.print("Dru¿yna o podanym numerze ju¿ istnieje w plutonie!");
		}
	}

	@Override
	public String getName() {
		return "AssignTeamToPlatoon";
	}

}
