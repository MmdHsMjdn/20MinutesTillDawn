package tilldawn.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import tilldawn.Main;
import tilldawn.Model.Player;

public class PlayerController {

    private final Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void update(float delta, Vector2 position, Vector3 mouseWorldPosition) {
        player.update(delta, position, mouseWorldPosition);
    }

    public void draw(float delta) {
        player.draw(Main.getBatch(),delta);
    }
}
