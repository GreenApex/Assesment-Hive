package com.ga.hive.persistence.entity;

public class Team {

    private String teamID;
    private String teamName;
    private String teamOwnerID;
    private String membersID;
    private boolean isdelete;

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

    public String getMembersID() {
        return membersID;
    }

    public void setMembersID(String membersID) {
        this.membersID = membersID;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public String toString() {
        return "Team [teamID=" + teamID + ", teamName=" + teamName + ", teamOwnerID=" + teamOwnerID + ", membersID="
                + membersID + ", isdelete=" + isdelete + "]";
    }

}
