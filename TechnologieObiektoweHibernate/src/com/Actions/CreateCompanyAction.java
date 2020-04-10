package com.Actions;

import java.util.Scanner;

import com.Entities.Company;
import com.Repos.CompanyRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCompanyAction implements Action{

	private CompanyRepo repo;
	
	@Override
	public void launch() {
		Company c = new Company();
		Scanner scanner = new Scanner(System.in);
		boolean isDone = false;
		while (isDone == false) {
			System.out.println("Podaj numer");
			String line = scanner.nextLine();
			
			try {
				long number = Long.parseLong(line);
				c.setNumber(number);
				isDone=true;
			} catch (NumberFormatException e) {
				System.out.println("Musisz podaæ liczbê");
			}
		}

		scanner.close();
		repo.createCompany(c);
	}

	@Override
	public String getName() {
		return "CreateCompanyAction";
	}

}
