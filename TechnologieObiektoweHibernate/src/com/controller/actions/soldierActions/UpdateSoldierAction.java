package com.controller.actions.soldierActions;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.RepoUtil;
import com.utils.enums.BloodType;
import com.utils.enums.Rank;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateSoldierAction implements Action {

	private View view;
	private SoldierRepo repo;

	@Override
	public void launch() {
		Soldier s = RepoUtil.getValidSoldier(view, repo);
		view.print("Podaj nowe imi�.(Zostaw puste je�li nie chcesz zmienia�)");
		String line = view.read();
		if (!line.equals("")) {
			s.setName(line);
		}

		view.print("Podaj nowe nazwisko.(Zostaw puste je�li nie chcesz zmienia�)");
		line = view.read();
		if (!line.equals("")) {
			s.setLastName(line);
		}

		view.print("Podaj now� grup� krwi.(Zostaw puste je�li nie chcesz zmienia�)");
		view.print("Dost�pne grupy krwi");
		view.print(BloodType.values());
		line = getBloodType();
		if (!line.equals("")) {
			s.setBloodType(BloodType.valueOf(line.toUpperCase()));
		}

		view.print("Podaj nowy stopie�.(Zostaw puste je�li nie chcesz zmienia�)");
		view.print("Dost�pne stopnie");
		view.print(Rank.values());
		line = getRank();
		if (!line.equals("")) {
			s.setRank(Rank.valueOf(line.toUpperCase()));
		}

		repo.updateSoldier(s);
	}

	private String getRank() {
		while (true) {
			try {
				String rank = view.read();
				return ("".equals(rank) || Rank.valueOf(rank) instanceof Rank) ? rank : "";
			} catch (Exception e) {

			}
		}
	}

	private String getBloodType() {
		while (true) {
			try {
				String bloodType = view.read();
				return ("".equals(bloodType) || BloodType.valueOf(bloodType) instanceof BloodType) ? bloodType : "";
			} catch (Exception e) {

			}
		}
	}

	@Override
	public String getName() {
		return "UpdateSoldier";
	}

}
