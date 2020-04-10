package com.Actions;

import java.util.Scanner;

import com.Repos.PlatoonRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePlatoonAction implements Action{

	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Podaj id plutonu do usuniêcia.(s³owo <<cancel>> zawraca)");
		String line = scanner.nextLine();		
		if (line.compareTo("cancel") == 0) {
			scanner.close();
			System.exit(1);
		}
		scanner.close();
		long id = Long.parseLong(line);
		repo.deletePlatoon(id);
	}

	@Override
	public String getName() {
		return "DeletePlatoonAction";
	}

}
