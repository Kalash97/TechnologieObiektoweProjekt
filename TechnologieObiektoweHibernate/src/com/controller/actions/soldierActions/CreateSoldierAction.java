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
public class CreateSoldierAction extends Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {
		Soldier s = new Soldier();
		
		s.setName(view.readProperty("Podaj imiê"));
		
		s.setLastName(view.readProperty("Podaj nazwisko"));		
		
		view.print("Dostêpne grupy krwi");
		view.print(BloodType.values());
		s.setBloodType(RepoUtil.getValidBloodType(view));
			
			
		view.print("Dostêpne stopnie");
		view.print(Rank.values());
		s.setRank(RepoUtil.getValidRank(view));
	
		repo.create(s);
	}

	@Override
	public String getName() {
		return "CreateSoldier";
	}

}
