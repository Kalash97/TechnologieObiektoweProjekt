package com.Actions;

import java.util.Scanner;

import com.Entities.Battalion;
import com.Repos.BattalionRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBattalionAction implements Action{
	
	private BattalionRepo repo;

	@Override
	public void launch() {
		Battalion b = new Battalion();
		Scanner scanner = new Scanner(System.in);
		boolean isDone = false;
		while (isDone == false) {
			System.out.println("Podaj numer");
			String line = scanner.nextLine();
			
			try {
				long number = Long.parseLong(line);
				b.setNumber(number);
				isDone=true;
			} catch (NumberFormatException e) {
				System.out.println("Musisz podaæ liczbê");
			}
		}
		
		scanner.close();
		repo.createBattalion(b);
	}

	@Override
	public String getName() {
		return "CreateBattalionAction";
	}

}
