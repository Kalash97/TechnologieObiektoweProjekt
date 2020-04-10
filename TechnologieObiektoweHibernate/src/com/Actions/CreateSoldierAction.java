package com.Actions;

import java.util.Scanner;

import com.Entities.Soldier;
import com.Enums.BloodType;
import com.Enums.Rank;
import com.Repos.SoldierRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSoldierAction implements Action{

	private SoldierRepo repo;
	
	@Override
	public void launch() {
		Soldier s = new Soldier();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Podaj imiê");
		s.setName(scanner.nextLine());
		
		System.out.println("Podaj nazwisko");
		s.setLastName(scanner.nextLine());
		
		boolean isDone = false;
		while(isDone==false) {
		System.out.println("Podaj grupê krwi spoœród dostêpnych");
		for(BloodType b : BloodType.values()) {
			System.out.println(b);
		}
		
		String bloodTypeStr = scanner.nextLine();
		
			try {
				BloodType bloodType = BloodType.valueOf(bloodTypeStr.toUpperCase());
				s.setBloodType(bloodType);
				isDone=true;
			}catch (IllegalArgumentException e) {
				System.out.println("Nie ma takiej grupy krwi");
			}
		}
		
		isDone=false;
		while(isDone==false) {
			System.out.println("Podaj stopieñ spoœród dostêpnych");
			for(Rank r : Rank.values()) {
				System.out.println(r);
			}
			String rankStr = scanner.nextLine();
			
			try {
				Rank rank = Rank.valueOf(rankStr.toUpperCase());
				s.setRank(rank);
				isDone=true;
			}catch(IllegalArgumentException e) {
				System.out.println("Nie ma takiego stopnia");
			}
		}
		scanner.close();
		
		repo.createSoldier(s);
	}

	@Override
	public String getName() {
		return "CreateSoldierAction";
	}

}
