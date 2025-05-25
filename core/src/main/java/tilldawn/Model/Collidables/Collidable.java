package tilldawn.Model.Collidables;

import com.badlogic.gdx.math.Rectangle;
import tilldawn.Model.Player;

public interface Collidable {
    Rectangle getCollisionRect();
    void onPlayerCollision(Player player);

}
