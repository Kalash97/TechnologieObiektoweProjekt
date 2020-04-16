package com.Actions;

import com.Entities.Soldier;
import com.Entities.Team;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommanderToTeamAction implements Action{

	private View view;
	private TeamRepo teamRepo;
	private SoldierRepo soldierRepo;
	
	@Override
	public void launch() {	
		Team t;
		Soldier s;
		String line;
		
		do {
			do {
				view.print("Podaj id dru¿yny do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		
		for(int i=0; i<t.getSoldiers().size(); i++) {
			System.out.println(t.getSoldiers().get(i));
		}
		
		do {
			do {
				view.print("Podaj id nowego dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		
		if(t.getSoldiers().contains(s)) {
			t.setCommander(s);
		}else {
			s.setTeam(t);
			t.getSoldiers().add(s);
			t.setCommander(s);
		}
		
		teamRepo.updateTeam(t);
		soldierRepo.updateSoldier(s);
	}

	@Override
	public String getName() {
		return "AssignCommanderToTeam";
	}

}
