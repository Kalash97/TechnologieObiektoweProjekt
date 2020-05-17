package com.controller.actions.CompanyActions;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.entities.Platoon;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCompanyAction implements Action {

	private View view;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;

	@Override
	public void launch() {
		Company c;

		c = getValidCompany();
		
		removeComanderFromCompany(c);
		removePlatoonsFromCompany(c);

		companyRepo.deleteCompany(c);
	}

	private void removeComanderFromCompany(Company c) {
		if(c.getCommander()!=null) {
			c.setCommander(null);
			companyRepo.updateCompany(c);
		}
	}
	
	private void removePlatoonsFromCompany(Company c) {
		Platoon p;
		if(c.getPlattons().size()>0) {
			for(int i=0; i<c.getPlattons().size();i++) {
				p=c.getPlattons().get(i);
				p.setCompany(null);
				platoonRepo.updatePlatoon(p);
			}
			companyRepo.updateCompany(c);
		}
	}
	
	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				view.print("Podaj id kompanii do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = companyRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling deleteCompany");
		}
	}
	
	@Override
	public String getName() {
		return "DeleteCompany";
	}

}
