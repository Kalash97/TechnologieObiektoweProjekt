package com.controller.actions.BattalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteBattalionAction implements Action {

	private View view;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {
		Battalion b;

		b = getValidBattalion();
		removeCommanderFromBattalion(b);
		removeCompaniesFromBattalion(b);

		battalionRepo.deleteBattalion(b);
	}

	private void removeCommanderFromBattalion(Battalion b) {
		if(b.getCommander()!=null) {
			b.setCommander(null);
			battalionRepo.updateBattalion(b);
		}
	}
	
	private void removeCompaniesFromBattalion(Battalion b) {
		Company c;
		if(b.getCompanies().size()>0) {
			for(int i=0; i<b.getCompanies().size();i++) {
				c=b.getCompanies().get(i);
				c.setBattalion(null);
				companyRepo.updateCompany(c);
			}
			battalionRepo.updateBattalion(b);
		}
	}
	
	private Battalion getValidBattalion() {
		Battalion b;
		String line;
		do {
			do {
				view.print("Podaj id batalionu do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = battalionRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling deleteBattalion");
		}
	}
	
	@Override
	public String getName() {
		return "DeleteBattalion";
	}

}
