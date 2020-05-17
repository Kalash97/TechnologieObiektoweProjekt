package com.controller.actions.TeamActions;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTeamAction implements Action {

	private View view;
	private TeamRepo teamRepo;
	private SoldierRepo soldierRepo;

	@Override
	public void launch() {

		Team t;

		t = getValidTeam();
		
		removeCommanderFromTeam(t);
		
		removeSoldiersFromTeam(t);
		teamRepo.deleteTeam(t);
	}

	private void removeSoldiersFromTeam(Team t) {
		Soldier s;
		if(t.getSoldiers().size()>0) {
			for(int i=0; i<t.getSoldiers().size();i++) {
				s=t.getSoldiers().get(i);
				s.setTeam(null);
				soldierRepo.updateSoldier(s);
			}
			teamRepo.updateTeam(t);
		}
	}

	private void removeCommanderFromTeam(Team t) {
		Soldier s;
		if(t.getCommander()!=null) {
			s = t.getCommander();
			t.setCommander(null);
			soldierRepo.updateSoldier(s);
		}
	}

	private Team getValidTeam() {
		String line;
		Team t;
		do {
			do {
				view.print("Podaj id dru¿yny do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling deleteTeam");
		}
	}

	@Override
	public String getName() {
		return "DeleteTeam";
	}

}
