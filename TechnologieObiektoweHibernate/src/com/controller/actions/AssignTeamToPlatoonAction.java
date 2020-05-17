package com.controller.actions;

import com.model.entities.Platoon;
import com.model.entities.Team;
import com.model.repos.PlatoonRepo;
import com.model.repos.TeamRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignTeamToPlatoonAction implements Action {

	private View view;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {
		Platoon p;
		Team t;

		p = getValidPlatoon();
		t = getValidTeam();

		assignTeamToPlatoon(p, t);
		
	}

	private void assignTeamToPlatoon(Platoon p, Team t) {
		if (!platoonContainsTeam(p, t)) {
			t.setPlatoon(p);
			p.getTeams().add(t);
			platoonRepo.updatePlatoon(p);
			teamRepo.updateTeam(t);
		}
	}

	private boolean platoonContainsTeam(Platoon p, Team t) {
		for (int i = 0; i < p.getTeams().size(); i++) {
			if (p.getTeams().get(i).getNumber() == t.getNumber()) {
				view.print("Druzyna o podanym numerze ju¿ istnieje w plutonie!");
				return true;
			}
		}
		return false;
	}

	private Platoon getValidPlatoon() {
		String line;
		Platoon p;
		do {
			do {
				System.out.println("Podaj id plutonu do przypisania dru¿yny.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			p = platoonRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		return p;
	}

	private Team getValidTeam() {
		Team t;
		String line;
		do {
			do {
				view.print("Podaj id dru¿yny do przypisania plutonu.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}

	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling AssignTeamToPlatoon");
		}
	}

	@Override
	public String getName() {
		return "AssignTeamToPlatoon";
	}

}
