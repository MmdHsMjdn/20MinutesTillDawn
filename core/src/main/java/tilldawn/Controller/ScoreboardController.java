package tilldawn.Controller;

import tilldawn.Model.User;
import tilldawn.Main;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreboardController {

    private final List<User> allUsers;
    private String currentSortMethod = "By Score";

    public ScoreboardController() {
        this.allUsers = new ArrayList<>(Main.getMain().getUserManager().getUsers());
    }

    public String getCurrentSortMethod() {
        return currentSortMethod;
    }

    public void setCurrentSortMethod(String method) {
        this.currentSortMethod = method;
    }

    public List<User> getTopUsersByScore(int count) {
        allUsers.sort(Comparator.comparingInt(User::getScore).reversed());
        return getTopUsers(count);
    }

    public List<User> getTopUsersByName(int count) {
        allUsers.sort(Comparator.comparing(User::getUsername));
        return getTopUsers(count);
    }

    public List<User> getTopUsersByKills(int count) {
        allUsers.sort(Comparator.comparingInt(User::getNumberOfKills).reversed());
        return getTopUsers(count);
    }

    public List<User> getTopUsersBySurvivalTime(int count) {
        allUsers.sort(Comparator.comparingInt(User::getMaxSurviveTime).reversed());
        return getTopUsers(count);
    }

    private List<User> getTopUsers(int count) {
        return allUsers.subList(0, Math.min(count, allUsers.size()));
    }
}
