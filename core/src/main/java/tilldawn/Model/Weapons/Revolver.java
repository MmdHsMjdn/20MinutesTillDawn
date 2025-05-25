package tilldawn.Model.Weapons;

import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Sfx;


public class Revolver extends Weapon {

    public Revolver(boolean autoReload) {
        super(6, 1, 1.0f, 0.5f, Main.getMain().getGameAssetManager().getRevolverTexture(), autoReload);
    }


    @Override
    public void shoot(Vector2 position, float angle, boolean shotByEnemy) {
        if (!canShoot()) {
            return;
        }

        --currentAmmo;

        for (int i = 0; i < projectTile; ++i) {
            Bullet b = new Bullet(position.cpy(), angle, shotByEnemy, 20);
            Main.getCurrentGameView().getBullets().add(b);
            Main.getCurrentGameView().getCollidables().add(b);
        }

        shootTimer = shootCooldown;
        Sfx.revolverShot(1);
    }

    @Override
    public void startReloading() {
        if (!reloading && currentAmmo < maxAmmo) {
            Sfx.revolverReload(1);
            reloading = true;
            reloadTimer = 0;
        }
    }
}
