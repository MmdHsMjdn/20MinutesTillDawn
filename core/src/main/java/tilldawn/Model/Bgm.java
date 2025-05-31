package tilldawn.Model;

public class Bgm {

    public static void playCaribbean() {
        AudioManager.stopAllMusic();
        AudioManager.playMusic("caribbean");
    }

    public static void playChampionsLeague() {
        AudioManager.stopAllMusic();
        AudioManager.playMusic("championsLeague");
    }
}
