package com.Main;

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
import com.PersistanceManager.HibernatePersistanceManager;
import com.Repos.BattalionRepo;
import com.Repos.CompanyRepo;
import com.Repos.PlatoonRepo;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Repos.WeaponRepo;

public class Main {

	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("techobdb");
//		EntityManager em = emf.createEntityManager();
//		
//		em.close();
//		emf.close();
		
		HibernatePersistanceManager hpm = new HibernatePersistanceManager();
		WeaponRepo weaponRepo = new WeaponRepo(hpm);
		SoldierRepo soldierRepo = new SoldierRepo(hpm);
		TeamRepo teamRepo = new TeamRepo(hpm);
		PlatoonRepo platoonRepo = new PlatoonRepo(hpm);
		CompanyRepo companyRepo = new CompanyRepo(hpm);
		BattalionRepo battalionRepo = new BattalionRepo(hpm);
		
		CreateWeaponAction cwa = new CreateWeaponAction(weaponRepo);
		DeleteWeaponAction dwa = new DeleteWeaponAction(weaponRepo);
		
		CreateSoldierAction csa = new CreateSoldierAction(soldierRepo);
		DeleteSoldierAction dsa = new DeleteSoldierAction(soldierRepo);
		
		CreateTeamAction cta = new CreateTeamAction(teamRepo);
		DeleteTeamAction dta = new DeleteTeamAction(teamRepo);
		
		CreatePlatoonAction cpa = new CreatePlatoonAction(platoonRepo);
		DeletePlatoonAction dpa = new DeletePlatoonAction(platoonRepo);
		
		CreateCompanyAction cca = new CreateCompanyAction(companyRepo);
		DeleteCompanyAction dca = new DeleteCompanyAction(companyRepo);
		
		CreateBattalionAction cba = new CreateBattalionAction(battalionRepo);
		DeleteBattalionAction dba = new DeleteBattalionAction(battalionRepo);
		
		////Weapon
		//cwa.launch();
		//dwa.launch();
		
		////Soldier
		//csa.launch();
		//dsa.launch();
		
		////Team
		//cta.launch();
		//dta.launch();
		
		////Platoon
		//cpa.launch();
		//dpa.launch();
		
		////Company
		//cca.launch();
		//dca.launch();
		
		////Battalion
		//cba.launch();
		//dba.launch();
	}

}
