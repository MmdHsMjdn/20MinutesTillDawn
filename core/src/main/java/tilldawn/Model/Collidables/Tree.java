package tilldawn.Model.Collidables;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Player;

public class Tree implements Collidable {

    private final Vector2 position;
    private final Texture treeTexture;
    private final Rectangle collisionRect;
    private final int damage = 5;
    private static final int width = 70;
    private static final int height = 92;

    public Tree(Vector2 position) {
        this.treeTexture = Main.getMain().getGameAssetManager().getTreeTexture();
        this.position = position;
        this.collisionRect = new Rectangle(position.x, position.y, width, height);
    }

    public void draw(OrthographicCamera camera) {

        float camLeft = camera.position.x - camera.viewportWidth / 2;
        float camRight = camera.position.x + camera.viewportWidth / 2;
        float camTop = camera.position.y + camera.viewportHeight / 2;
        float camBottom = camera.position.y - camera.viewportHeight / 2;

        if (position.x + width < camLeft || position.x > camRight || position.y + height < camBottom || position.y > camTop) {
            return;
        }

        Main.getBatch().draw(treeTexture, position.x, position.y, width, height);

    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void onPlayerCollision(Player player) {
        player.applyDamage(this.damage);
        player.triggerDamageAnimation(this.collisionRect , player.getCollisionRect());
    }
}
