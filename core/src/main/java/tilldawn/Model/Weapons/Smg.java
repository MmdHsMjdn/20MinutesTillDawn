package tilldawn.Model.Weapons;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Sfx;


public class Smg extends Weapon{

    private float lastShotSoundTimer = 1.0f;
    private float lastEmptyAmmoTimer = 1.0f;

    public Smg(boolean autoReload) {
        super(24,1,2.0f,0.1f,Main.getMain().getGameAssetManager().getSmgTexture() , autoReload);
    }

    @Override
    public void shoot(Vector2 position, float angle , boolean shotByEnemy ,float ratio) {
        if (!canShoot()) {
            return;
        }

        --currentAmmo;

        for (int i=0 ; i<projectTile; ++i) {

            float spread = MathUtils.random(-5f,5f);

            Bullet b = new Bullet(position.cpy(),angle + spread,shotByEnemy,8);
            Main.getCurrentGameView().getBullets().add(b);
            Main.getCurrentGameView().getCollidables().add(b);
        }


        shootTimer = shootCooldown;

        if (lastShotSoundTimer >= 1.0f) {
            lastShotSoundTimer = 0.0f;
            Sfx.smgShot(1);
        }

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        lastShotSoundTimer += delta;
        lastEmptyAmmoTimer += delta;
    }

    @Override
    public void startReloading() {
        if (!reloading && currentAmmo < maxAmmo) {
            Sfx.smgReload(1);
            reloading = true;
            reloadTimer = 0;
        }
    }

    @Override
    public boolean canShoot() {

        if (currentAmmo == 0) {
            if (lastEmptyAmmoTimer >= 0.5f) {
                Sfx.emptyAmmo(1);
                lastEmptyAmmoTimer = 0.0f;
            }
            return false;
        }

        return !reloading && shootTimer == 0;
    }

}
