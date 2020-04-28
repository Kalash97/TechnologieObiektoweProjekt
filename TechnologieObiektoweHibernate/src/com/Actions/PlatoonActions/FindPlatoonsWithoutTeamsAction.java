package com.Actions.PlatoonActions;

import java.util.List;

import com.Actions.Action;
import com.Entities.Platoon;
import com.Repos.PlatoonRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonsWithoutTeamsAction implements Action{

	private View view;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {
		List<Platoon> platoons = platoonRepo.findPlatoonsWithoutTeams();
		showPlatoons(platoons);
	}

	private void showPlatoons(List<Platoon> platoons) {
		if(platoons.size()>0) {
			for(int i=0; i<platoons.size();i++) {
				Platoon p = platoons.get(i);
				view.print(i + ": ID: "+p.getId()+", Numer: "+p.getNumber());
			}
		}else {
			view.print("Brak plutonów bez dru¿yn");
		}
	}

	@Override
	public String getName() {
		return "FindPlatoonsWithoutTeams";
	}

}
