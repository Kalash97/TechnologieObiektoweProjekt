package com.Actions.PlatoonActions;

import java.util.Arrays;
import java.util.List;

import com.Actions.Action;
import com.Entities.Company;
import com.Entities.Platoon;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.PlatoonRepo;
import com.Utils.ValidUtil;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonByIdAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p;
		p = findValidPlatoon();
		
		ViewHelper.printResults(Arrays.asList(p), view);
		view.print("");
		
		try {
			view.print("-----Kompania:");
			ViewHelper.printResults(Arrays.asList(p.getCompany()), view);
		}catch (NullPointerException e) {
			view.print("Brak kompanii");
		}
		view.print("");
		
		view.print("-----Dru¿yny:");
		ViewHelper.printResults(ViewHelper.teamsToPersistable(p.getTeams()), view);		
		view.print("");
	}

	private Platoon findValidPlatoon() {
//		String line;
//		Platoon p;
//		do {
//			do {
//				view.print("Podaj id plutonu do znalezienia.(s³owo <<cancel>> zawraca)");
//				line = view.read();
//				canceling(line);
//			} while (!ValidUtil.isLongInstance(line));
//			p = repo.findById(Long.parseLong(line));
//		} while (!ValidUtil.isValid(p));
//		return p;
		
		while(true) {
			long id = view.getValidNumberCancellable("Podaj ID plutonu");
			Platoon p = repo.findById(id);
			if(p!=null) {
				return p;
			}
		}
	}

//	private void canceling(String line) {
//		if("cancel".equals(line)) {
//			throw new OperationCancelException("canceling findSoldier");
//		}
//	}
	
	@Override
	public String getName() {
		return "FindPlatoonById";
	}

}
