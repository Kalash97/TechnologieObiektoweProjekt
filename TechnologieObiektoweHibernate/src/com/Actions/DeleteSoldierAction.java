package com.Actions;

import com.Entities.Soldier;
import com.Entities.Team;
import com.Entities.Weapon;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Repos.WeaponRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSoldierAction implements Action {

	private View view;
	private SoldierRepo soldierRepo;
	private WeaponRepo weaponRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {

		String line;
		Soldier s;
		Weapon w;
		Team t;
		
		do {
			do {
				view.print("Podaj id ¿o³nierza do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));

		if(s.getWeapons().size()>0) {
			for(int i=0; i<s.getWeapons().size();i++) {
				long id = s.getWeapons().get(i).getId();
				w = weaponRepo.findById(id);
				w.setSoldier(null);
				weaponRepo.updateWeapon(w);
			}
			s.getWeapons().clear();
			soldierRepo.updateSoldier(s);
		}
		
		if(s.getTeam()!=null) {
			t = teamRepo.findById(s.getTeam().getId());
			if(t.getCommander().getId()==s.getId()) {
				t.setCommander(null);
				t.getSoldiers().remove(s);	
				teamRepo.updateTeam(t);
			}
			soldierRepo.updateSoldier(s);
		}		
		soldierRepo.deleteSoldier(s);
	}

	@Override
	public String getName() {
		return "DeleteSoldier";
	}

}
