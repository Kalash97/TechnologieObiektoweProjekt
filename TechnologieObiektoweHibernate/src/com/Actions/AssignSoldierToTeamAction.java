package com.Actions;

import com.Entities.Soldier;
import com.Entities.Team;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignSoldierToTeamAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private TeamRepo teamRepo;
	
	@Override
	public void launch() {
		Team t;
		Soldier s;
		String line;
		
		do {
			do {
				view.print("Podaj id dru¿yny do przypisania ¿o³nierza.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		
		do {
			do {
				view.print("Podaj id ¿o³nierza do przypisania dru¿yny.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		
		s.setTeam(t);
		t.getSoldiers().add(s);
		
		teamRepo.updateTeam(t);
		soldierRepo.updateSoldier(s);
	}

	@Override
	public String getName() {
		return "AssignSoldierToTeam";
	}

}
