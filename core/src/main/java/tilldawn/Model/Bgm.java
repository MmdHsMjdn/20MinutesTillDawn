package tilldawn.Model;

public class Bgm {

    public static void playMenu(){
        AudioManager.stopAllMusic();
        AudioManager.playMusic("menu");
    }
}
