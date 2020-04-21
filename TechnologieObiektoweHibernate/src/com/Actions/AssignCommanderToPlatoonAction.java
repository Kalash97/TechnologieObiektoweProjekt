package com.Actions;

import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Exceptions.OperationCancelException;
import com.Repos.PlatoonRepo;
import com.Repos.SoldierRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommanderToPlatoonAction implements Action{
	
	private View view;
	private SoldierRepo soldierRepo;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {
		Platoon p;
		Soldier s;
		
		p = getValidPlatoon();
		s = getValidSoldier();
		assignCommanderToPlatoon(p, s);
		platoonRepo.updatePlatoon(p);
	}
	
	private void assignCommanderToPlatoon(Platoon p, Soldier s) {
		p.setCommander(s);
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
	
	private Platoon getValidPlatoon() {
		Platoon p;
		String line;
		do {
			do {
				view.print("Podaj id plutonu do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			p = platoonRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		return p;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling assignCommander");
		}
	}

	@Override
	public String getName() {
		return "AssignCommanderToPlatoon";
	}

}
