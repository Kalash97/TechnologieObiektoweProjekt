package com.Actions;

import java.util.List;

import com.Entities.Battalion;
import com.Entities.Company;
import com.Repos.BattalionRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionByIdAction implements Action{

	private View view;
	private BattalionRepo repo;
	
	@Override
	public void launch() {
		String line;
		Battalion b;
		
		do {
			do {
				view.print("Podaj id batalionu do znalezienia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			b = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		
		view.print("Znaleziony batalion:");
		view.print("Numer: " + b.getNumber());
		view.print("Dowódca: " + b.getCommander());
		view.print("Kompanie w batalionie:");
		List<Company> list = b.getCompanies();
		if(list.size()>0) {
			for(int i=0; i<list.size(); i++) {
				view.print("Kompania nr.: " + i + " ID: "+list.get(i).getId());
			}
		}
		view.print("");
	}

	@Override
	public String getName() {
		return "FindBattalionById";
	}

}
