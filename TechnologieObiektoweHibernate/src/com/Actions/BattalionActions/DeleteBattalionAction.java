package com.Actions.BattalionActions;

import com.Actions.Action;
import com.Entities.Battalion;
import com.Entities.Company;
import com.Exceptions.OperationCancelException;
import com.Repos.BattalionRepo;
import com.Repos.CompanyRepo;
import com.Utils.ValidUtil;
import com.View.View;

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
