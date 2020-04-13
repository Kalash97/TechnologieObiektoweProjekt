package com.Actions;

import com.Entities.Company;
import com.Repos.CompanyRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCompanyAction implements Action{

	private View view;
	CompanyRepo repo;
	
	@Override
	public void launch() {
		view.print("Podaj id kompanii do usuniêcia.(s³owo <<cancel>> zawraca)");
		
		String line = view.read();
		if (line.equals("cancel")) {
			return;
		}
		
		Company c =repo.findById(Long.parseLong(line));
		
		repo.deleteCompany(c);
	}

	@Override
	public String getName() {
		return "DeleteCompany";
	}

}
