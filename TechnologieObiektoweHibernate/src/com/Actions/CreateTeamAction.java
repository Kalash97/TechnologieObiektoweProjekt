package com.Actions;

import java.util.Scanner;

import com.Entities.Team;
import com.Repos.TeamRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTeamAction implements Action {

	private TeamRepo repo;

	@Override
	public void launch() {
		Team t = new Team();
		Scanner scanner = new Scanner(System.in);
		boolean isDone = false;
		while (isDone == false) {
			System.out.println("Podaj numer");
			String line = scanner.nextLine();
			
			try {
				long number = Long.parseLong(line);
				t.setNumber(number);
				isDone=true;
			} catch (NumberFormatException e) {
				System.out.println("Musisz podaæ liczbê");
			}
		}

		scanner.close();
		repo.createTeam(t);
	}

	@Override
	public String getName() {
		return "CreateTeamAction";
	}

}
