package com.Actions;

import java.util.Scanner;

import com.Repos.WeaponRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteWeaponAction implements Action {

	private WeaponRepo repo;

	@Override
	public void launch() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Podaj id broni do usuniêcia.(s³owo <<cancel>> zawraca)");
		String line = scanner.nextLine();
		if (line.compareTo("cancel") == 0) {
			scanner.close();
			System.exit(1);
		}

		scanner.close();
		long id = Long.parseLong(line);
		repo.deleteWeapon(id);
	}

	@Override
	public String getName() {
		return "DeleteWeapon";
	}

}
