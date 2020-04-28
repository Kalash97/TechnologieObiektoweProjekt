package com.Main;

import java.util.ArrayList;
import java.util.List;

import com.Actions.Action;
import com.Actions.AssignCommanderToBattalionAction;
import com.Actions.AssignCommanderToCompanyAction;
import com.Actions.AssignCommanderToPlatoonAction;
import com.Actions.AssignCommanderToTeamAction;
import com.Actions.AssignCompanyToBattalionAction;
import com.Actions.AssignPlatoonToCompanyAction;
import com.Actions.AssignSoldierToTeamAction;
import com.Actions.AssignTeamToPlatoonAction;
import com.Actions.AssignWeaponToSoldierAction;
import com.Actions.ExitAction;
import com.Actions.BattalionActions.CreateBattalionAction;
import com.Actions.BattalionActions.DeleteBattalionAction;
import com.Actions.BattalionActions.FindBattalionByIdAction;
import com.Actions.BattalionActions.FindBattalionsWithoutCommanderAction;
import com.Actions.BattalionActions.FindBattalionsWithoutCompaniesAction;
import com.Actions.CompanyActions.CreateCompanyAction;
import com.Actions.CompanyActions.DeleteCompanyAction;
import com.Actions.CompanyActions.FindCompaniesWithoutBattalionAction;
import com.Actions.CompanyActions.FindCompaniesWithoutCommanderAction;
import com.Actions.CompanyActions.FindCompaniesWithoutPlatoonsAction;
import com.Actions.CompanyActions.FindCompanyByIdAction;
import com.Actions.PlatoonActions.CreatePlatoonAction;
import com.Actions.PlatoonActions.DeletePlatoonAction;
import com.Actions.PlatoonActions.FindPlatoonByIdAction;
import com.Actions.PlatoonActions.FindPlatoonsWithoutCommanderAction;
import com.Actions.PlatoonActions.FindPlatoonsWithoutCompanyAction;
import com.Actions.PlatoonActions.FindPlatoonsWithoutTeamsAction;
import com.Actions.SoldierActions.CreateSoldierAction;
import com.Actions.SoldierActions.DeleteSoldierAction;
import com.Actions.SoldierActions.FindSoldierByIdAction;
import com.Actions.SoldierActions.FindSoldiersWithoutTeamAction;
import com.Actions.SoldierActions.FindSoldiersWithoutWeaponAction;
import com.Actions.TeamActions.CreateTeamAction;
import com.Actions.TeamActions.DeleteTeamAction;
import com.Actions.TeamActions.FindTeamByIdAction;
import com.Actions.TeamActions.FindTeamsWithoutCommanderAction;
import com.Actions.TeamActions.FindTeamsWithoutPlatoonAction;
import com.Actions.TeamActions.FindTeamsWithoutSoldiersAction;
import com.Actions.WeaponActions.CreateWeaponAction;
import com.Actions.WeaponActions.DeleteWeaponAction;
import com.Actions.WeaponActions.FindWeaponByIdAction;
import com.Actions.WeaponActions.FindWeaponsWithoutSoldierAction;
import com.Exceptions.OperationCancelException;
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
			consoleView.print("Lista dost�pnych akcji:");
			showActions();
			consoleView.print("");
			consoleView.print("Podaj akcj�");
			runAction(consoleView.read());
		}
	}

	private static void showActions() {
		for (Action a : actionsList) {
			consoleView.print(" " + a.getName());
		}
	}

	private static void runAction(String name) {
		for (Action a : actionsList) {
			if (name.equals(a.getName())) {
				launchActionOrShowError(a);
				return;
			}
		}
		consoleView.print("Nie ma takiej akcji: " + name);
	}

	private static void launchActionOrShowError(Action a) {
		try {
			a.launch();
		} catch (OperationCancelException e) {
			consoleView.print(e.getMessage());
		}
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
		actionsList.add(new DeleteSoldierAction(consoleView, soldierRepo, weaponRepo, teamRepo, platoonRepo, companyRepo, battalionRepo));
		actionsList.add(new FindSoldierByIdAction(consoleView, soldierRepo));

		actionsList.add(new CreateTeamAction(consoleView, teamRepo));
		actionsList.add(new DeleteTeamAction(consoleView, teamRepo, soldierRepo));
		actionsList.add(new FindTeamByIdAction(consoleView, teamRepo));

		actionsList.add(new CreatePlatoonAction(consoleView, platoonRepo));
		actionsList.add(new DeletePlatoonAction(consoleView, platoonRepo, teamRepo));
		actionsList.add(new FindPlatoonByIdAction(consoleView, platoonRepo));

		actionsList.add(new CreateCompanyAction(consoleView, companyRepo));
		actionsList.add(new DeleteCompanyAction(consoleView, companyRepo, platoonRepo));
		actionsList.add(new FindCompanyByIdAction(consoleView, companyRepo));

		actionsList.add(new CreateBattalionAction(consoleView, battalionRepo));
		actionsList.add(new DeleteBattalionAction(consoleView, battalionRepo, companyRepo));
		actionsList.add(new FindBattalionByIdAction(consoleView, battalionRepo));

		actionsList.add(new AssignWeaponToSoldierAction(consoleView, weaponRepo, soldierRepo));

		actionsList.add(new AssignSoldierToTeamAction(consoleView, soldierRepo, teamRepo));
		actionsList.add(new AssignCommanderToTeamAction(consoleView, teamRepo, soldierRepo));
		actionsList.add(new AssignTeamToPlatoonAction(consoleView, platoonRepo, teamRepo));
		actionsList.add(new AssignCommanderToPlatoonAction(consoleView, soldierRepo, platoonRepo));
		actionsList.add(new AssignPlatoonToCompanyAction(consoleView, companyRepo, platoonRepo));
		actionsList.add(new AssignCommanderToCompanyAction(consoleView, soldierRepo, companyRepo));
		actionsList.add(new AssignCompanyToBattalionAction(consoleView, battalionRepo, companyRepo));
		actionsList.add(new AssignCommanderToBattalionAction(consoleView, soldierRepo, battalionRepo));
		
		actionsList.add(new FindWeaponsWithoutSoldierAction(consoleView, weaponRepo));
		
		actionsList.add(new FindSoldiersWithoutTeamAction(consoleView, soldierRepo));
		actionsList.add(new FindSoldiersWithoutWeaponAction(consoleView, soldierRepo));
		
		actionsList.add(new FindTeamsWithoutSoldiersAction(consoleView, teamRepo));
		actionsList.add(new FindTeamsWithoutPlatoonAction(consoleView, teamRepo));
		actionsList.add(new FindTeamsWithoutCommanderAction(consoleView, teamRepo));
		
		actionsList.add(new FindPlatoonsWithoutTeamsAction(consoleView, platoonRepo));
		actionsList.add(new FindPlatoonsWithoutCompanyAction(consoleView, platoonRepo));
		actionsList.add(new FindPlatoonsWithoutCommanderAction(consoleView, platoonRepo));
		
		actionsList.add(new FindCompaniesWithoutPlatoonsAction(consoleView, companyRepo));
		actionsList.add(new FindCompaniesWithoutBattalionAction(consoleView, companyRepo));
		actionsList.add(new FindCompaniesWithoutCommanderAction(consoleView, companyRepo));
		
		actionsList.add(new FindBattalionsWithoutCompaniesAction(consoleView, battalionRepo));
		actionsList.add(new FindBattalionsWithoutCommanderAction(consoleView, battalionRepo));
		
		actionsList.add(new ExitAction());
	}

}
