package com.Actions.PlatoonActions;

import com.Actions.Action;
import com.Entities.Platoon;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.PlatoonRepo;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePlatoonAction implements Action {

	private View view;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {
		//TO DO detaching entities

		Platoon p;

		p = getValidPlatoon();

		removeCommanderFromPlatoon(p);
		removeTeamsFromPlatoon(p);
		
		platoonRepo.deletePlatoon(p);
	}

	private void removeTeamsFromPlatoon(Platoon p) {
		Team t;
		if(p.getTeams().size()>0) {
			for(int i=0; i<p.getTeams().size();i++) {
				t=p.getTeams().get(i);
				t.setPlatoon(null);
				teamRepo.updateTeam(t);
			}
			platoonRepo.updatePlatoon(p);
		}
	}
	
	private void removeCommanderFromPlatoon(Platoon p) {
		if(p.getCommander()!=null) {
			p.setCommander(null);
			platoonRepo.updatePlatoon(p);
		}
	}
	
	private Platoon getValidPlatoon() {
		String line;
		Platoon p;
		do {
			do {
				System.out.println("Podaj id plutonu do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			p = platoonRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		return p;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling deletePlatoon");
		}
	}
	
	@Override
	public String getName() {
		return "DeletePlatoon";
	}

}
