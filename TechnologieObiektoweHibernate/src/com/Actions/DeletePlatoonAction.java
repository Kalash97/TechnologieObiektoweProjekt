package com.Actions;

import com.Entities.Platoon;
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

		String line;
		Platoon p;

		do {
			do {
				System.out.println("Podaj id plutonu do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			p = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));

		repo.deletePlatoon(p);
	}

	@Override
	public String getName() {
		return "DeletePlatoon";
	}

}
