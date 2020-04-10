package com.Actions;

import java.util.Scanner;

import com.Repos.SoldierRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSoldierAction implements Action{

	private SoldierRepo repo;
	
	@Override
	public void launch() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Podaj id ¿o³nierza do usuniêcia.(s³owo <<cancel>> zawraca)");
		
		String line = scanner.nextLine();
		if(line.compareTo("cancel")==0) {
			scanner.close();
			System.exit(1);
		}
		
		scanner.close();
		long id = Long.parseLong(line);
		repo.deleteSoldier(id);
	}

	@Override
	public String getName() {
		return "DeleteSoldierAction";
	}

}
