package com.Actions;

import java.util.Scanner;

import com.Repos.BattalionRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteBattalionAction implements Action{
	
	private BattalionRepo repo;
	
	@Override
	public void launch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Podaj id batalionu do usuniêcia.(s³owo <<cancel>> zawraca)");
		String line = scanner.nextLine();
		if (line.compareTo("cancel") == 0) {
			scanner.close();
			System.exit(1);
		}
		scanner.close();
		long id = Long.parseLong(line);
		repo.deleteBattalion(id);
	}

	@Override
	public String getName() {
		return "DeleteBattalionAction";
	}

}
