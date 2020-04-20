package com.Actions;

import com.Entities.Soldier;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
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
		
		t = getValidTeam();
		
		for(int i=0; i<t.getSoldiers().size(); i++) {
			System.out.println(t.getSoldiers().get(i));
		}
		
		s = getValidSoldier();
		
		assignCommanderToTeam(t, s);	
		teamRepo.updateTeam(t);
		soldierRepo.updateSoldier(s);
	}

	private void assignCommanderToTeam(Team t, Soldier s) {
		if(t.getSoldiers().contains(s)) {
			t.setCommander(s);
		}else {
			s.setTeam(t);
			t.getSoldiers().add(s);
			t.setCommander(s);
		}
	}

	private Soldier getValidSoldier() {
		Soldier s;
		String line;
		do {
			do {
				view.print("Podaj id nowego dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}

	private Team getValidTeam() {
		Team t;
		String line;
		do {
			do {
				view.print("Podaj id dru¿yny do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling assignCommander");
		}
	}
	
	@Override
	public String getName() {
		return "AssignCommanderToTeam";
	}

}
