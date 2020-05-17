package com.controller.actions.SoldierActions;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.ValidUtil;
import com.utils.enums.BloodType;
import com.utils.enums.Rank;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateSoldierAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {
		Soldier s = findValidSoldier();
		view.print("Podaj nowe imiê.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		String line = view.read();
		if(!line.equals("")) {
			s.setName(line);
		}
		
		view.print("Podaj nowe nazwisko.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		line = view.read();
		if(!line.equals("")) {
			s.setLastName(line);
		}
		
		view.print("Podaj now¹ grupê krwi.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		view.print("Dostêpne grupy krwi");
		view.print(BloodType.values());
		line=getBloodType();
		if(!line.equals("")) {
			s.setBloodType(BloodType.valueOf(line.toUpperCase()));
		}
		
		view.print("Podaj nowy stopieñ.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		view.print("Dostêpne stopnie");
		view.print(Rank.values());
		line=getRank();
		if(!line.equals("")) {
			s.setRank(Rank.valueOf(line.toUpperCase()));
		}
		
		repo.updateSoldier(s);
	}

	private Soldier findValidSoldier() {
		String line;
		Soldier s;
		do {
			do {
				view.print("Podaj id ¿o³nierza do modyfikacji.");
				line = view.read();	
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}

	private String getRank() {
		String rankStr;
		do {
			view.print("Podaj stopieñ");
			rankStr=view.read();
			if(rankStr.equals("")) {
				break;
			}
		}while(!ValidUtil.isValid(rankStr, Rank.values()));
		return rankStr;
	}
	
	private String getBloodType() {
		String bloodTypeStr;
		do {
			view.print("Podaj grupê krwi");
			bloodTypeStr = view.read();
			if(bloodTypeStr.equals("")) {
				break;
			}
		}while(!ValidUtil.isValid(bloodTypeStr, BloodType.values()));
		return bloodTypeStr;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling updateSoldier");
		}
	}
	
	@Override
	public String getName() {
		return "UpdateSoldier";
	}

}
