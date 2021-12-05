package com.zefrocan.ipldashboard.controller;

import com.zefrocan.ipldashboard.model.Match;
import com.zefrocan.ipldashboard.model.Team;
import com.zefrocan.ipldashboard.service.IPLDashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class IPLDashboardController {

    private IPLDashboardService iplDashboardService;

    public IPLDashboardController(IPLDashboardService iplDashboardService) {
        this.iplDashboardService = iplDashboardService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teams/{teamName}")
    public Team fetchTeamInformation(@PathVariable(value = "teamName") String teamName) {
        return iplDashboardService.fetchTeamInformation(teamName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teams/{teamName}/matches")
    public List<Match> fetchMatchesForTeam(@PathVariable(value = "teamName") String teamName,
                                           @RequestParam(value = "year") int year) {
        return iplDashboardService.fetchMatchesForTeam(teamName, year);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teams")
    public List<Team> fetchAllTeams() {
        return iplDashboardService.fetchAllTeams();
    }


}
