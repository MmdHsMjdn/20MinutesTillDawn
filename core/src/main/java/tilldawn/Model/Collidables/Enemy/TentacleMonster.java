package tilldawn.Model.Collidables.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Player;

public class TentacleMonster extends Enemy {

    private final int damage = 1;//edit damage
    private static float spawnTimer = 0.0f;

    public TentacleMonster(Vector2 position) {
        super(position , 25);
        this.enemyTexture = new Texture("Sprite/BrainMonster/BrainMonster_0.png");
        this.moveAnimation = Main.getMain().getGameAssetManager().getTentacleMoveAnimation();
        this.enemySprite = new Sprite(enemyTexture);
        this.enemySprite.setOriginCenter();
        this.enemySprite.setScale(1.5f);
        this.collisionRect = new Rectangle(
            position.x - enemySprite.getWidth()*enemySprite.getScaleX()/2f,
            position.y - enemySprite.getHeight()*enemySprite.getScaleY()/2f,
            enemySprite.getWidth()*enemySprite.getScaleX(),
            enemySprite.getHeight()*enemySprite.getScaleY()
        );
    }

    public static void spawn(float delta) {

        spawnTimer += delta;

        if (spawnTimer >= 3f) {
            spawnTimer = 0.0f;
        } else {
            return;
        }

        for (int i = 0; i< Main.getCurrentGameView().getPassedTime()/30 ; ++i ) {

            Vector2 position = Enemy.randomValidPosition(600,1500);
            if (position != null) {
                TentacleMonster monster = new TentacleMonster(position);
                Main.getCurrentGameView().enemies().add(monster);
                Main.getCurrentGameView().getCollidables().add(monster);
            }
        }

    }

    @Override
    public void onPlayerCollision(Player player) {
        player.applyDamage(this.damage);
        player.triggerDamageAnimation(this.collisionRect , player.getCollisionRect());
    }
}
