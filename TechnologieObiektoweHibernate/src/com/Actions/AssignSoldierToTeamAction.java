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
public class AssignSoldierToTeamAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private TeamRepo teamRepo;
	
	@Override
	public void launch() {
		Team t;
		Soldier s;
		
		t = getValidTeam();
		
		s = getValidSoldier();
		
		assignSoldierToTeam(t, s);
		
		teamRepo.updateTeam(t);
		soldierRepo.updateSoldier(s);
	}

	private void assignSoldierToTeam(Team t, Soldier s) {
		s.setTeam(t);
		t.getSoldiers().add(s);
	}

	private Soldier getValidSoldier() {
		Soldier s;
		String line;
		do {
			do {
				view.print("Podaj id ¿o³nierza do przypisania dru¿yny.(s³owo <<cancel>> zawraca)");
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
				view.print("Podaj id dru¿yny do przypisania ¿o³nierza.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling assigningSoldier");
		}
	}
	
	@Override
	public String getName() {
		return "AssignSoldierToTeam";
	}

}
