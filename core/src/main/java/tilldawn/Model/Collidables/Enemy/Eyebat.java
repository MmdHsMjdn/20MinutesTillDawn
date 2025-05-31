package tilldawn.Model.Collidables.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Player;

public class Eyebat extends Enemy {

    private final int damage = 5;
    private static float spawnTimer = 0.0f;
    private float shotTimer = 0.0f;

    public Eyebat(Vector2 position) {
        super(position , 50);
        this.enemyTexture = new Texture("Sprite/T/T_EyeBat_0.png");
        this.moveAnimation = Main.getMain().getGameAssetManager().getEyebatMoveAnimation();
        this.enemySprite = new Sprite(enemyTexture);
        this.enemySprite.setOriginCenter();
        this.enemySprite.setScale(0.7f);
        this.collisionRect = new Rectangle(
            position.x - enemySprite.getWidth()*enemySprite.getScaleX()/2f,
            position.y - enemySprite.getHeight()*enemySprite.getScaleY()/2f,
            enemySprite.getWidth()*enemySprite.getScaleX(),
            enemySprite.getHeight()*enemySprite.getScaleY()
        );

    }

    public static void spawn(float delta) {

        spawnTimer += delta;

    if (!(Main.getCurrentGameView().getPassedTime() > Main.getCurrentGameView().getTotalMinutes()*15 )) {
        return;
    }

    if (spawnTimer >= 10f) {
        spawnTimer = 0.0f;
    } else {
        return;
    }

    for (int i = 0; i < (4*Main.getCurrentGameView().getPassedTime() - Main.getCurrentGameView().getTotalMinutes()*60 + 30)/30 ; i++) {

        Vector2 position = Enemy.randomValidPosition(800,1000);
        if (position != null) {
            Eyebat eyebat = new Eyebat(position);
            Main.getCurrentGameView().enemies().add(eyebat);
            Main.getCurrentGameView().getCollidables().add(eyebat);
        }

    }


    }

    @Override
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

        shotTimer += delta;
        if (shotTimer >= 3f) {
            shotTimer = 0.0f;
            shoot();
        }
    }

    private void shoot() {
        Bullet b = new Bullet(this.position.cpy(),this.enemySprite.getRotation(),true,10);
        Main.getCurrentGameView().getBullets().add(b);
        Main.getCurrentGameView().getCollidables().add(b);
    }

    @Override
    public void onPlayerCollision(Player player) {
        player.applyDamage(this.damage);
        player.triggerDamageAnimation(this.collisionRect , player.getCollisionRect());
    }
}
