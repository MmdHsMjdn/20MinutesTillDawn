package tilldawn.Controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import tilldawn.Main;
import tilldawn.Model.DefaultKeys.DefaultsKeys;
import tilldawn.Model.DefaultKeys.InputManager;
import tilldawn.Model.Sfx;
import tilldawn.Model.World;

public class WorldController {
    private final OrthographicCamera camera;
    private final World world;

    public WorldController(World world, OrthographicCamera camera) {
        this.camera = camera;
        this.world = world;
    }

    public void update(Vector2 playerPosition, Vector3 mouseWorldPosition) {

        if (InputManager.isKeyJustPressed(DefaultsKeys.DecreaseTime)) {
            Main.getCurrentGameView().addToPassedTime(60);
        }

        if (InputManager.isKeyJustPressed(DefaultsKeys.IncreaseLevel)) {
            if (Main.getCurrentGameView().getPlayer().increaseXp(Main.getCurrentGameView().getPlayer().neededXpForNextLevel())) {
                Sfx.xpLevelUp(1);
                Main.getCurrentGameView().getController().setTemporaryMessage("Level Up!",2);
            }
        }

        if (InputManager.isKeyJustPressed(DefaultsKeys.IncreaseHealth)) {
            Main.getCurrentGameView().getPlayer().setHealth(Main.getCurrentGameView().getPlayer().getHealth() * 1.2f);
            Main.getCurrentGameView().getPlayer().setHealth(Math.min(Main.getCurrentGameView().getPlayer().getMaxHealth(), Main.getCurrentGameView().getPlayer().getHealth()));
            Main.getCurrentGameView().getController().setTemporaryMessage("Health increased!",2)    ;
        }

        world.update(playerPosition, camera, mouseWorldPosition);
    }

    public void draw() {
        world.draw(camera);
    }
}
