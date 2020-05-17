package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.controller.actions.Action;
import com.controller.actions.AssignCommanderToBattalionAction;
import com.controller.actions.AssignCommanderToCompanyAction;
import com.controller.actions.AssignCommanderToPlatoonAction;
import com.controller.actions.AssignCommanderToTeamAction;
import com.controller.actions.AssignCompanyToBattalionAction;
import com.controller.actions.AssignPlatoonToCompanyAction;
import com.controller.actions.AssignSoldierToTeamAction;
import com.controller.actions.AssignTeamToPlatoonAction;
import com.controller.actions.AssignWeaponToSoldierAction;
import com.controller.actions.ExitAction;
import com.controller.actions.BattalionActions.CreateBattalionAction;
import com.controller.actions.BattalionActions.DeleteBattalionAction;
import com.controller.actions.BattalionActions.FindBattalionByCommanderAction;
import com.controller.actions.BattalionActions.FindBattalionByIdAction;
import com.controller.actions.BattalionActions.FindBattalionsWithoutCommanderAction;
import com.controller.actions.BattalionActions.FindBattalionsWithoutCompaniesAction;
import com.controller.actions.BattalionActions.UpdateBattalionAction;
import com.controller.actions.CompanyActions.CreateCompanyAction;
import com.controller.actions.CompanyActions.DeleteCompanyAction;
import com.controller.actions.CompanyActions.FindCompaniesWithoutBattalionAction;
import com.controller.actions.CompanyActions.FindCompaniesWithoutCommanderAction;
import com.controller.actions.CompanyActions.FindCompaniesWithoutPlatoonsAction;
import com.controller.actions.CompanyActions.FindCompanyByCommanderAction;
import com.controller.actions.CompanyActions.FindCompanyByIdAction;
import com.controller.actions.CompanyActions.RemoveCommanderFromCompanyAction;
import com.controller.actions.CompanyActions.UpdateCompanyAction;
import com.controller.actions.PlatoonActions.CreatePlatoonAction;
import com.controller.actions.PlatoonActions.DeletePlatoonAction;
import com.controller.actions.PlatoonActions.FindPlatoonByCommanderAction;
import com.controller.actions.PlatoonActions.FindPlatoonByIdAction;
import com.controller.actions.PlatoonActions.FindPlatoonsWithoutCommanderAction;
import com.controller.actions.PlatoonActions.FindPlatoonsWithoutCompanyAction;
import com.controller.actions.PlatoonActions.FindPlatoonsWithoutTeamsAction;
import com.controller.actions.PlatoonActions.RemoveCommanderFromPlatoonAction;
import com.controller.actions.PlatoonActions.UpdatePlatoonAction;
import com.controller.actions.SoldierActions.CreateSoldierAction;
import com.controller.actions.SoldierActions.DeleteSoldierAction;
import com.controller.actions.SoldierActions.FindSoldierByIdAction;
import com.controller.actions.SoldierActions.FindSoldiersWithoutTeamAction;
import com.controller.actions.SoldierActions.FindSoldiersWithoutWeaponAction;
import com.controller.actions.SoldierActions.UpdateSoldierAction;
import com.controller.actions.TeamActions.CreateTeamAction;
import com.controller.actions.TeamActions.DeleteTeamAction;
import com.controller.actions.TeamActions.FindTeamByCommanderAction;
import com.controller.actions.TeamActions.FindTeamByIdAction;
import com.controller.actions.TeamActions.FindTeamsWithoutCommanderAction;
import com.controller.actions.TeamActions.FindTeamsWithoutPlatoonAction;
import com.controller.actions.TeamActions.FindTeamsWithoutSoldiersAction;
import com.controller.actions.TeamActions.RemoveCommanderFromTeamAction;
import com.controller.actions.TeamActions.UpdateTeamAction;
import com.controller.actions.WeaponActions.CreateWeaponAction;
import com.controller.actions.WeaponActions.DeleteWeaponAction;
import com.controller.actions.WeaponActions.FindWeaponByIdAction;
import com.controller.actions.WeaponActions.FindWeaponsWithoutSoldierAction;
import com.controller.actions.WeaponActions.UpdateWeaponAction;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.model.repos.WeaponRepo;
import com.utils.exceptions.OperationCancelException;
import com.view.ConsoleView;

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
		actionsList.add(new UpdateWeaponAction(consoleView, weaponRepo));

		actionsList.add(new CreateSoldierAction(consoleView, soldierRepo));
		actionsList.add(new DeleteSoldierAction(consoleView, soldierRepo, weaponRepo, teamRepo, platoonRepo, companyRepo, battalionRepo));
		actionsList.add(new FindSoldierByIdAction(consoleView, soldierRepo));
		actionsList.add(new UpdateSoldierAction(consoleView, soldierRepo));

		actionsList.add(new CreateTeamAction(consoleView, teamRepo));
		actionsList.add(new DeleteTeamAction(consoleView, teamRepo, soldierRepo));
		actionsList.add(new FindTeamByIdAction(consoleView, teamRepo));
		actionsList.add(new UpdateTeamAction(consoleView, teamRepo));
		actionsList.add(new RemoveCommanderFromTeamAction(consoleView, teamRepo));
		actionsList.add(new FindTeamByCommanderAction(consoleView, soldierRepo, teamRepo));

		actionsList.add(new CreatePlatoonAction(consoleView, platoonRepo));
		actionsList.add(new DeletePlatoonAction(consoleView, platoonRepo, teamRepo));
		actionsList.add(new FindPlatoonByIdAction(consoleView, platoonRepo));
		actionsList.add(new UpdatePlatoonAction(consoleView, platoonRepo));
		actionsList.add(new RemoveCommanderFromPlatoonAction(consoleView, platoonRepo));
		actionsList.add(new FindPlatoonByCommanderAction(consoleView, soldierRepo, platoonRepo));

		actionsList.add(new CreateCompanyAction(consoleView, companyRepo));
		actionsList.add(new DeleteCompanyAction(consoleView, companyRepo, platoonRepo));
		actionsList.add(new FindCompanyByIdAction(consoleView, companyRepo));
		actionsList.add(new UpdateCompanyAction(consoleView, companyRepo));
		actionsList.add(new RemoveCommanderFromCompanyAction(consoleView, companyRepo));
		actionsList.add(new FindCompanyByCommanderAction(consoleView, soldierRepo, companyRepo));

		actionsList.add(new CreateBattalionAction(consoleView, battalionRepo));
		actionsList.add(new DeleteBattalionAction(consoleView, battalionRepo, companyRepo));
		actionsList.add(new FindBattalionByIdAction(consoleView, battalionRepo));
		actionsList.add(new UpdateBattalionAction(consoleView, battalionRepo));
		actionsList.add(new FindBattalionByCommanderAction(consoleView, soldierRepo, battalionRepo));

		actionsList.add(new AssignWeaponToSoldierAction(consoleView, weaponRepo, soldierRepo));
		actionsList.add(new AssignSoldierToTeamAction(consoleView, soldierRepo, teamRepo));
		actionsList.add(new AssignCommanderToTeamAction(consoleView, teamRepo, platoonRepo, companyRepo, battalionRepo, soldierRepo));
		actionsList.add(new AssignTeamToPlatoonAction(consoleView, platoonRepo, teamRepo));
		actionsList.add(new AssignCommanderToPlatoonAction(consoleView, soldierRepo, battalionRepo, companyRepo, platoonRepo, teamRepo));
		actionsList.add(new AssignPlatoonToCompanyAction(consoleView, companyRepo, platoonRepo));
		actionsList.add(new AssignCommanderToCompanyAction(consoleView, soldierRepo, battalionRepo, companyRepo, platoonRepo, teamRepo));
		actionsList.add(new AssignCompanyToBattalionAction(consoleView, battalionRepo, companyRepo));
		actionsList.add(new AssignCommanderToBattalionAction(consoleView, soldierRepo, battalionRepo, teamRepo, platoonRepo, companyRepo));
		
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
