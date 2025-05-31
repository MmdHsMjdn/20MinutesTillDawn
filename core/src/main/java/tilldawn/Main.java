package tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tilldawn.Model.AudioManager;
import tilldawn.Model.Bgm;
import tilldawn.Model.DefaultKeys.DefaultsKeys;
import tilldawn.Model.GameAssetManager;
import tilldawn.Model.UserManager;
import tilldawn.View.GameView;
import tilldawn.View.SettingMenuView;
import tilldawn.View.SignUpMenuView;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class Main extends Game {

    private static Main main;
    private static SpriteBatch batch;
    private static GameAssetManager gameAssetManager;
    private static UserManager userManager;
    private static GameView currentGameView;

    private boolean autoReload = false;
    private boolean SFXOn = true;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        gameAssetManager = new GameAssetManager();
        userManager = new UserManager();
        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor/tap.png"));
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();
        AudioManager.loadAll();
        Bgm.playChampionsLeague();
        DefaultsKeys.loadFromPreferences();
        getMain().setScreen(new SignUpMenuView());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public GameAssetManager getGameAssetManager() {
        return gameAssetManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public static GameView getCurrentGameView() {
        return currentGameView;
    }

    public static void setCurrentGameView(GameView currentGameView) {
        Main.currentGameView = currentGameView;
    }

    public boolean isSFXOn() {
        return SFXOn;
    }

    public void setSFXOn(boolean SFXOn) {
        this.SFXOn = SFXOn;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }
}
