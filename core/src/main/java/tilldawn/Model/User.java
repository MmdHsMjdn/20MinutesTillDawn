package tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import tilldawn.Main;

import java.util.Random;

public class User {

    private String username;
    private String password;
    private String securityAnswer;
    private int score;
    private int numberOfKills;
    private int maxSurviveTime;
    private String avatarAddress;

    public User() {}

    public User(String username, String password , String securityAnswer) {
        this.username = username;
        this.password = password;
        this.securityAnswer = securityAnswer;
        this.score = 0;
        this.numberOfKills = 0;
        this.maxSurviveTime = 0;

        Random rand = new Random();
        if (rand.nextBoolean()) {
            this.avatarAddress = "Sprite/Avatar/male.jpg";
        } else {
            this.avatarAddress = "Sprite/Avatar/female.jpg";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void addToScore(int value) {
        score += value;
        Main.getMain().getUserManager().saveUsers();
    }

    public int getScore() {
        return score;
    }

    public void setAvatarAddress(String avatarAddress) {
        this.avatarAddress = avatarAddress;
        Main.getMain().getUserManager().saveUsers();
    }

    public Texture getAvatar() {
        return new Texture(avatarAddress);
    }

    public int getMaxSurviveTime() {
        return maxSurviveTime;
    }

    public void addMaxSurviveTime(int maxSurviveTime) {
        if (this.maxSurviveTime < maxSurviveTime) {
            this.maxSurviveTime = maxSurviveTime;
            Main.getMain().getUserManager().saveUsers();
        }
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void addToNumberOfKills(int numberOfKills) {
        this.numberOfKills += numberOfKills;
        Main.getMain().getUserManager().saveUsers();
    }
}
