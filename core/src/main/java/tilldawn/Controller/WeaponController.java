package tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
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

    public void updateWeapon(Player player, float delta) {

        player.getDefaultWeapon().update(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            player.setAutoReload();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            player.getDefaultWeapon().startReloading();
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            player.shoot();
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && player.getDefaultWeapon() instanceof Smg) {
            player.shoot();
        }


    }
}
