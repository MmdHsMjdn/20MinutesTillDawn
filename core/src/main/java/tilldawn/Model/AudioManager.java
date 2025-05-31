package tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import tilldawn.Main;

import java.util.HashMap;

public class AudioManager {

    private static final HashMap<String, Sound> soundMap = new HashMap<>();
    private static final HashMap<String, Music> musicMap = new HashMap<>();

    private static boolean soundEnabled = true;
    private static boolean musicEnabled = true;

    private static void loadSound(String name, String path) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        soundMap.put(name, sound);
    }

    private static void loadMusic(String name, String path, boolean looping) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.setLooping(looping);
        musicMap.put(name, music);
    }


    public static void playSound(String name, float volume) {

        if (!Main.getMain().isSFXOn()) {
            return;
        }

        if (soundEnabled && soundMap.containsKey(name)) {
            soundMap.get(name).play(volume);
        }

    }

    public static void changeMusic() {
        if (musicMap.get("championsLeague").isPlaying()) {
            musicMap.get("championsLeague").stop();
            playMusic("caribbean");
        } else {
            musicMap.get("caribbean").stop();
            playMusic("championsLeague");
        }
    }

    public static void playMusic(String name) {
        if (musicEnabled && musicMap.containsKey(name)) {
            musicMap.get(name).play();
        }
    }

    public static void stopMusic(String name) {
        if (musicMap.containsKey(name)) {
            musicMap.get(name).stop();
        }
    }

    public static void stopAllMusic() {
        for (Music music : musicMap.values()) {
            music.stop();
        }
    }

    public static void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;
    }

    public static void setMusicEnabled(boolean enabled) {
        musicEnabled = enabled;
        if (!enabled) {
            stopAllMusic();
        }
    }

    public static void setMusicVolume(float volume) {
        for (Music music : musicMap.values()) {
            music.setVolume(volume);
        }
    }

    public static float getMusicVolume() {
        for (Music music : musicMap.values()) {
            if (music.isPlaying()) {
                return music.getVolume();
            }
        }
        return 0;
    }

    public static void dispose() {
        for (Sound s : soundMap.values()) {
            s.dispose();
        }
        for (Music m : musicMap.values()) {
            m.dispose();
        }
    }

    public static void loadAll() {
        loadAllMusics();
        loadAllSounds();
    }

    private static void loadAllSounds() {
        loadSound("walk1","sfx/st3-footstep-sfx-323056.mp3");
        loadSound("walk2","sfx/regularfootstep002-47146.mp3");
        loadSound("walk3", "sfx/Footsteps_Casual_Grass_01.wav");
        loadSound("damage","sfx/Blood_Splash_Quick_01.wav");
        loadSound("shotgunReload","sfx/Weapon_Shotgun_Reload.wav");
        loadSound("revolverReload","sfx/revolver-chamber-spin-ratchet-sound-90521.mp3");
        loadSound("smgReload","sfx/machine-gun-reload-81593.mp3");
        loadSound("emptyAmmo" , "sfx/empty-gun-shot-6209.mp3");
        loadSound("revolverShot","sfx/desert-eagle-gunshot-14622.mp3");
        loadSound("smgShot","sfx/toy-pistol-82582.mp3");
        loadSound("shotgunShot","sfx/gun-shot-1-7069.mp3");
        loadSound("eyebatDeath","sfx/Bat_Death_02.wav");
        loadSound("monsterDeath","sfx/Explosion_Blood_01.wav");
        loadSound("click","sfx/UI Click 36.wav");
        loadSound("obtainDrop","sfx/Obtain_Points_01.wav");
        loadSound("xpLevelUp","sfx/Buff_Power_Up_01.wav");

    }

    public static void loadAllMusics() {
        loadMusic("championsLeague","musics/_- Champions League (128).mp3",true);
        loadMusic("caribbean","musics/pirate-of-caribbean.mp3",true);
    }
}
