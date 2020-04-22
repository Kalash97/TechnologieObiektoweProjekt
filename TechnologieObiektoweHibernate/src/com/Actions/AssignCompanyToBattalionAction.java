package com.Actions;

import com.Entities.Battalion;
import com.Entities.Company;
import com.Exceptions.OperationCancelException;
import com.Repos.BattalionRepo;
import com.Repos.CompanyRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCompanyToBattalionAction implements Action{

	private View view;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;
	
	@Override
	public void launch() {
		Battalion b = getValidBattalion();
		Company c = getValidCompany();
		
		assignBattalionToCompany(b, c);
		
		battalionRepo.updateBattalion(b);
		companyRepo.updateCompany(c);
	}
	
	public void assignBattalionToCompany(Battalion b, Company c) {
		c.setBattalion(b);
	}
	
	private Battalion getValidBattalion() {
		String line;
		Battalion b;
		do {
			do {
				System.out.println("Podaj id batalionu do przypisania kompanii.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = battalionRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
	}
	
	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				System.out.println("Podaj id kompanii do przypisania batalionu.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = companyRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling AssignCompanyToBattalion");
		}
	}
	
	@Override
	public String getName() {
		return "AssignCompanyToBattalion";
	}

}
