package com.controller.actions.SoldierActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldiersWithoutTeamAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	
	@Override
	public void launch() {
		List<Soldier> soldiers = soldierRepo.findSoldiersWithoutTeam();
		//showSoldiers(soldiers);
		ViewHelper.printResults(ViewHelper.soldiersToPersistable(soldiers), view);
	}

//	private void showSoldiers(List<Soldier> soldiers) {
//		if(soldiers.size()>0) {
//			for(int i=0; i<soldiers.size();i++) {
//				Soldier s = soldiers.get(i);
//				view.print(i+": ID: "+ s.getId()+", Imiê: "+s.getName()+", Nazwisko: "+s.getLastName()+", Stopieñ: "+ s.getRank());
//			}
//		}else {
//			view.print("Brak nieprzypisanch ¿o³nierzy.");
//		}
//	}
	
	@Override
	public String getName() {
		return "FindSoldiersWithoutTeam";
	}

}
