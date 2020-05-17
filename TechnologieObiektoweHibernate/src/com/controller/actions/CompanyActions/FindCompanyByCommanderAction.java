package com.controller.actions.CompanyActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.entities.Soldier;
import com.model.repos.CompanyRepo;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompanyByCommanderAction implements Action {

	private View view;
	private SoldierRepo soldierRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {
		view.print("Podaj imiê");
		String name = view.read();

		view.print("Podaj nazwisko");
		String lastName = view.read();
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for (Soldier s : soldiers) {
			List<Company> companies = companyRepo.findCompanyOfCommander(s);
			Set<Company> companiesSet = new HashSet<Company>();
			for (Company c : companies) {
				companiesSet.add(c);
			}
			ViewHelper.printResults(companiesSet, view);
		}
	}

	@Override
	public String getName() {
		return "FindCompanyByCommander";
	}

}
