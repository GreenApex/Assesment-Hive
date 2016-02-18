package com.ga.hive.persistence.entity;

import java.util.List;

public class TeamDTO {
    private String teamID;
    private String teamName;
    private String teamOwnerID;
    private List<Members> members;

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamOwnerID() {
        return teamOwnerID;
    }

    public void setTeamOwnerID(String teamOwnerID) {
        this.teamOwnerID = teamOwnerID;
    }

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "TeamDTO [teamID=" + teamID + ", teamName=" + teamName + ", teamOwnerID=" + teamOwnerID + ", members="
                + members + "]";
    }

}
