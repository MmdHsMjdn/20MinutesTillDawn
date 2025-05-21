package tilldawn.Model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class UserManager {

    private final FileHandle file = Gdx.files.local("Users.json");
    private final Json json = new Json();

    private final ArrayList<User> users;

    public UserManager() {
        if (file.exists()) {
            users = json.fromJson(ArrayList.class, file);
        } else {
            users = new ArrayList<>();
        }
    }

    private boolean isUsernameTaken(String username) {

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;

    }

    private boolean isPasswordValid(String password) {
        return ValidationRegexes.Password.getMatcher(password) != null;
    }

    public Result addUser(String username, String password, String securityAnswer) {

        if (isUsernameTaken(username)) {
            return new Result(false, "Username is already taken.");
        }

        if (!isPasswordValid(password)) {
            return new Result(false, "Password is too weak.");
        }

        User temp = new User(username, password , securityAnswer);
        users.add(temp);

        json.toJson(users, file);

        return new Result(true, "You signed up successfully.");
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Result changeUsername(String oldUsername, String newUsername) {

        if (isUsernameTaken(newUsername)) {
            return new Result(false, "Username is already taken!");
        }

        for (User user : users) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                json.toJson(users, file);
                return new Result(true, "Username changed successfully.");
            }
        }

        return new Result(false, "User does not exist!");

    }

    public Result changePassword(String username ,String newPassword) {

        if (!isPasswordValid(newPassword)) {
            return new Result(false, "Password is too weak!");
        }

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                json.toJson(users, file);
                return new Result(true, "Password changed successfully.");
            }
        }

        return new Result(false, "User does not exist!");
    }

}
