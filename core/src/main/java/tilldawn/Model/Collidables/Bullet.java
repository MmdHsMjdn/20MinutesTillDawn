package tilldawn.Model.Collidables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Player;

public class Bullet implements Collidable {

    private final Texture texture;
    private final Vector2 position;
    private final Vector2 velocity;
    private final float speed = 1200f;
    private final int damage; //change damage
    private boolean destroyed = false;
    private final boolean shotByEnemy;
    private final Rectangle collisionRect;
    private final float width = 40 , height = 40;
    private float lifeTime = 0.0f;
    private final float maxLifeTime = 2f;


    public Bullet(Vector2 startPosition, float angelDeg , boolean shotByEnemy , int damage) {
        this.position = new Vector2(startPosition);
        this.velocity = new Vector2(1,0).setAngleDeg(angelDeg).scl(speed);
        this.texture = Main.getMain().getGameAssetManager().getBulletTexture();
        this.shotByEnemy = shotByEnemy;
        this.collisionRect = new Rectangle(position.x, position.y, width, height);
        this.damage = damage;
    }

    public void update(float delta) {
        lifeTime += delta;
        position.mulAdd(velocity, delta);
        collisionRect.setPosition(position);
        checkDestroyed();
    }

    private void checkDestroyed() {

        if (lifeTime > maxLifeTime) {
            destroyed = true;
        }

        for (Collidable collidable : Main.getCurrentGameView().getCollidables()) {
            if (collidable instanceof Bullet) {
                if (((Bullet) collidable).shotByEnemy == this.shotByEnemy) {
                    continue;
                }
            }
            if (this.collisionRect.overlaps(collidable.getCollisionRect())) {
                destroyed = true;
            }
        }

    }

    public void draw() {
        Main.getBatch().draw(texture, position.x, position.y,width,height);
    }

    @Override
    public Rectangle getCollisionRect() {
        return this.collisionRect;
    }

    @Override
    public void onPlayerCollision(Player player) {
        player.applyDamage(this.damage);
        player.triggerDamageAnimation(this.collisionRect , player.getCollisionRect());
    }

    public boolean isShotByEnemy() {
        return shotByEnemy;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
