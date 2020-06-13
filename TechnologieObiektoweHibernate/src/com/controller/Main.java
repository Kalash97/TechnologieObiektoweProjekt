package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.controller.actions.Action;
import com.controller.actions.ActionsGroup;
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
	private static List<ActionsGroup> actionsList;

	public static void main(String[] args) {

		init();

		while (true) {
			consoleView.print("Lista dostêpnych akcji:");
			showActions();
			consoleView.printDelimeter();
			consoleView.print("Podaj akcjê");
			runAction(consoleView.read());
		}
	}

	private static void showActions() {
		actionsList.stream().forEach(group -> {
			consoleView.print(group.getGroupName());

			group.getActions().stream().forEach(action -> {
				consoleView.printSubMessage("[" + action.getId() + "] " + action.getName());
			});

			consoleView.printDelimeter();
		});
	}

	private static void runAction(String name) {
		for (ActionsGroup ag : actionsList) {
			for (Action a : ag.getActions()) {
				if (isStringMatchingAction(name, a)) {
					launchActionOrShowError(a);
					return;
				}
			}
		}
		consoleView.print("Nie ma takiej akcji: " + name);
	}

	private static boolean isStringMatchingAction(String name, Action a) {
		try {
			Integer id = Integer.valueOf(name);
			return name.equalsIgnoreCase(a.getName()) || id.equals(a.getId());
		} catch (NumberFormatException e) {
			return name.equalsIgnoreCase(a.getName());
		}
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
		actionsList = new ArrayList<ActionsGroup>();

		actionsList.add(ActionsGroup.createGroup("Weapon actions:", 
				new CreateWeaponAction(consoleView, weaponRepo),
				new DeleteWeaponAction(consoleView, weaponRepo),
				new FindWeaponByIdAction(consoleView, weaponRepo),
				new UpdateWeaponAction(consoleView, weaponRepo),
				new FindAllWeaponsAction(weaponRepo, consoleView)));

		actionsList.add(ActionsGroup.createGroup("Soldiers actions:",
				new CreateSoldierAction(consoleView, soldierRepo),
				new DeleteSoldierAction(consoleView, soldierRepo,weaponRepo, teamRepo, platoonRepo, companyRepo, battalionRepo),
				new FindSoldierByIdAction(consoleView, soldierRepo),
				new UpdateSoldierAction(consoleView, soldierRepo),
				new FindAllSoldiersAction(soldierRepo, consoleView)));

		actionsList.add(ActionsGroup.createGroup("Team actions:",
				new CreateTeamAction(consoleView, teamRepo),
				new DeleteTeamAction(consoleView, teamRepo, soldierRepo),
				new FindTeamByIdAction(consoleView, teamRepo),
				new UpdateTeamAction(consoleView, teamRepo),
				new RemoveCommanderFromTeamAction(consoleView, teamRepo),
				new FindTeamByCommanderAction(consoleView, soldierRepo,teamRepo),
				new FindAllTeamsAction(teamRepo, consoleView)));

		actionsList.add(ActionsGroup.createGroup("Platoon actions:",
				new CreatePlatoonAction(consoleView, platoonRepo),
				new DeletePlatoonAction(consoleView, platoonRepo,teamRepo),
				new FindPlatoonByIdAction(consoleView, platoonRepo),
				new UpdatePlatoonAction(consoleView, platoonRepo),
				new RemoveCommanderFromPlatoonAction(consoleView,platoonRepo),
				new FindPlatoonByCommanderAction(consoleView,soldierRepo, platoonRepo),
				new FindAllPlatoonsAction(platoonRepo, consoleView)));

		actionsList.add(ActionsGroup.createGroup("Company actions:",
				new CreateCompanyAction(consoleView, companyRepo),
				new DeleteCompanyAction(consoleView, companyRepo,platoonRepo),
				new FindCompanyByIdAction(consoleView, companyRepo),
				new UpdateCompanyAction(consoleView, companyRepo),
				new RemoveCommanderFromCompanyAction(consoleView,companyRepo),
				new FindCompanyByCommanderAction(consoleView,soldierRepo, companyRepo),
				new FindAllCompaniesAction(companyRepo, consoleView)));

		actionsList.add(ActionsGroup.createGroup("Battalion actions:",
				new CreateBattalionAction(consoleView, battalionRepo),
				new DeleteBattalionAction(consoleView, battalionRepo,companyRepo),
				new FindBattalionByIdAction(consoleView, battalionRepo),
				new UpdateBattalionAction(consoleView, battalionRepo),
				new FindBattalionByCommanderAction(consoleView,soldierRepo, battalionRepo),
				new FindAllBattalionsAction(battalionRepo, consoleView)));
		
		actionsList.add(ActionsGroup.createGroup("Assigning actions:",
				new AssignWeaponToSoldierAction(consoleView,weaponRepo, soldierRepo),
				new AssignSoldierToTeamAction(consoleView, soldierRepo,teamRepo),
				new AssignCommanderToTeamAction(consoleView, teamRepo,platoonRepo, companyRepo, battalionRepo, soldierRepo),
				new AssignTeamToPlatoonAction(consoleView, platoonRepo,teamRepo),
				new AssignCommanderToPlatoonAction(consoleView,soldierRepo, battalionRepo, companyRepo, platoonRepo,teamRepo),
				new AssignPlatoonToCompanyAction(consoleView,companyRepo, platoonRepo),
				new AssignCommanderToCompanyAction(consoleView,soldierRepo, battalionRepo, companyRepo, platoonRepo,teamRepo),
				new AssignCompanyToBattalionAction(consoleView,battalionRepo, companyRepo),
				new AssignCommanderToBattalionAction(consoleView,soldierRepo, battalionRepo, teamRepo, platoonRepo,companyRepo)));

		actionsList.add(ActionsGroup.createGroup("Advanced finding actions:",
				new FindWeaponsWithoutSoldierAction(consoleView,weaponRepo),
				new FindSoldiersWithoutTeamAction(consoleView,soldierRepo),
				new FindSoldiersWithoutWeaponAction(consoleView,soldierRepo),
				new FindTeamsWithoutSoldiersAction(consoleView,teamRepo),
				new FindTeamsWithoutPlatoonAction(consoleView, teamRepo),
				new FindTeamsWithoutCommanderAction(consoleView,teamRepo),
				new FindPlatoonsWithoutTeamsAction(consoleView,platoonRepo),
				new FindPlatoonsWithoutCompanyAction(consoleView,platoonRepo),
				new FindPlatoonsWithoutCommanderAction(consoleView,platoonRepo),
				new FindCompaniesWithoutPlatoonsAction(consoleView,companyRepo),
				new FindCompaniesWithoutBattalionAction(consoleView,companyRepo),
				new FindCompaniesWithoutCommanderAction(consoleView,companyRepo),
				new FindBattalionsWithoutCompaniesAction(consoleView,battalionRepo),
				new FindBattalionsWithoutCommanderAction(consoleView,battalionRepo)));

		actionsList.add(ActionsGroup.createGroup("System actions:", new ExitAction()));
	}

}
