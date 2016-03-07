package com.ga.hive.persistence.entity;

public class Counter {

    public int templatesCount;
    public int categoriesCount;
    public int principlesCount;
    public int questionnaireCount;
    public int teamsCount;
    public int usersCount;
    public int tasksCount;

    public int getTemplatesCount() {
        return templatesCount;
    }

    public void setTemplatesCount(int templatesCount) {
        this.templatesCount = templatesCount;
    }

    public int getCategoriesCount() {
        return categoriesCount;
    }

    public void setCategoriesCount(int categoriesCount) {
        this.categoriesCount = categoriesCount;
    }

    public int getPrinciplesCount() {
        return principlesCount;
    }

    public void setPrinciplesCount(int principlesCount) {
        this.principlesCount = principlesCount;
    }

    public int getQuestionnaireCount() {
        return questionnaireCount;
    }

    public void setQuestionnaireCount(int questionnaireCount) {
        this.questionnaireCount = questionnaireCount;
    }

    public int getTeamsCount() {
        return teamsCount;
    }

    public void setTeamsCount(int teamsCount) {
        this.teamsCount = teamsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(int tasksCount) {
        this.tasksCount = tasksCount;
    }

    @Override
    public String toString() {
        return "Counter [templatesCount=" + templatesCount + ", categoriesCount=" + categoriesCount
                + ", principlesCount=" + principlesCount + ", questionnaireCount=" + questionnaireCount
                + ", teamsCount=" + teamsCount + ", usersCount=" + usersCount + ", tasksCount=" + tasksCount + "]";
    }

}
