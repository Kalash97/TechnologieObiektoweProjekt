package com.controller.actions.PlatoonActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonsWithoutCommanderAction implements Action{

	private View view;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {
		List<Platoon> platoons = platoonRepo.findPlatoonsWithoutCommander();
		//showPlatoons(platoons);
		ViewHelper.printResults(ViewHelper.platoonsToPersistable(platoons), view);
	}

//	private void showPlatoons(List<Platoon> platoons) {
//		if(platoons.size()>0) {
//			for(int i=0; i<platoons.size();i++) {
//				Platoon p = platoons.get(i);
//				view.print(i + ": ID: "+p.getId()+", Numer: "+p.getNumber());
//			}
//		}else {
//			view.print("Brak plutonów bez dowódcy");
//		}
//	}

	@Override
	public String getName() {
		return "FindPlatoonsWithoutCommander";
	}

}
