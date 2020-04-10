package com.Actions;

import java.util.Scanner;

import com.Entities.Platoon;
import com.Repos.PlatoonRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlatoonAction implements Action{

	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = new Platoon();
		Scanner scanner = new Scanner(System.in);
		boolean isDone = false;
		while (isDone == false) {
			System.out.println("Podaj numer");
			String line = scanner.nextLine();
			
			try {
				long number = Long.parseLong(line);
				p.setNumber(number);
				isDone=true;
			} catch (NumberFormatException e) {
				System.out.println("Musisz podaæ liczbê");
			}
		}
		
		scanner.close();
		repo.createPlatoon(p);
	}

	@Override
	public String getName() {
		return "CreatePlatoonAction";
	}

}
