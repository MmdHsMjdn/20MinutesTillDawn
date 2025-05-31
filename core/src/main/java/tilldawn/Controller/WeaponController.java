package tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.DefaultKeys.DefaultsKeys;
import tilldawn.Model.DefaultKeys.InputManager;
import tilldawn.Model.Player;
import tilldawn.Model.Weapons.Revolver;
import tilldawn.Model.Weapons.Shotgun;
import tilldawn.Model.Weapons.Smg;

public class WeaponController {

    public void draw(Player player) {

        Vector2 weaponOffset = new Vector2(30, 0);
        weaponOffset.setAngleDeg(player.getPlayerSprite().getRotation());
        Vector2 weaponPosition = player.getPosition().cpy().add(weaponOffset);

        TextureRegion weaponTextureRegion = new TextureRegion(player.getDefaultWeapon().getTexture());

        Main.getBatch().draw(
            weaponTextureRegion
            , weaponPosition.x - weaponTextureRegion.getRegionWidth() / 2f,
            weaponPosition.y - weaponTextureRegion.getRegionHeight() / 2f,
            weaponTextureRegion.getRegionWidth() / 2f,
            weaponTextureRegion.getRegionHeight() / 2f,
            weaponTextureRegion.getRegionWidth(),
            weaponTextureRegion.getRegionHeight(),
            3.5f, 3.5f,
            player.getPlayerSprite().getRotation());
    }

    public void autoAimUpdate() {
        if (InputManager.isKeyJustPressed(DefaultsKeys.AutoAim)) {
            Main.getCurrentGameView().changeAutoAim();
        }
    }

    public void updateWeapon(Player player, float delta) {

        player.getDefaultWeapon().update(delta);

        if (InputManager.isKeyJustPressed(DefaultsKeys.ChangeWeapon)) {

            boolean autoReload = player.getDefaultWeapon().isAutoReload();

            if (player.getDefaultWeapon() instanceof Revolver) {
                player.setDefaultWeapon(new Shotgun(autoReload));
            } else if (player.getDefaultWeapon() instanceof Shotgun) {
                player.setDefaultWeapon(new Smg(autoReload));
            } else if (player.getDefaultWeapon() instanceof Smg) {
                player.setDefaultWeapon(new Revolver(autoReload));
            }

            return;
        }

        if (InputManager.isKeyJustPressed(DefaultsKeys.AutoReload)) {
            player.setAutoReload();
        }

        if (InputManager.isKeyPressed(DefaultsKeys.Reload)) {
            player.reload();
        }

        if (InputManager.isKeyJustPressed(DefaultsKeys.Shoot)) {
            player.shoot();
        }

        if (InputManager.isKeyPressed(DefaultsKeys.Shoot) && player.getDefaultWeapon() instanceof Smg) {
            player.shoot();
        }


    }
}
