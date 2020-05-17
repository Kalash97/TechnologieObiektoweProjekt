package com.controller.actions;

import com.model.entities.Company;
import com.model.entities.Platoon;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignPlatoonToCompanyAction implements Action {

	private View view;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;

	@Override
	public void launch() {
		Company c = getValidCompany();
		Platoon p = getValidPlatoon();

		assignPlatoonToCompany(c, p);		
	}

	public void assignPlatoonToCompany(Company c, Platoon p) {
		if (!companyContainsPlatoon(c, p)) {
			p.setCompany(c);
			c.getPlattons().add(p);
			companyRepo.updateCompany(c);
			platoonRepo.updatePlatoon(p);
		}
	}

	private boolean companyContainsPlatoon(Company c, Platoon p) {
		for (int i = 0; i < c.getPlattons().size(); i++) {
			if (c.getPlattons().get(i).getNumber() == p.getNumber()) {
				view.print("Pluton o podanym numerze ju� istnieje w kompanii!");
				return true;
			}
		}
		return false;
	}

	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				System.out.println("Podaj id kompanii do przypisania plutonu.(s�owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = companyRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}

	private Platoon getValidPlatoon() {
		String line;
		Platoon p;
		do {
			do {
				System.out.println("Podaj id plutonu do przypisania kompanii.(s�owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			p = platoonRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		return p;
	}

	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling AssignPlatoonToCompany");
		}
	}

	@Override
	public String getName() {
		return "AssignPlatoonToCompany";
	}

}