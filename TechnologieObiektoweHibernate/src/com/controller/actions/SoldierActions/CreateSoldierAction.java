package com.controller.actions.SoldierActions;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.RepoUtil;
import com.utils.enums.BloodType;
import com.utils.enums.Rank;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSoldierAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {
		Soldier s = new Soldier();
		
		view.print("Podaj imi�");
		s.setName(view.read());
		
		view.print("Podaj nazwisko");
		s.setLastName(view.read());		
		
		view.print("Dost�pne grupy krwi");
		view.print(BloodType.values());
		s.setBloodType(RepoUtil.getValidBloodType(view));
			
			
		view.print("Dost�pne stopnie");
		view.print(Rank.values());
		s.setRank(RepoUtil.getValidRank(view));
	
		repo.createSoldier(s);
	}

	@Override
	public String getName() {
		return "CreateSoldier";
	}

}
