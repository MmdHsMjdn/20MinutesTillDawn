package tilldawn.Model.Weapons;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Sfx;


public class Shotgun extends Weapon{

    public Shotgun(boolean autoReload) {
        super(2,4,1.0f,1.0f, Main.getMain().getGameAssetManager().getShotgunTexture(), autoReload );
    }


    @Override
    public void shoot(Vector2 position, float angle, boolean shotByEnemy) {
        if (!canShoot()) {
            return;
        }

        --currentAmmo;

        for (int i=0 ; i<projectTile; ++i) {

            float spread = MathUtils.random(-15f,15f);

            Bullet b = new Bullet(position.cpy(),angle + spread,shotByEnemy,10);
            Main.getCurrentGameView().getBullets().add(b);
            Main.getCurrentGameView().getCollidables().add(b);

        }

        shootTimer = shootCooldown;
        Sfx.shotgunShot(1);
    }

    @Override
    public void startReloading() {
        if (!reloading && currentAmmo < maxAmmo) {
            Sfx.shotgunReload(1);
            reloading = true;
            reloadTimer = 0;
        }
    }
}
