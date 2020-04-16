package com.Actions;

import com.Entities.Soldier;
import com.Entities.Team;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTeamAction implements Action {

	private View view;
	private TeamRepo teamRepo;
	private SoldierRepo soldierRepo;

	@Override
	public void launch() {

		String line;
		Team t;
		Soldier s;

		do {
			do {
				view.print("Podaj id dru¿yny do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		
		if(t.getCommander()!=null) {
			s = soldierRepo.findById(t.getCommander().getId());
			t.setCommander(null);
			soldierRepo.updateSoldier(s);
		}
		
		if(t.getSoldiers().size()>0) {
			for(int i=0; i<t.getSoldiers().size();i++) {
				long id = t.getSoldiers().get(i).getId();
				s=soldierRepo.findById(id);
				s.setTeam(null);
				soldierRepo.updateSoldier(s);
			}
			teamRepo.updateTeam(t);
		}
		teamRepo.deleteTeam(t);
	}

	@Override
	public String getName() {
		return "DeleteTeam";
	}

}
