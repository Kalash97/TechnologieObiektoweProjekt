package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

public class TeamRepo extends EntityRepo<Team> {
	
	public TeamRepo(HibernatePersistanceManager persistence) {
		super(persistence);
	}

	public Team findById(long id) {
		return (Team) persistence.findById(id, Team.class);
	}
		
	public List<Team> findTeamsWithoutSoldiers(){
		String query = "SELECT T FROM Team T WHERE size(T.soldiers)=0";
		return findTeamsByQuery(query);
	}
	
	public List<Team> findTeamsWithoutPlatoon(){
		String query = "SELECT T FROM Team T WHERE T.platoon.id=null";
		return findTeamsByQuery(query);
	}
	
	public List<Team> findTeamsWithoutCommander(){
		String query = "SELECT T FROM Team T WHERE T.commander.id=null";
		return findTeamsByQuery(query);
	}

	private List<Team> findTeamsByQuery(String query) {
		List<Persistable> results = persistence.findByQuery(query, Team.class);
		List<Team> teams = ParseUtil.parseTeamList(results);
		return teams;
	}
	
	public List<Team> findTeamsOfSoldier(Soldier soldier){
		String query = "SELECT T FROM Team T, Soldier S WHERE S.id = " + soldier.getId() + " AND S MEMBER OF T.soldiers";
		return findTeamsByQuery(query);
	}
	
	public List<Team> findTeamsOfCommander(Soldier soldier){
		String query = "SELECT T FROM Team T, Soldier S WHERE T.commander.id= " + soldier.getId() + " AND S.id = " + soldier.getId();
		return findTeamsByQuery(query);
	}
	
	public List<Team> findAllTeams() {
		String query = "SELECT T FROM Team T";
		return findTeamsByQuery(query);
	}
}
