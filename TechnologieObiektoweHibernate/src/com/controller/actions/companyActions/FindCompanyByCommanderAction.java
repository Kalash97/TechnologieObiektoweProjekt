package com.controller.actions.companyActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.entities.Soldier;
import com.model.repos.CompanyRepo;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompanyByCommanderAction extends Action {

	private View view;
	private SoldierRepo soldierRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {
		String name = view.readProperty("Podaj imiê");
		String lastName = view.readProperty("Podaj nazwisko");
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for (Soldier s : soldiers) {
			List<Company> companies = companyRepo.findCompanyOfCommander(s);
			ViewHelper.printResults(s.getFullName(),companies, view);
		}
	}

	@Override
	public String getName() {
		return "FindCompanyByCommander";
	}

}
