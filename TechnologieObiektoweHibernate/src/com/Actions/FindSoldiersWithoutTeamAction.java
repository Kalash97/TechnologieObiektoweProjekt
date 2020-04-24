package com.Actions;

import java.util.List;

import com.Entities.Soldier;
import com.Repos.SoldierRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldiersWithoutTeamAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	
	@Override
	public void launch() {
		List<Soldier> soldiers = soldierRepo.findSoldiersWithoutTeam();
		showSoldiers(soldiers);
	}

	private void showSoldiers(List<Soldier> soldiers) {
		if(soldiers.size()>0) {
			for(int i=0; i<soldiers.size();i++) {
				Soldier s = soldiers.get(i);
				view.print(i+": ID: "+ s.getId()+", Imiê: "+s.getName()+", Nazwisko: "+s.getLastName()+", Stopieñ: "+ s.getRank());
			}
		}else {
			view.print("Brak nieprzypisanch ¿o³nierzy.");
		}
	}
	
	@Override
	public String getName() {
		return "FindSoldiersWithoutTeamAction";
	}

}
