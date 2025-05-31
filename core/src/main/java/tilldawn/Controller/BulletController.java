package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Collidables.Collidable;
import tilldawn.Model.Collidables.Enemy.Enemy;

import java.util.Iterator;

public class BulletController {

    public void draw() {
        for (Bullet bullet : Main.getCurrentGameView().getBullets()) {
            bullet.draw();
        }
    }

    public void update(float delta) {

        Iterator<Bullet> iterator = Main.getCurrentGameView().getBullets().iterator();

        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update(delta);

            if (bullet.getCollisionRect().overlaps(Main.getCurrentGameView().getPlayer().getCollisionRect()) && bullet.isShotByEnemy()) {
                bullet.onPlayerCollision(Main.getCurrentGameView().getPlayer());
            }

            for (Collidable collidable : Main.getCurrentGameView().getCollidables()) {
                if (collidable instanceof Enemy && !bullet.isShotByEnemy()) {
                    if (collidable.getCollisionRect().overlaps(bullet.getCollisionRect())) {
                        bullet.onEnemyCollision((Enemy) collidable);
                        ((Enemy) collidable).applyKnockDown();
                    }
                }
            }

            if (bullet.isDestroyed()) {
                Main.getCurrentGameView().getCollidables().remove(bullet);
                iterator.remove();
            }
        }

    }

}
