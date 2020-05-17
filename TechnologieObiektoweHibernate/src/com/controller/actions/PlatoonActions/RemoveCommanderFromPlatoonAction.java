package com.controller.actions.PlatoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromPlatoonAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = findValidPlatoon();
		
		p.setCommander(null);
		repo.updatePlatoon(p);
	}

	private Platoon findValidPlatoon() {
		String line;
		Platoon p;
		do {
			do {
				view.print("Podaj id plutonu do usuniêcia dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			p = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		return p;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling findSoldier");
		}
	}
	
	@Override
	public String getName() {
		return "RemoveCommanderFromPlatoon";
	}

}
