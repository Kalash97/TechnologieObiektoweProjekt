package com.Main;

import java.util.ArrayList;
import java.util.List;

import com.Actions.Action;
import com.Actions.CreateBattalionAction;
import com.Actions.CreateCompanyAction;
import com.Actions.CreatePlatoonAction;
import com.Actions.CreateSoldierAction;
import com.Actions.CreateTeamAction;
import com.Actions.CreateWeaponAction;
import com.Actions.DeleteBattalionAction;
import com.Actions.DeleteCompanyAction;
import com.Actions.DeletePlatoonAction;
import com.Actions.DeleteSoldierAction;
import com.Actions.DeleteTeamAction;
import com.Actions.DeleteWeaponAction;
import com.Actions.ExitAction;
import com.Actions.FindBattalionByIdAction;
import com.Actions.FindCompanyByIdAction;
import com.Actions.FindPlatoonByIdAction;
import com.Actions.FindSoldierByIdAction;
import com.Actions.FindTeamByIdAction;
import com.Actions.FindWeaponByIdAction;
import com.PersistanceManager.HibernatePersistanceManager;
import com.Repos.BattalionRepo;
import com.Repos.CompanyRepo;
import com.Repos.PlatoonRepo;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Repos.WeaponRepo;
import com.View.ConsoleView;

public class Main {

	private static HibernatePersistanceManager hpm;
	private static WeaponRepo weaponRepo;
	private static SoldierRepo soldierRepo;
	private static TeamRepo teamRepo;
	private static PlatoonRepo platoonRepo;
	private static CompanyRepo companyRepo;
	private static BattalionRepo battalionRepo;
	private static ConsoleView consoleView;
	private static List<Action> actionsList;

	public static void main(String[] args) {

		init();
		
		while (true) {
			consoleView.print("Lista dostêpnych akcji:");
			showActions();
			consoleView.print("");
			consoleView.print("Podaj akcjê");
			runAction(consoleView.read());
		}
	}

	private static void showActions() {
		for (Action a : actionsList) {
			consoleView.print(" "+a.getName());
		}
	}

	private static void runAction(String name) {
		for (Action a : actionsList) {
			if (name.equals(a.getName())) {
				a.launch();
				return;
			}
		}
		consoleView.print("Nie ma takiej akcji: " + name);
	}

	public static void init() {
		hpm = new HibernatePersistanceManager();
		weaponRepo = new WeaponRepo(hpm);
		soldierRepo = new SoldierRepo(hpm);
		teamRepo = new TeamRepo(hpm);
		platoonRepo = new PlatoonRepo(hpm);
		companyRepo = new CompanyRepo(hpm);
		battalionRepo = new BattalionRepo(hpm);
		consoleView = new ConsoleView();
		actionsList = new ArrayList<Action>();

		actionsList.add(new CreateWeaponAction(consoleView, weaponRepo));
		actionsList.add(new DeleteWeaponAction(consoleView, weaponRepo));
		actionsList.add(new FindWeaponByIdAction(consoleView, weaponRepo));

		actionsList.add(new CreateSoldierAction(consoleView, soldierRepo));
		actionsList.add(new DeleteSoldierAction(consoleView, soldierRepo));
		actionsList.add(new FindSoldierByIdAction(consoleView, soldierRepo));
		
		actionsList.add(new CreateTeamAction(consoleView, teamRepo));
		actionsList.add(new DeleteTeamAction(consoleView, teamRepo));
		actionsList.add(new FindTeamByIdAction(consoleView, teamRepo));

		actionsList.add(new CreatePlatoonAction(consoleView, platoonRepo));
		actionsList.add(new DeletePlatoonAction(consoleView, platoonRepo));
		actionsList.add(new FindPlatoonByIdAction(consoleView, platoonRepo));

		actionsList.add(new CreateCompanyAction(consoleView, companyRepo));
		actionsList.add(new DeleteCompanyAction(consoleView, companyRepo));
		actionsList.add(new FindCompanyByIdAction(consoleView, companyRepo));

		actionsList.add(new CreateBattalionAction(consoleView, battalionRepo));
		actionsList.add(new DeleteBattalionAction(consoleView, battalionRepo));
		actionsList.add(new FindBattalionByIdAction(consoleView, battalionRepo));

		actionsList.add(new ExitAction());
	}

}
