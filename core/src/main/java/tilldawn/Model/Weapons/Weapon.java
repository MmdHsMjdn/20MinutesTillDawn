package tilldawn.Model.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import tilldawn.Model.Sfx;


public abstract class Weapon {

    protected int maxAmmo;
    protected int projectTile;
    protected int currentAmmo;
    protected float reloadTime;
    protected float reloadTimer;
    protected boolean reloading;
    protected float shootCooldown = 0.0f;
    protected float shootTimer;
    private boolean autoReload;
    protected Texture texture;

    public Weapon(int maxAmmo, int projectTile, float reloadTime, float shootCooldown, Texture texture , boolean autoReload) {
        this.maxAmmo = maxAmmo;
        this.projectTile = projectTile;
        this.currentAmmo = maxAmmo;
        this.reloadTime = reloadTime;
        this.reloading = false;
        this.shootCooldown = shootCooldown;
        this.texture = texture;
        this.autoReload = autoReload;
    }

    public void update(float delta) {


        if (currentAmmo == 0 && autoReload) {
            this.startReloading();
        }

        if (reloading) {
            reloadTimer += delta;
            if (reloadTimer >= reloadTime) {
                currentAmmo = maxAmmo;
                reloading = false;
                reloadTimer = 0;
            }
        }

        if (shootTimer > 0) {
            shootTimer -= delta;
            shootTimer = Math.max(0, shootTimer);
        }

    }


    public abstract void shoot(Vector2 position, float angle, boolean shotByEnemy);

    public boolean canShoot() {

        if (currentAmmo == 0) {
            Sfx.emptyAmmo(1);
            return false;
        }

        return !reloading && shootTimer == 0;
    }

    public abstract void startReloading();

    public boolean isReloading() {
        return reloading;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }
}
