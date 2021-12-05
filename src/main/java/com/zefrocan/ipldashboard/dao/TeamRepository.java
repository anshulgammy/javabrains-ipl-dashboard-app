package com.zefrocan.ipldashboard.dao;

import com.zefrocan.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    public Team findTeamByName(String teamName);
}
