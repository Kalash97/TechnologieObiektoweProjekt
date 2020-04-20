package com.Actions;

import com.Entities.Platoon;
import com.Exceptions.OperationCancelException;
import com.Repos.PlatoonRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePlatoonAction implements Action {

	private View view;
	private PlatoonRepo repo;

	@Override
	public void launch() {
		//TO DO detaching entities

		Platoon p;

		p = getValidPlatoon();

		repo.deletePlatoon(p);
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
			p = repo.findById(Long.parseLong(line));
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
