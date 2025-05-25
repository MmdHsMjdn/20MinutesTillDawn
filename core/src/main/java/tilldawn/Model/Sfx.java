package tilldawn.Model;

public class Sfx {

    public static void playWalk1(float x) {
        AudioManager.playSound("walk1",x);
    }

    public static void playWalk2(float x) {
        AudioManager.playSound("walk2",x);
    }

    public static void playWalk3(float x) {
        AudioManager.playSound("walk3",x);
    }

    public static void playDamage(float x) {
        AudioManager.playSound("damage",x);
    }

}
