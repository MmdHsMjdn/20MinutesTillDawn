package tilldawn.Model;

public class User {

    private String username;
    private String password;
    private String securityAnswer;
    private int score;

    public User() {}

    public User(String username, String password , String securityAnswer) {
        this.username = username;
        this.password = password;
        this.securityAnswer = securityAnswer;
        this.score = 0;
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

    public void increaseScore(int value) {
        score += value;
    }

    public int getScore() {
        return score;
    }
}
