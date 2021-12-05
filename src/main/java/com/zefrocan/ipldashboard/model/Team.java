package com.zefrocan.ipldashboard.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_MD_TEAMS")
@SequenceGenerator(name="teams_id_seq", initialValue=1000)
public class Team {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="teams_id_seq")
    private Long id;
    private String name;
    private Long totalMatches;
    private Long totalWins;
    @Transient
    private List<Match> totalMatchesList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(Long totalMatches) {
        this.totalMatches = totalMatches;
    }

    public Long getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(Long totalWins) {
        this.totalWins = totalWins;
    }

    public List<Match> getTotalMatchesList() {
        return totalMatchesList;
    }

    public void setTotalMatchesList(List<Match> totalMatchesList) {
        this.totalMatchesList = totalMatchesList;
    }
}
