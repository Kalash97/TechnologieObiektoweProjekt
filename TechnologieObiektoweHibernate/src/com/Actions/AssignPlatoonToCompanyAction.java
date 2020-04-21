package com.Actions;

import com.Entities.Company;
import com.Entities.Platoon;
import com.Exceptions.OperationCancelException;
import com.Repos.CompanyRepo;
import com.Repos.PlatoonRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignPlatoonToCompanyAction implements Action{

	private View view;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {
		Company c = getValidCompany();
		Platoon p = getValidPlatoon();
		
		assignPlatoonToCompany(c, p);
		
		companyRepo.updateCompany(c);
		platoonRepo.updatePlatoon(p);
	}

	public void assignPlatoonToCompany(Company c, Platoon p) {
		p.setCompany(c);
		c.getPlattons().add(p);
	}
	
	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				System.out.println("Podaj id kompanii do przypisania plutonu.(s³owo <<cancel>> zawraca)");
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
				System.out.println("Podaj id plutonu do przypisania kompanii.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			p = platoonRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		return p;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling AssignPlatoonToCompany");
		}
	}
	
	@Override
	public String getName() {
		return "AssignPlatoonToCompany";
	}

}
