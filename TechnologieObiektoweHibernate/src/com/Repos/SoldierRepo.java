package com.Repos;

import java.util.List;

import com.Entities.Battalion;
import com.Entities.Company;
import com.Entities.Persistable;
import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Entities.Team;
import com.PersistanceManager.HibernatePersistanceManager;
import com.Utils.ParseUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SoldierRepo {

	private HibernatePersistanceManager hpm;

	public Soldier createSoldier(Soldier soldier) {
		return (Soldier) hpm.create(soldier);
	}

	public void deleteSoldier(Soldier soldier) {
		hpm.delete(soldier);
	}

	public Soldier findById(long id) {
		return (Soldier) hpm.findById(id, Soldier.class);
	}

	public void updateSoldier(Soldier soldier) {
		hpm.update(soldier);
	}

	public List<Team> findTeamsOfSoldier(Soldier soldier){
		String query = "SELECT T FROM Team T, Soldier S WHERE S.id = " + soldier.getId() + " AND S MEMBER OF T.soldiers";
		return findTeamsByQuery(query);
	}
	
	public List<Team> findTeamsOfCommander(Soldier soldier){
		String query = "SELECT T FROM Team T, Soldier S WHERE T.commander.id= " + soldier.getId();
		return findTeamsByQuery(query);
	}
	
	public List<Platoon> findPlatoonOfCommander(Soldier soldier) {
		String query = "SELECT P FROM Platoon P, Soldier S WHERE P.commander.id = " + soldier.getId();
		return findPlatoonsByQuery(query);
	}
	
	public List<Company> findCompanyOfCommander(Soldier soldier){
		String query = "SELECT C FROM Company C, Soldier S WHERE C.commander.id = " + soldier.getId();
		return findCompaniesByQuery(query);
	}
	
	public List<Battalion> findBattalionOfCommander(Soldier soldier){
		String query = "SELECT B FROM Battalion B, Soldier S WHERE B.commander.id = " + soldier.getId();
		return findBattalionsByQuery(query);

	}

	public List<Soldier> findSoldiersWithoutTeam(){
		String query = "SELECT S FROM Soldier S WHERE S.team.id=null";
		return findSoldiersByQuery(query);
	}
	
	public List<Soldier> findSoldiersWithoutWeapon(){
		String query = "SELECT S FROM Soldier S WHERE size(S.weapons)=0";
		return findSoldiersByQuery(query);
	}

	private List<Soldier> findSoldiersByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Soldier.class);
		List<Soldier> soldiers = ParseUtil.parseSoldierList(results);
		return soldiers;
	}

	private List<Team> findTeamsByQuery(String query){
		List<Persistable> results = hpm.findByQuery(query, Team.class);
		List<Team> teams = ParseUtil.parseTeamList(results);
		return teams;
	}
	
	private List<Platoon> findPlatoonsByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Platoon.class);
		List<Platoon> platoons = ParseUtil.parsePlatoonList(results);
		return platoons;
	}

	private List<Company> findCompaniesByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Company.class);
		List<Company> companies = ParseUtil.parseCompanyList(results);
		return companies;
	}
	
	private List<Battalion> findBattalionsByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Battalion.class);
		List<Battalion> battalions = ParseUtil.parseBattalionList(results);
		return battalions;
	}
}
