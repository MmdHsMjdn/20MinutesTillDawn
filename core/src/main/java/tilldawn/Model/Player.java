package tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Collidables.Collidable;
import tilldawn.Model.Collidables.Enemy.Enemy;
import tilldawn.Model.Collidables.Enemy.Eyebat;
import tilldawn.Model.Collidables.Enemy.TentacleMonster;
import tilldawn.Model.Weapons.Weapon;

import java.util.Iterator;
import java.util.Random;

public class Player {

    private final int characterIndex;
    private final String username;
    private Texture playerTexture;
    private Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> damageAnimation;
    private TextureRegion currentFrame;
    private Sprite playerSprite;
    private Vector2 position = new Vector2();
    private float angle;
    private float health;
    private float maxHealth;
    private final Rectangle collisionRect;
    private float soundStepTimer = 0.0f;
    private float speed;
    private float stateTimeForPlayerMove = 0;
    private float stateTimeForDamage = 0.0f;
    private boolean collisionWithCollidable = false;
    private Vector2 damagePosition = new Vector2();
    private final float damageCooldown = 1f;
    private float timeSinceLastDamage = damageCooldown;
    private Weapon defaultWeapon;
    private float lastManualMouseMoveTime = 0;
    private static final float autoAimCoolDown = 0.5f;
    private int xp = 0;
    private int killsNumber = 0;
    private Levels level = Levels.One;
    private boolean vitalityGained = false;
    private boolean damagerGained = false;
    private boolean proCreaseGained = false;
    private boolean amoCreaseGained = false;
    private boolean speedyGained = false;
    private float damagerTimer = 0.0f;
    private float speedyTimer = 0.0f;

    public Player(String username, int characterIndex, Weapon defaultWeapon) {
        this.username = username;
        this.characterIndex = characterIndex;
        this.setCharacter();
        this.position.set(160000, 160000);
        this.collisionRect = new Rectangle(
            position.x - playerSprite.getWidth() * playerSprite.getScaleX() / 2f,
            position.y - playerSprite.getHeight() * playerSprite.getScaleY() / 2f,
            playerSprite.getWidth() * playerSprite.getScaleX(),
            playerTexture.getHeight() * playerSprite.getScaleY()
        );
        this.damageAnimation = Main.getMain().getGameAssetManager().getDamageAnimation();
        this.defaultWeapon = defaultWeapon;
        this.health = maxHealth;
    }

    private void setCharacter() {
        switch (characterIndex) {
            case 1:
                playerTexture = new Texture("Sprite/Idle/char1/Idle_01.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter1WalkAnimation();
                speed = 240;
                maxHealth = 400;
                break;
            case 2:
                playerTexture = new Texture("Sprite/Idle/char2/Idle_02.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter2WalkAnimation();
                speed = 60;
                maxHealth = 700;
                break;
            case 3:
                playerTexture = new Texture("Sprite/Idle/char3/Idle_03.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter3WalkAnimation();
                speed = 300;
                maxHealth = 300;
                break;
            case 4:
                playerTexture = new Texture("Sprite/Idle/char4/Idle_04.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter4WalkAnimation();
                speed = 180;
                maxHealth = 500;
                break;
            case 5:
                playerTexture = new Texture("Sprite/Idle/char5/Idle_05.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter5WalkAnimation();
                speed = 600;
                maxHealth = 200;
                break;
        }

        playerSprite.setOriginCenter();
        playerSprite.setScale(3f);
    }

    public void update(float delta, Vector2 moveInput, Vector3 mouseWorldPosition) {
        timeSinceLastDamage += delta;
        updateMovement(delta, moveInput);
        updateRotation(mouseWorldPosition);
        updateAnimation(delta, moveInput);
        updateSprite();
        updateCollisionRect();
        if (this.damagerGained) {
            damagerTimer += delta;
        }
        if (speedyGained) {
            speedyTimer += delta;
        }
    }

    private void updateMovement(float delta, Vector2 moveInput) {
        soundStepTimer += delta;
        int ratio = (speedyGained && speedyTimer < 10f) ? 2 : 1;

        if (!moveInput.isZero()) {
            moveInput.nor().scl(speed * delta * ratio);
            Vector2 newPosition = new Vector2(position).add(moveInput);

            boolean collided = false;
            for (Collidable c : Main.getCurrentGameView().getCollidables()) {
                Rectangle testRect = new Rectangle(
                    newPosition.x - collisionRect.width / 2,
                    newPosition.y - collisionRect.height / 2,
                    collisionRect.width,
                    collisionRect.height
                );

                if (testRect.overlaps(c.getCollisionRect())) {
                    if (c instanceof Bullet) {
                        if (!((Bullet) c).isShotByEnemy()) {
                            continue;
                        }
                    }

                    if (c instanceof Eyebat || c instanceof TentacleMonster) {
                        continue;
                    }

                    c.onPlayerCollision(this);
                    collided = true;
                    break;
                }
            }

            if (!collided) {
                position.set(newPosition);
            }

            if (soundStepTimer >= 0.5f) {
                Sfx.playWalk3(0.5f);
                soundStepTimer = 0.0f;
            }
        }

        Iterator<Drop> iterator = Main.getCurrentGameView().getDrops().iterator();

        while (iterator.hasNext()) {

            Drop drop = iterator.next();
            if (drop.getCollisionRect().overlaps(this.getCollisionRect())) {
                iterator.remove();
                this.increaseXp(3);
                Sfx.obtainDrop(1);
            }
        }

        position.x = MathUtils.clamp(position.x,
            collisionRect.width / 2 + 10,
            319990 - collisionRect.width / 2);
        position.y = MathUtils.clamp(position.y,
            collisionRect.height / 2 + 10,
            319990 - collisionRect.height / 2);
    }

    private void updateRotation(Vector3 mouseWorldPosition) {
        if (Gdx.input.getDeltaX() != 0 || Gdx.input.getDeltaY() != 0) {
            lastManualMouseMoveTime = 0;
        } else {
            lastManualMouseMoveTime += Gdx.graphics.getDeltaTime();
        }

        if (lastManualMouseMoveTime < autoAimCoolDown) {
            angle = new Vector2(mouseWorldPosition.x, mouseWorldPosition.y).sub(position).angleDeg();
            return;
        }

        if (Main.getCurrentGameView().isAutoAim()) {
            Enemy nearestEnemy = null;
            float minDistance = Float.MAX_VALUE;

            for (Enemy enemy : Main.getCurrentGameView().enemies()) {
                Vector2 enemyCenter = enemy.getCollisionRect().getCenter(new Vector2());
                float distance = enemyCenter.dst(position);

                if (distance < minDistance) {
                    minDistance = distance;
                    nearestEnemy = enemy;
                }
            }

            if (nearestEnemy != null) {
                Vector2 enemyCenter = nearestEnemy.getCollisionRect().getCenter(new Vector2());
                OrthographicCamera cam = Main.getCurrentGameView().getController().getCamera();

                float camLeft = cam.position.x - cam.viewportWidth / 2 + 50;
                float camRight = cam.position.x + cam.viewportWidth / 2 - 50;
                float camBottom = cam.position.y - cam.viewportHeight / 2 + 50;
                float camTop = cam.position.y + cam.viewportHeight / 2 - 50;

                boolean inView = enemyCenter.x >= camLeft && enemyCenter.x <= camRight &&
                    enemyCenter.y >= camBottom && enemyCenter.y <= camTop;

                if (inView) {
                    Vector2 offsetFromPlayer = enemyCenter.cpy().sub(position);
                    int screenCenterX = Gdx.graphics.getWidth() / 2;
                    int screenCenterY = Gdx.graphics.getHeight() / 2;

                    Gdx.input.setCursorPosition(
                        screenCenterX + (int) offsetFromPlayer.x,
                        screenCenterY - (int) offsetFromPlayer.y
                    );

                    angle = offsetFromPlayer.angleDeg();
                    return;
                }
            }
        }

        angle = new Vector2(mouseWorldPosition.x, mouseWorldPosition.y).sub(position).angleDeg();
    }

    private void updateAnimation(float delta, Vector2 moveInput) {
        stateTimeForPlayerMove += delta;
        currentFrame = moveInput.isZero() ? walkAnimation.getKeyFrame(0) :
            walkAnimation.getKeyFrame(stateTimeForPlayerMove, true);
    }

    private void updateSprite() {
        playerSprite.setRegion(currentFrame);
        playerSprite.setRotation(angle);
        playerSprite.setPosition(
            position.x - playerSprite.getWidth() / 2,
            position.y - playerSprite.getHeight() / 2
        );
    }

    private void updateCollisionRect() {
        collisionRect.setPosition(
            position.x - collisionRect.width / 2,
            position.y - collisionRect.height / 2
        );
    }

    public void draw(SpriteBatch batch, float delta) {
        if (collisionWithCollidable) {
            stateTimeForDamage += delta;
            TextureRegion damage = damageAnimation.getKeyFrame(stateTimeForDamage, false);
            batch.draw(damage,
                damagePosition.x - damage.getRegionWidth() / 2f,
                damagePosition.y - damage.getRegionHeight() / 2f);

            if (damageAnimation.isAnimationFinished(stateTimeForDamage)) {
                collisionWithCollidable = false;
            }
        }
        playerSprite.draw(batch);
    }

    public void triggerDamageAnimation(Rectangle rect1, Rectangle rect2) {
        Vector2 center1 = new Vector2(
            rect1.x + rect1.getWidth() / 2,
            rect1.y + rect1.getHeight() / 2
        );
        Vector2 center2 = new Vector2(
            rect2.x + rect2.getWidth() / 2,
            rect2.y + rect2.getHeight() / 2
        );
        this.damagePosition = center1.add(center2).scl(0.5f);
        stateTimeForDamage = 0f;
        collisionWithCollidable = true;
    }

    public void applyDamage(float damage) {
        if (timeSinceLastDamage >= damageCooldown) {
            health = Math.max(0, health - damage);
            Sfx.playDamage(1);
            timeSinceLastDamage = 0.0f;
        }
    }

    public void shoot() {
        if (this.damagerGained && damagerTimer < 10f) {
            defaultWeapon.shoot(position.cpy(), playerSprite.getRotation(), false, 1.25f);
        } else {
            defaultWeapon.shoot(position.cpy(), playerSprite.getRotation(), false, 1f);
        }

    }

    public void reload() {
        defaultWeapon.startReloading();
    }

    public Weapon getDefaultWeapon() {
        return defaultWeapon;
    }

    public void setDefaultWeapon(Weapon defaultWeapon) {
        this.defaultWeapon = defaultWeapon;
    }

    public void setAutoReload() {
        defaultWeapon.setAutoReload(!defaultWeapon.isAutoReload());
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getHealth() {
        return health;
    }


    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setHealth(float health) {
        this.health = Math.min(maxHealth, health);
    }


    public boolean increaseXp(int value) {

        Levels current = this.level;

        this.xp += value;
        this.xp = Math.min(300, this.xp);

        if (xp < 20) {
            this.level = Levels.One;
        } else if (xp < 60) {
            this.level = Levels.Two;
        } else if (xp < 120) {
            this.level = Levels.Three;
        } else if (xp < 200) {
            this.level = Levels.Four;
        } else if (xp < 300) {
            this.level = Levels.Five;
        } else {
            this.level = Levels.Six;
        }

        if (current != this.level) {
            gainAbility();
        }

        return !this.level.equals(Levels.Six);
    }

    private void gainAbility() {

        Random random = new Random();

        switch (random.nextInt(5)) {
            case 0: {
                if (activeVitality()) {
                    return;
                }
                if (activeDamager()) {
                    return;
                }
                if (activeProCrease()) {
                    return;
                }
                if (activeAmoCrease()) {
                    return;
                }
                if (activeSpeedy()) {
                    return;
                }
            }
            break;

            case 1: {
                if (activeSpeedy()) {
                    return;
                }
                if (activeVitality()) {
                    return;
                }
                if (activeDamager()) {
                    return;
                }
                if (activeProCrease()) {
                    return;
                }
                if (activeAmoCrease()) {
                    return;
                }
            }
            break;

            case 2: {
                if (activeAmoCrease()) {
                    return;
                }
                if (activeSpeedy()) {
                    return;
                }
                if (activeVitality()) {
                    return;
                }
                if (activeDamager()) {
                    return;
                }
                if (activeProCrease()) {
                    return;
                }
            }
            break;
            case 3: {
                if (activeProCrease()) {
                    return;
                }
                if (activeAmoCrease()) {
                    return;
                }
                if (activeSpeedy()) {
                    return;
                }
                if (activeVitality()) {
                    return;
                }
                if (activeDamager()) {
                    return;
                }
            }
            break;

            case 4: {
                if (activeDamager()) {
                    return;
                }
                if (activeProCrease()) {
                    return;
                }
                if (activeAmoCrease()) {
                    return;
                }
                if (activeSpeedy()) {
                    return;
                }
                if (activeVitality()) {
                    return;
                }
            }
            break;
        }
    }

    public int getKillsNumber() {
        return killsNumber;
    }

    public Levels getLevel() {
        return level;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void increaseKillsNumber() {
        this.killsNumber += 1;
    }

    public int neededXpForNextLevel() {
        int level = this.level.ordinal() + 1;

        if (level == 6) {
            return 0;
        }
        return 10 * level * (level + 1) - xp;
    }

    public int nextLevelXp() {
        int level = this.level.ordinal() + 1;

        return 10 * level * (level + 1) - 10 * level * (level - 1);
    }

    public String getUsername() {
        return username;
    }

    private boolean activeVitality() {
        if (this.vitalityGained) {
            return false;
        }

        this.vitalityGained = true;
        this.maxHealth *= 1.2f;
        this.health += maxHealth / 6;
        Main.getCurrentGameView().getController().setTemporaryMessage("You gained VITALITY", 2);
        return true;
    }

    private boolean activeDamager() {
        if (this.damagerGained) {
            return false;
        }

        this.damagerGained = true;
        Main.getCurrentGameView().getController().setTemporaryMessage("You gained DAMAGER", 2);
        return true;

    }

    private boolean activeProCrease() {
        if (this.proCreaseGained) {
            return false;
        }

        this.proCreaseGained = true;
        this.defaultWeapon.increaseProjectTile();
        Main.getCurrentGameView().getController().setTemporaryMessage("You gained PROCREASE", 2);
        return true;
    }

    private boolean activeAmoCrease() {
        if (this.amoCreaseGained) {
            return false;
        }

        this.amoCreaseGained = true;
        this.defaultWeapon.increaseMaxAmmo();
        Main.getCurrentGameView().getController().setTemporaryMessage("You gained AMOCREASED", 2);
        return true;
    }

    private boolean activeSpeedy() {
        if (this.speedyGained) {
            return false;
        }

        this.speedyGained = true;
        Main.getCurrentGameView().getController().setTemporaryMessage("You gained SPEEDY", 2);
        return true;
    }

    public boolean isProCreaseGained() {
        return proCreaseGained;
    }

    public boolean isAmoCreaseGained() {
        return amoCreaseGained;
    }

    public boolean isVitalityGained() {
        return vitalityGained;
    }

    public boolean isDamagerGained() {
        return damagerGained;
    }

    public boolean isSpeedyGained() {
        return speedyGained;
    }
}
