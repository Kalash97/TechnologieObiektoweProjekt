package com.Actions;

import java.util.List;

import com.Entities.Soldier;
import com.Repos.SoldierRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldiersWithoutWeaponAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	
	@Override
	public void launch() {
		List<Soldier> unarmedSoldiers = soldierRepo.findSoldiersWithoutWeapon();
		showUnarmedSoldiers(unarmedSoldiers);
	}
	
	private void showUnarmedSoldiers(List<Soldier> unarmedSoldiers) {
		if(unarmedSoldiers.size()>0) {
			for(int i=0; i<unarmedSoldiers.size();i++) {
				Soldier s = unarmedSoldiers.get(i);
				view.print(i+": ID: "+ s.getId()+", Imiê: "+s.getName()+", Nazwisko: "+s.getLastName()+", Stopieñ: "+ s.getRank());
			}
		}else {
			view.print("Brak nieuzbrojonych ¿o³nierzy.");
		}
	}

	@Override
	public String getName() {
		return "FindSoldiersWithoutWeapon";
	}

}
