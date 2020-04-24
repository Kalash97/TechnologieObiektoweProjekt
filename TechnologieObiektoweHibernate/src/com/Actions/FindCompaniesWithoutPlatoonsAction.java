package com.Actions;

import java.util.List;

import com.Entities.Company;
import com.Repos.CompanyRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompaniesWithoutPlatoonsAction implements Action{

	private View view;
	private CompanyRepo companyRepo;
	
	@Override
	public void launch() {
		List<Company> companies = companyRepo.findCompaniesWithoutPlatoons();
		showCompanies(companies);
	}

	private void showCompanies(List<Company> companies) {
		if(companies.size()>0) {
			for(int i=0; i<companies.size();i++) {
				Company c = companies.get(i);
				view.print(i + ": ID: "+c.getId()+", Numer: "+c.getNumber());
			}
		}else {
			view.print("Brak kompanii bez plutonów");
		}
	}

	@Override
	public String getName() {
		return "FindCompaniesWithoutPlatoons";
	}

}
