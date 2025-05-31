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
    private Animation<TextureRegion> damageAnimation;
    private Animation<TextureRegion> deathAnimation;
    private Animation<TextureRegion> eyebatMoveAnimation;
    private Animation<TextureRegion> tentacleMoveAnimation;
    private Texture basicTileTexture;
    private Texture treeTexture;
    private Texture smgTexture;
    private Texture revolverTexture;
    private Texture shotgunTexture;
    private Texture bulletTexture;
    private Texture dropTexture;
    private Texture heartTexture;
    private Skin skin;

    public GameAssetManager() {
        setSkin();
        createWalkAnimations();
        createBasicTileTexture();
        createTreeTexture();
        createDamage();
        createWeapons();
        createBullet();
        createDrop();
        createDeath();
        createEyebatMove();
        createTentacleMove();
        createHeart();
    }

    private void setSkin() {
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    }

    public Skin getSkin() {
        return skin;
    }


    private void createHeart() {
        this.heartTexture = new Texture("Sprite/HeartAnimation/HeartAnimation_0.png");
    }

    private void createDrop() {
        this.dropTexture = new Texture("Sprite/enemyDrop/drop.png");
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

    }

    private void createEyebatMove() {

        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(new Texture("Sprite/T/T_EyeBat_0.png"));
        frames[1] = new TextureRegion(new Texture("Sprite/T/T_EyeBat_1.png"));
        frames[2] = new TextureRegion(new Texture("Sprite/T/T_EyeBat_2.png"));
        frames[3] = new TextureRegion(new Texture("Sprite/T/T_EyeBat_3.png"));
        this.eyebatMoveAnimation = new Animation<>(0.1f, frames);
        eyebatMoveAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }

    private void createTentacleMove() {

        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(new Texture("Sprite/BrainMonster/BrainMonster_0.png"));
        frames[1] = new TextureRegion(new Texture("Sprite/BrainMonster/BrainMonster_1.png"));
        frames[2] = new TextureRegion(new Texture("Sprite/BrainMonster/BrainMonster_2.png"));
        frames[3] = new TextureRegion(new Texture("Sprite/BrainMonster/BrainMonster_3.png"));
        this.tentacleMoveAnimation = new Animation<>(0.1f, frames);
        tentacleMoveAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }

    private void createDeath() {

        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_0.png"));
        frames[1] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_1.png"));
        frames[2] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_2.png"));
        frames[3] = new TextureRegion(new Texture("Sprite/DeathFX/DeathFX_3.png"));
        this.deathAnimation = new Animation<>(0.1f, frames);

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

    public Animation<TextureRegion> getDamageAnimation() {
        return damageAnimation;
    }

    public Animation<TextureRegion> getDeathAnimation() {return deathAnimation;}

    public Animation<TextureRegion> getEyebatMoveAnimation() {return eyebatMoveAnimation;}

    public Animation<TextureRegion> getTentacleMoveAnimation() {
        return tentacleMoveAnimation;
    }

    public Texture getDropTexture() {
        return dropTexture;
    }

    public Texture getHeartTexture(){return heartTexture;}
}
