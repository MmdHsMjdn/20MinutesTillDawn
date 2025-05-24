package tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tilldawn.Model.AudioManager;
import tilldawn.Model.GameAssetManager;
import tilldawn.Model.UserManager;
import tilldawn.View.GameView;
import tilldawn.View.SignUpMenuView;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class Main extends Game {

    private static Main main;
    private static SpriteBatch batch;
    private static GameAssetManager gameAssetManager;
    private static UserManager userManager;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        gameAssetManager = new GameAssetManager();
        userManager = new UserManager();
        AudioManager.loadAll();
        getMain().setScreen(new GameView(11));
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

}
