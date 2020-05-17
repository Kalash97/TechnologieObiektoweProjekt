package com.controller.actions;

import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.utils.ValidUtil;
import com.utils.enums.Rank;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignSoldierToTeamAction implements Action {

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

		
	}

	private void assignSoldierToTeam(Team t, Soldier s) {
		if (ValidUtil.isRankLowerOrEqual(s, Rank.MASTER_SERGEANT)) {
			s.setTeam(t);
			t.getSoldiers().add(s);
			teamRepo.updateTeam(t);
			soldierRepo.updateSoldier(s);
		} else {
			view.print("�o�nierz ma nieodpowiedni stopie�");
			return;
		}
	}

	private Soldier getValidSoldier() {
		Soldier s;
		String line;
		do {
			do {
				view.print("Podaj id �o�nierza do przypisania dru�yny.(s�owo <<cancel>> zawraca)");
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
				view.print("Podaj id dru�yny do przypisania �o�nierza.(s�owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}

	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling assigningSoldier");
		}
	}

	@Override
	public String getName() {
		return "AssignSoldierToTeam";
	}

}
