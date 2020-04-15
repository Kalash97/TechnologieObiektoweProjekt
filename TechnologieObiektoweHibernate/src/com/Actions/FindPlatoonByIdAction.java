package com.Actions;

import java.util.List;

import com.Entities.Company;
import com.Entities.Platoon;
import com.Entities.Team;
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
		String line;
		Platoon p;
		do {
			do {
				view.print("Podaj id plutonu do znalezienia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			p = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		
		view.print("Znaleziony pluton:");
		view.print("Numer: " + p.getNumber());
		view.print("Dowódca: " + p.getCommander());
		Company c = p.getCompany();
		if(c!=null) {
			view.print("Kompania: Id:" + c.getId() + " Numer: " + c.getNumber());
		}else {
			view.print("Kompania: "+c);
		}
		view.print("Dru¿yny w plutonie:");
		List<Team> list = p.getTeams();
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				view.print("Dru¿yna nr.: " + i + " ID: "+list.get(i).getId());
			}
		}
		view.print("");
	}

	@Override
	public String getName() {
		return "FindPlatoonById";
	}

}
