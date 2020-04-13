package com.Actions;

import com.Entities.Soldier;
import com.Enums.BloodType;
import com.Enums.Rank;
import com.Repos.SoldierRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSoldierAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {
		Soldier s = new Soldier();
		
		view.print("Podaj imiê");
		s.setName(view.read());
		
		view.print("Podaj nazwisko");
		s.setLastName(view.read());		
		
		view.print("Dostêpne grupy krwi");
		view.print(BloodType.values());
		
		String bloodTypeStr;
		do {
			view.print("Podaj grupê krwi");
			bloodTypeStr = view.read();
			
		}while(!ValidUtil.isValid(bloodTypeStr, BloodType.values()));
		
		s.setBloodType(BloodType.valueOf(bloodTypeStr.toUpperCase()));
			
			
		view.print("Dostêpne stopnie");
		view.print(Rank.values());
		
		String rankStr;
		do {
			view.print("Podaj stopieñ");
			rankStr=view.read();
			
		}while(!ValidUtil.isValid(rankStr, Rank.values()));
			
		s.setRank(Rank.valueOf(rankStr.toUpperCase()));
	
		repo.createSoldier(s);
	}

	@Override
	public String getName() {
		return "CreateSoldier";
	}

}
