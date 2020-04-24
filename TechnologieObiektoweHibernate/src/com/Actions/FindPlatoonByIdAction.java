package com.Actions;

import java.util.List;

import com.Entities.Company;
import com.Entities.Platoon;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.PlatoonRepo;
import com.Utils.ValidUtil;
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
		
		view.print("Znaleziony pluton:");
		view.print("Numer: " + p.getNumber());
		view.print("Dowódca:: Imiê: " + p.getCommander().getName()+", Nazwisko: "+p.getCommander().getLastName()+", Stopieñ: "+p.getCommander().getRank());
		Company c = p.getCompany();
		if(c!=null) {
			view.print("Kompania:: Id:" + c.getId() + " Numer: " + c.getNumber());
		}else {
			view.print("Kompania: "+c);
		}
		view.print("Dru¿yny w plutonie:");
		List<Team> list = p.getTeams();
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				view.print(i+ ": " + " ID: "+list.get(i).getId()+ ", Nr. dru¿yny: "+ list.get(i).getNumber());
			}
		}
		view.print("");
	}

	private Platoon findValidPlatoon() {
		String line;
		Platoon p;
		do {
			do {
				view.print("Podaj id plutonu do znalezienia.(s³owo <<cancel>> zawraca)");
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
		return "FindPlatoonById";
	}

}
