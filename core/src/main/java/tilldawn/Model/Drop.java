package tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;

public class Drop {

    private final Texture dropTexture;
    private final Vector2 position;
    private final Rectangle collisionRect;
    private final float width = 40 , height = 40;

    public Drop(Vector2 position) {
        this.position = position;
        this.dropTexture = Main.getMain().getGameAssetManager().getDropTexture();
        this.collisionRect = new Rectangle(position.x, position.y, width, height);
    }

    public void draw() {
        Main.getBatch().draw(dropTexture, position.x, position.y,width,height);
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }
}
