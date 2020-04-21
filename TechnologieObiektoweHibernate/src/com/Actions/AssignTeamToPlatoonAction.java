package com.Actions;

import com.Entities.Platoon;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.PlatoonRepo;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignTeamToPlatoonAction implements Action{

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
		
		platoonRepo.updatePlatoon(p);
		teamRepo.updateTeam(t);
	}

	private void assignTeamToPlatoon(Platoon p, Team t) {
		t.setPlatoon(p);
		p.getTeams().add(t);
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
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling AssignTeamToPlatoon");
		}
	}
	
	@Override
	public String getName() {
		return "AssignTeamToPlatoon";
	}

}
