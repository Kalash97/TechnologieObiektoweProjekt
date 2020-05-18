package com.controller.actions.platoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.entities.Team;
import com.model.repos.PlatoonRepo;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePlatoonAction implements Action {

	private View view;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {

		Platoon p = RepoUtil.getValidPlatoon(view, platoonRepo);
		
		removeCommanderFromPlatoon(p);
		removeTeamsFromPlatoon(p);
		
		platoonRepo.deletePlatoon(p);
	}

	private void removeTeamsFromPlatoon(Platoon p) {
		Team t;
		if(p.getTeams().size()>0) {
			for(int i=0; i<p.getTeams().size();i++) {
				t=p.getTeams().get(i);
				t.setPlatoon(null);
				teamRepo.updateTeam(t);
			}
			platoonRepo.updatePlatoon(p);
		}
	}
	
	private void removeCommanderFromPlatoon(Platoon p) {
		if(p.getCommander()!=null) {
			p.setCommander(null);
			platoonRepo.updatePlatoon(p);
		}
	}

	@Override
	public String getName() {
		return "DeletePlatoon";
	}

}
