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
import com.controller.actions.battalionActions.CreateBattalionAction;
import com.controller.actions.battalionActions.DeleteBattalionAction;
import com.controller.actions.battalionActions.FindAllBattalionsAction;
import com.controller.actions.battalionActions.FindBattalionByCommanderAction;
import com.controller.actions.battalionActions.FindBattalionByIdAction;
import com.controller.actions.battalionActions.FindBattalionsWithoutCommanderAction;
import com.controller.actions.battalionActions.FindBattalionsWithoutCompaniesAction;
import com.controller.actions.battalionActions.UpdateBattalionAction;
import com.controller.actions.companyActions.CreateCompanyAction;
import com.controller.actions.companyActions.DeleteCompanyAction;
import com.controller.actions.companyActions.FindAllCompaniesAction;
import com.controller.actions.companyActions.FindCompaniesWithoutBattalionAction;
import com.controller.actions.companyActions.FindCompaniesWithoutCommanderAction;
import com.controller.actions.companyActions.FindCompaniesWithoutPlatoonsAction;
import com.controller.actions.companyActions.FindCompanyByCommanderAction;
import com.controller.actions.companyActions.FindCompanyByIdAction;
import com.controller.actions.companyActions.RemoveCommanderFromCompanyAction;
import com.controller.actions.companyActions.UpdateCompanyAction;
import com.controller.actions.platoonActions.CreatePlatoonAction;
import com.controller.actions.platoonActions.DeletePlatoonAction;
import com.controller.actions.platoonActions.FindAllPlatoonsAction;
import com.controller.actions.platoonActions.FindPlatoonByCommanderAction;
import com.controller.actions.platoonActions.FindPlatoonByIdAction;
import com.controller.actions.platoonActions.FindPlatoonsWithoutCommanderAction;
import com.controller.actions.platoonActions.FindPlatoonsWithoutCompanyAction;
import com.controller.actions.platoonActions.FindPlatoonsWithoutTeamsAction;
import com.controller.actions.platoonActions.RemoveCommanderFromPlatoonAction;
import com.controller.actions.platoonActions.UpdatePlatoonAction;
import com.controller.actions.soldierActions.CreateSoldierAction;
import com.controller.actions.soldierActions.DeleteSoldierAction;
import com.controller.actions.soldierActions.FindAllSoldiersAction;
import com.controller.actions.soldierActions.FindSoldierByIdAction;
import com.controller.actions.soldierActions.FindSoldiersWithoutTeamAction;
import com.controller.actions.soldierActions.FindSoldiersWithoutWeaponAction;
import com.controller.actions.soldierActions.UpdateSoldierAction;
import com.controller.actions.teamActions.CreateTeamAction;
import com.controller.actions.teamActions.DeleteTeamAction;
import com.controller.actions.teamActions.FindAllTeamsAction;
import com.controller.actions.teamActions.FindTeamByCommanderAction;
import com.controller.actions.teamActions.FindTeamByIdAction;
import com.controller.actions.teamActions.FindTeamsWithoutCommanderAction;
import com.controller.actions.teamActions.FindTeamsWithoutPlatoonAction;
import com.controller.actions.teamActions.FindTeamsWithoutSoldiersAction;
import com.controller.actions.teamActions.RemoveCommanderFromTeamAction;
import com.controller.actions.teamActions.UpdateTeamAction;
import com.controller.actions.weaponActions.CreateWeaponAction;
import com.controller.actions.weaponActions.DeleteWeaponAction;
import com.controller.actions.weaponActions.FindAllWeaponsAction;
import com.controller.actions.weaponActions.FindWeaponByIdAction;
import com.controller.actions.weaponActions.FindWeaponsWithoutSoldierAction;
import com.controller.actions.weaponActions.UpdateWeaponAction;
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
		actionsList.add(new FindAllWeaponsAction(weaponRepo, consoleView));

		actionsList.add(new CreateSoldierAction(consoleView, soldierRepo));
		actionsList.add(new DeleteSoldierAction(consoleView, soldierRepo, weaponRepo, teamRepo, platoonRepo, companyRepo, battalionRepo));
		actionsList.add(new FindSoldierByIdAction(consoleView, soldierRepo));
		actionsList.add(new UpdateSoldierAction(consoleView, soldierRepo));
		actionsList.add(new FindAllSoldiersAction(soldierRepo, consoleView));

		actionsList.add(new CreateTeamAction(consoleView, teamRepo));
		actionsList.add(new DeleteTeamAction(consoleView, teamRepo, soldierRepo));
		actionsList.add(new FindTeamByIdAction(consoleView, teamRepo));
		actionsList.add(new UpdateTeamAction(consoleView, teamRepo));
		actionsList.add(new RemoveCommanderFromTeamAction(consoleView, teamRepo));
		actionsList.add(new FindTeamByCommanderAction(consoleView, soldierRepo, teamRepo));
		actionsList.add(new FindAllTeamsAction(teamRepo, consoleView));

		actionsList.add(new CreatePlatoonAction(consoleView, platoonRepo));
		actionsList.add(new DeletePlatoonAction(consoleView, platoonRepo, teamRepo));
		actionsList.add(new FindPlatoonByIdAction(consoleView, platoonRepo));
		actionsList.add(new UpdatePlatoonAction(consoleView, platoonRepo));
		actionsList.add(new RemoveCommanderFromPlatoonAction(consoleView, platoonRepo));
		actionsList.add(new FindPlatoonByCommanderAction(consoleView, soldierRepo, platoonRepo));
		actionsList.add(new FindAllPlatoonsAction(platoonRepo, consoleView));

		actionsList.add(new CreateCompanyAction(consoleView, companyRepo));
		actionsList.add(new DeleteCompanyAction(consoleView, companyRepo, platoonRepo));
		actionsList.add(new FindCompanyByIdAction(consoleView, companyRepo));
		actionsList.add(new UpdateCompanyAction(consoleView, companyRepo));
		actionsList.add(new RemoveCommanderFromCompanyAction(consoleView, companyRepo));
		actionsList.add(new FindCompanyByCommanderAction(consoleView, soldierRepo, companyRepo));
		actionsList.add(new FindAllCompaniesAction(companyRepo, consoleView));

		actionsList.add(new CreateBattalionAction(consoleView, battalionRepo));
		actionsList.add(new DeleteBattalionAction(consoleView, battalionRepo, companyRepo));
		actionsList.add(new FindBattalionByIdAction(consoleView, battalionRepo));
		actionsList.add(new UpdateBattalionAction(consoleView, battalionRepo));
		actionsList.add(new FindBattalionByCommanderAction(consoleView, soldierRepo, battalionRepo));
		actionsList.add(new FindAllBattalionsAction(battalionRepo, consoleView));

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
