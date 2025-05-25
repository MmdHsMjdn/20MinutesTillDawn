package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;

import java.util.Iterator;

public class BulletController {

    public void draw() {
        for (Bullet bullet : Main.getCurrentGameView().getBullets()) {
            bullet.draw();
        }
    }

    public void update(float delta) {

        checkDamage();
        Iterator<Bullet> iterator = Main.getCurrentGameView().getBullets().iterator();

        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update(delta);

            if (bullet.isDestroyed()) {
                Main.getCurrentGameView().getCollidables().remove(bullet);
                iterator.remove();
            }
        }

    }

    private void checkDamage() {
        //TODO
    }
}
