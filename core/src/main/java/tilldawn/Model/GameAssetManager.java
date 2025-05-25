package tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Arrays;

public class GameAssetManager {

    private Animation<TextureRegion> character1WalkAnimation;
    private Animation<TextureRegion> character2WalkAnimation;
    private Animation<TextureRegion> character3WalkAnimation;
    private Animation<TextureRegion> character4WalkAnimation;
    private Animation<TextureRegion> character5WalkAnimation;
    private Animation<TextureRegion> character6WalkAnimation;
    private Animation<TextureRegion> character7WalkAnimation;
    private Animation<TextureRegion> character8WalkAnimation;
    private Animation<TextureRegion> character9WalkAnimation;
    private Animation<TextureRegion> character10WalkAnimation;
    private Animation<TextureRegion> character11WalkAnimation;
    private Animation<TextureRegion> damageAnimation;
    private Texture basicTileTexture;
    private Texture treeTexture;
    private Texture smgTexture;
    private Texture revolverTexture;
    private Texture shotgunTexture;
    private Texture bulletTexture;


    public GameAssetManager() {
        createWalkAnimations();
        createBasicTileTexture();
        createTreeTexture();
        createDamage();
        createWeapons();
        createBullet();
    }

    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    public Skin getSkin() {
        return skin;
    }

    private void createWalkAnimations() {

         ArrayList<TextureRegion> idle1WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char1/Idle_01.png")),
            new TextureRegion(new Texture("Sprite/Idle/char1/Idle_11.png")),
            new TextureRegion(new Texture("Sprite/Idle/char1/Idle_21.png")),
            new TextureRegion(new Texture("Sprite/Idle/char1/Idle_31.png")),
            new TextureRegion(new Texture("Sprite/Idle/char1/Idle_41.png")),
            new TextureRegion(new Texture("Sprite/Idle/char1/Idle_51.png"))
        ));

         TextureRegion[] idle1WalkFrames = idle1WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle2WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char2/Idle_02.png")),
            new TextureRegion(new Texture("Sprite/Idle/char2/Idle_12.png")),
            new TextureRegion(new Texture("Sprite/Idle/char2/Idle_22.png")),
            new TextureRegion(new Texture("Sprite/Idle/char2/Idle_32.png")),
            new TextureRegion(new Texture("Sprite/Idle/char2/Idle_42.png")),
            new TextureRegion(new Texture("Sprite/Idle/char2/Idle_52.png"))
        ));

         TextureRegion[] idle2WalkFrames = idle2WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle3WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char3/Idle_03.png")),
            new TextureRegion(new Texture("Sprite/Idle/char3/Idle_13.png")),
            new TextureRegion(new Texture("Sprite/Idle/char3/Idle_23.png")),
            new TextureRegion(new Texture("Sprite/Idle/char3/Idle_33.png")),
            new TextureRegion(new Texture("Sprite/Idle/char3/Idle_43.png")),
            new TextureRegion(new Texture("Sprite/Idle/char3/Idle_53.png"))
        ));

         TextureRegion[] idle3WalkFrames = idle3WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle4WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char4/Idle_04.png")),
            new TextureRegion(new Texture("Sprite/Idle/char4/Idle_14.png")),
            new TextureRegion(new Texture("Sprite/Idle/char4/Idle_24.png")),
            new TextureRegion(new Texture("Sprite/Idle/char4/Idle_34.png")),
            new TextureRegion(new Texture("Sprite/Idle/char4/Idle_44.png")),
            new TextureRegion(new Texture("Sprite/Idle/char4/Idle_54.png"))
        ));

         TextureRegion[] idle4WalkFrames = idle4WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle5WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char5/Idle_05.png")),
            new TextureRegion(new Texture("Sprite/Idle/char5/Idle_15.png")),
            new TextureRegion(new Texture("Sprite/Idle/char5/Idle_25.png")),
            new TextureRegion(new Texture("Sprite/Idle/char5/Idle_35.png")),
            new TextureRegion(new Texture("Sprite/Idle/char5/Idle_45.png")),
            new TextureRegion(new Texture("Sprite/Idle/char5/Idle_55.png"))
        ));

         TextureRegion[] idle5WalkFrames = idle5WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle6WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char6/Idle_06.png")),
            new TextureRegion(new Texture("Sprite/Idle/char6/Idle_16.png")),
            new TextureRegion(new Texture("Sprite/Idle/char6/Idle_26.png")),
            new TextureRegion(new Texture("Sprite/Idle/char6/Idle_36.png")),
            new TextureRegion(new Texture("Sprite/Idle/char6/Idle_46.png")),
            new TextureRegion(new Texture("Sprite/Idle/char6/Idle_56.png"))
        ));

         TextureRegion[] idle6WalkFrames = idle6WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle7WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char7/Idle_07.png")),
            new TextureRegion(new Texture("Sprite/Idle/char7/Idle_17.png")),
            new TextureRegion(new Texture("Sprite/Idle/char7/Idle_27.png")),
            new TextureRegion(new Texture("Sprite/Idle/char7/Idle_37.png")),
            new TextureRegion(new Texture("Sprite/Idle/char7/Idle_47.png")),
            new TextureRegion(new Texture("Sprite/Idle/char7/Idle_57.png"))
        ));

         TextureRegion[] idle7WalkFrames = idle7WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle8WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char8/Idle_08.png")),
            new TextureRegion(new Texture("Sprite/Idle/char8/Idle_18.png")),
            new TextureRegion(new Texture("Sprite/Idle/char8/Idle_28.png")),
            new TextureRegion(new Texture("Sprite/Idle/char8/Idle_38.png")),
            new TextureRegion(new Texture("Sprite/Idle/char8/Idle_48.png")),
            new TextureRegion(new Texture("Sprite/Idle/char8/Idle_58.png"))
        ));

         TextureRegion[] idle8WalkFrames = idle8WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle9WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char9/Idle_09.png")),
            new TextureRegion(new Texture("Sprite/Idle/char9/Idle_19.png")),
            new TextureRegion(new Texture("Sprite/Idle/char9/Idle_29.png")),
            new TextureRegion(new Texture("Sprite/Idle/char9/Idle_39.png")),
            new TextureRegion(new Texture("Sprite/Idle/char9/Idle_49.png")),
            new TextureRegion(new Texture("Sprite/Idle/char9/Idle_59.png"))
        ));

         TextureRegion[] idle9WalkFrames = idle9WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle10WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char10/Idle_010.png")),
            new TextureRegion(new Texture("Sprite/Idle/char10/Idle_110.png")),
            new TextureRegion(new Texture("Sprite/Idle/char10/Idle_210.png")),
            new TextureRegion(new Texture("Sprite/Idle/char10/Idle_310.png")),
            new TextureRegion(new Texture("Sprite/Idle/char10/Idle_410.png")),
            new TextureRegion(new Texture("Sprite/Idle/char10/Idle_510.png"))
        ));

         TextureRegion[] idle10WalkFrames = idle10WalkFramesList.toArray(new TextureRegion[0]);

         ArrayList<TextureRegion> idle11WalkFramesList = new ArrayList<>(Arrays.asList(
            new TextureRegion(new Texture("Sprite/Idle/char11/Idle_011.png")),
            new TextureRegion(new Texture("Sprite/Idle/char11/Idle_111.png")),
            new TextureRegion(new Texture("Sprite/Idle/char11/Idle_211.png")),
            new TextureRegion(new Texture("Sprite/Idle/char11/Idle_311.png")),
            new TextureRegion(new Texture("Sprite/Idle/char11/Idle_411.png")),
            new TextureRegion(new Texture("Sprite/Idle/char11/Idle_511.png"))
        ));

         TextureRegion[] idle11WalkFrames = idle11WalkFramesList.toArray(new TextureRegion[0]);



         this.character1WalkAnimation = new Animation<>(0.1f, idle1WalkFrames);
         character1WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character2WalkAnimation = new Animation<>(0.1f, idle2WalkFrames);
         character2WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character3WalkAnimation = new Animation<>(0.1f, idle3WalkFrames);
         character3WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character4WalkAnimation = new Animation<>(0.1f, idle4WalkFrames);
         character4WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character5WalkAnimation = new Animation<>(0.1f, idle5WalkFrames);
         character5WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character6WalkAnimation = new Animation<>(0.1f, idle6WalkFrames);
         character6WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character7WalkAnimation = new Animation<>(0.1f, idle7WalkFrames);
         character7WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character8WalkAnimation = new Animation<>(0.1f, idle8WalkFrames);
         character8WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character9WalkAnimation = new Animation<>(0.1f, idle9WalkFrames);
         character9WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character10WalkAnimation = new Animation<>(0.1f, idle10WalkFrames);
         character10WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

         this.character11WalkAnimation = new Animation<>(0.1f, idle11WalkFrames);
         character11WalkAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }

    private void createDamage() {

        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_0.png"));
        frames[1] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_1.png"));
        frames[2] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_2.png"));
        frames[3] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_3.png"));
        this.damageAnimation = new Animation<>(0.1f, frames);
    }

    private void createBasicTileTexture() {
        this.basicTileTexture = new Texture("Sprite/background/T_TempleTile_4.png");
    }

    private void createTreeTexture() {
        this.treeTexture = new Texture("Sprite/T/T_TreeMonster_0.png");
    }

    private void createBullet() {
        this.bulletTexture = new Texture("Sprite/Bullet/bullet.png");
    }

    private void createWeapons() {
        this.smgTexture = new Texture("Sprite/T/T_DualSMGs_Icon.png");
        this.shotgunTexture = new Texture("Sprite/T/T_Shotgun_SS_0.png");
        this.revolverTexture = new Texture("Sprite/RevolverStill/RevolverStill.png");
    }

    public Texture getBaseTileTexture() {
        return this.basicTileTexture;
    }

    public Texture getTreeTexture() {
        return this.treeTexture;
    }

    public Texture getSmgTexture() {
        return this.smgTexture;
    }

    public Texture getRevolverTexture() {
        return this.revolverTexture;
    }

    public Texture getShotgunTexture() {
        return this.shotgunTexture;
    }

    public Texture getBulletTexture() {
        return this.bulletTexture;
    }

    public Animation<TextureRegion> getCharacter1WalkAnimation() {
        return character1WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter2WalkAnimation() {
        return character2WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter3WalkAnimation() {
        return character3WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter4WalkAnimation() {
        return character4WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter5WalkAnimation() {
        return character5WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter6WalkAnimation() {
        return character6WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter7WalkAnimation() {
        return character7WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter8WalkAnimation() {
        return character8WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter9WalkAnimation() {
        return character9WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter10WalkAnimation() {
        return character10WalkAnimation;
    }

    public Animation<TextureRegion> getCharacter11WalkAnimation() {
        return character11WalkAnimation;
    }

    public Animation<TextureRegion> getDamageAnimation() {
        return damageAnimation;
    }
}
