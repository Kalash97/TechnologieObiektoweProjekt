package com.Actions;

import java.util.Scanner;

import com.Entities.Weapon;
import com.Enums.WeaponType;
import com.Repos.WeaponRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateWeaponAction implements Action {

	private WeaponRepo repo;

	@Override
	public void launch() {
		Weapon w = new Weapon();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Podaj nazwê");
		w.setName(scanner.nextLine());

		System.out.println("Podaj nr. seryjny");
		w.setSerialNumber(scanner.nextLine());

		boolean isDone = false;
		while (isDone == false) {
			System.out.println("Podaj typ spoœród dostêpnych");
			for(WeaponType wpn : WeaponType.values()) {
				System.out.println(wpn);
			}
			String weaponStr = scanner.nextLine();
			
			try {
				WeaponType type = WeaponType.valueOf(weaponStr.toUpperCase());
				w.setWeaponType(type);
				isDone=true;
			}catch (IllegalArgumentException e) {
				System.out.println("Nie ma takiego typu");			
			}
		}
		
		scanner.close();
		
		repo.createWeapon(w);
	}

	@Override
	public String getName() {
		return "CreateWeapon";
	}

}
