package com.Actions;

import java.util.List;

import com.Entities.Battalion;
import com.Entities.Company;
import com.Entities.Platoon;
import com.Repos.CompanyRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompanyByIdAction implements Action{

	private View view;
	private CompanyRepo repo;
	
	@Override
	public void launch() {
		String line;
		Company c;
		
		do {
			do {
				view.print("Podaj id kompanii do znalezienia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			c = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		
		view.print("Znaleziona kompania:");
		view.print("Numer: " + c.getNumber());
		view.print("Dowódca: " + c.getCommander());
		Battalion b = c.getBattalion();
		if(b!=null) {
			view.print("Batalion: Id:" + b.getId() + " Numer: " + b.getNumber());
		}else {
			view.print("Batalion: "+b);
		}
		view.print("Plutony w kompanii:");
		List<Platoon> list = c.getPlattons();
		if(list.size()>0) {
			for(int i=0; i<list.size(); i++) {
				view.print("Pluton nr.: " + i + " ID: "+list.get(i).getId());
			}
		}
		view.print("");
	}

	@Override
	public String getName() {
		return "FindCompanyById";
	}

}
