package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamRepo {

	HibernatePersistanceManager hpm;
	
	public Team createTeam(Team team) {
		return (Team) hpm.create(team);
	}
	
	public void deleteTeam(Team team) {
		hpm.delete(team);
	}
	
	public Team findById(long id) {
		return (Team) hpm.findById(id, Team.class);
	}
	
	public void updateTeam(Team team) {
		hpm.update(team);
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
		List<Persistable> results = hpm.findByQuery(query, Team.class);
		List<Team> teams = ParseUtil.parseTeamList(results);
		return teams;
	}
	
	public List<Team> findTeamsOfSoldier(Soldier soldier){
		String query = "SELECT T FROM Team T, Soldier S WHERE S.id = " + soldier.getId() + " AND S MEMBER OF T.soldiers";
		return findTeamsByQuery(query);
	}
	
	public List<Team> findTeamsOfCommander(Soldier soldier){
		String query = "SELECT T FROM Team T, Soldier S WHERE T.commander.id= " + soldier.getId();
		return findTeamsByQuery(query);
	}
}
