package tilldawn.Model.Collidables.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Collidables.Collidable;

public abstract class Enemy implements Collidable {

    protected Texture enemyTexture;
    protected Animation<TextureRegion> moveAnimation;
    protected TextureRegion currentFrame;
    protected Sprite enemySprite;
    protected Vector2 position = new Vector2();
    protected float stateTime = 0.0f;
    protected final float speed = 60;
    protected Vector2 direction = new Vector2();
    protected float HP;
    protected float maxHP;
    protected Rectangle collisionRect;
    protected boolean knockDown = false;
    protected boolean isDead = false;

    public Enemy(Vector2 position, float maxHP) {
        this.position = position;
        this.maxHP = maxHP;
        this.HP = maxHP;
    }


    public void update(float delta, Vector2 playerPosition) {

        this.direction.set(playerPosition).sub(position);
        if (!direction.isZero()) {
            direction.nor();
        }

        updateMovement(delta, direction.cpy());
        updateAnimation(delta);
        updateSprite();
        updateCollisionRect();
        this.knockDown = false;
    }


    protected void updateMovement(float delta, Vector2 direction) {


        Vector2 movement = new Vector2();

        if (knockDown) {
            movement.set(direction.cpy().scl(-speed/5));
        } else {
            movement.set(direction.cpy().scl(speed * delta));
        }
        Vector2 newPosition = new Vector2(position).add(movement);

        boolean collided = false;
        if (this.collisionRect.overlaps(Main.getCurrentGameView().getPlayer().getCollisionRect())) {
            collided = true;
            this.onPlayerCollision(Main.getCurrentGameView().getPlayer());
        }

        for (Collidable c : Main.getCurrentGameView().getCollidables()) {

            if (c instanceof Enemy) {
                continue;
            }

            if (c instanceof Bullet) {
                if (((Bullet) c).isShotByEnemy()) {
                    continue;
                }
            }

            Rectangle testRect = new Rectangle(
                newPosition.x - collisionRect.width/2,
                newPosition.y - collisionRect.height/2,
                collisionRect.width,
                collisionRect.height
            );

            if (testRect.overlaps(c.getCollisionRect())) {
                collided = true;
                break;
            }

        }

        if (!collided) {
            position.set(newPosition);
        }

    }


    protected void updateAnimation(float delta) {
        this.stateTime += delta;
        this.currentFrame = moveAnimation.getKeyFrame(stateTime, true);
    }


    protected void updateSprite() {
        this.enemySprite.setRegion(this.currentFrame);
        this.enemySprite.setRotation(this.direction.angleDeg());
        this.enemySprite.setPosition(
            this.position.x - this.enemySprite.getWidth()/2,
            this.position.y - this.enemySprite.getHeight()/2
        );
    }

    protected void updateCollisionRect() {
        this.collisionRect.setPosition(position.x - collisionRect.width/2f, position.y - collisionRect.height/2f);
    }

    public void applyKnockDown() {
        this.knockDown = true;
    }

    public void draw() {
        enemySprite.draw(Main.getBatch());
    }

    protected static Vector2 randomValidPosition(float minDistance, float maxDistance) {

        int maxAttempts = 5;

        for (int i = 0; i < maxAttempts; i++) {

            float angel = MathUtils.random(0f,360f);
            float distance = MathUtils.random(minDistance, maxDistance);

            float x = Main.getCurrentGameView().getPlayer().getPosition().x + MathUtils.cos(angel) * distance;
            float y = Main.getCurrentGameView().getPlayer().getPosition().y + MathUtils.sin(angel) * distance;

            Rectangle testRect = new Rectangle(x, y, 64, 64);

            boolean overlaps = false;

            for (Collidable c : Main.getCurrentGameView().getCollidables()) {

                if (c instanceof Bullet) {
                    continue;
                }

                if (testRect.overlaps(c.getCollisionRect())) {
                    overlaps = true;
                    break;
                }

            }

            if (!overlaps) {
                return new Vector2(x, y);
            }

        }

        return null;


    }

    public boolean isDead() {
        return isDead;
    }

    @Override
    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public void decreaseHP(float value) {
        HP -= value;
        if (HP <= 0) {
            HP = 0;
            isDead = true;
        }
    }

    public float getHP() {
        return HP;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getEnemyTexture() {
        return enemyTexture;
    }
}
