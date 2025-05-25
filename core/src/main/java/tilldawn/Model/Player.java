package tilldawn.Model;

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
import tilldawn.Model.Collidables.Collidable;

public class Player {
    private final int characterIndex;
    private Texture playerTexture;
    private Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> damageAnimation;
    private TextureRegion currentFrame;
    private Sprite playerSprite;
    private Vector2 position = new Vector2();
    private float angle;
    private float health = 100;
    private static final float MAX_HEALTH = 100;
    private final Rectangle collisionRect;
    private float soundStepTimer = 0.0f;
    private float speed = 300;
    private float stateTimeForPlayerMove = 0;
    private boolean isMoving = false;
    private float stateTimeForDamage = 0.0f;
    private boolean collisionWithCollidable = false;
    private Vector2 damagePosition = new Vector2();
    private final float damageCooldown = 0.5f;
    private float timeSinceLastDamage = damageCooldown;

    public Player(int characterIndex) {
        this.characterIndex = characterIndex;
        this.setTextureAndSprite();
        this.position.set(160000, 160000);
        this.collisionRect = new Rectangle(
            position.x - playerSprite.getWidth() * playerSprite.getScaleX() / 2f,
            position.y - playerSprite.getHeight() * playerSprite.getScaleY() / 2f,
            playerSprite.getWidth() * playerSprite.getScaleX(),
            playerTexture.getHeight() * playerSprite.getScaleY()
        );
        this.damageAnimation = Main.getMain().getGameAssetManager().getDamageAnimation();
    }

    private void setTextureAndSprite() {
        switch (characterIndex) {
            case 1:
                playerTexture = new Texture("Sprite/Idle/char1/Idle_01.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter1WalkAnimation();
                break;
            case 2:
                playerTexture = new Texture("Sprite/Idle/char2/Idle_02.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter2WalkAnimation();
                break;
            case 3:
                playerTexture = new Texture("Sprite/Idle/char3/Idle_03.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter3WalkAnimation();
                break;
            case 4:
                playerTexture = new Texture("Sprite/Idle/char4/Idle_04.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter4WalkAnimation();
                break;
            case 5:
                playerTexture = new Texture("Sprite/Idle/char5/Idle_05.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter5WalkAnimation();
                break;
            case 6:
                playerTexture = new Texture("Sprite/Idle/char6/Idle_06.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter6WalkAnimation();
                break;
            case 7:
                playerTexture = new Texture("Sprite/Idle/char7/Idle_07.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter7WalkAnimation();
                break;
            case 8:
                playerTexture = new Texture("Sprite/Idle/char8/Idle_08.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter8WalkAnimation();
                break;
            case 9:
                playerTexture = new Texture("Sprite/Idle/char9/Idle_09.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter9WalkAnimation();
                break;
            case 10:
                playerTexture = new Texture("Sprite/Idle/char10/Idle_010.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter10WalkAnimation();
                break;
            case 11:
                playerTexture = new Texture("Sprite/Idle/char11/Idle_011.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter11WalkAnimation();
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
    }

    private void updateMovement(float delta, Vector2 moveInput) {
        soundStepTimer += delta;

        if (!moveInput.isZero()) {
            moveInput.nor().scl(speed * delta);
            Vector2 newPosition = new Vector2(position).add(moveInput);

            boolean collided = false;
            for (Collidable c : Main.getCurrentGameView().getCollidables()) {
                Rectangle testRect = new Rectangle(
                    newPosition.x - collisionRect.width/2,
                    newPosition.y - collisionRect.height/2,
                    collisionRect.width,
                    collisionRect.height
                );
                if (testRect.overlaps(c.getCollisionRect())) {
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

        position.x = MathUtils.clamp(position.x,
            collisionRect.width/2 + 10,
            319990 - collisionRect.width/2);
        position.y = MathUtils.clamp(position.y,
            collisionRect.height/2 + 10,
            319990 - collisionRect.height/2);
    }

    private void updateRotation(Vector3 mouseWorldPosition) {
        angle = new Vector2(mouseWorldPosition.x, mouseWorldPosition.y).sub(position).angleDeg();
    }

    private void updateAnimation(float delta, Vector2 moveInput) {
        stateTimeForPlayerMove += delta;
        isMoving = !moveInput.isZero();
        currentFrame = isMoving ? walkAnimation.getKeyFrame(stateTimeForPlayerMove, true) :
            walkAnimation.getKeyFrame(0);
    }

    private void updateSprite() {
        playerSprite.setRegion(currentFrame);
        playerSprite.setRotation(angle);
        playerSprite.setPosition(
            position.x - playerSprite.getWidth()/2,
            position.y - playerSprite.getHeight()/2
        );
    }

    private void updateCollisionRect() {
        collisionRect.setPosition(
            position.x - collisionRect.width/2,
            position.y - collisionRect.height/2
        );
    }

    public void draw(SpriteBatch batch, float delta) {
        if (collisionWithCollidable) {
            stateTimeForDamage += delta;
            TextureRegion damage = damageAnimation.getKeyFrame(stateTimeForDamage, false);
            batch.draw(damage,
                damagePosition.x - damage.getRegionWidth()/2f,
                damagePosition.y - damage.getRegionHeight()/2f);

            if (damageAnimation.isAnimationFinished(stateTimeForDamage)) {
                collisionWithCollidable = false;
            }
        }
        playerSprite.draw(batch);
    }

    public void triggerDamageAnimation(Rectangle rect1, Rectangle rect2) {
        Vector2 center1 = new Vector2(
            rect1.x + rect1.getWidth()/2,
            rect1.y + rect1.getHeight()/2
        );
        Vector2 center2 = new Vector2(
            rect2.x + rect2.getWidth()/2,
            rect2.y + rect2.getHeight()/2
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

    public int getCharacterIndex() { return characterIndex; }
    public Texture getPlayerTexture() { return playerTexture; }
    public Sprite getPlayerSprite() { return playerSprite; }
    public Vector2 getPosition() { return position; }
    public float getHealth() { return health; }
    public Rectangle getCollisionRect() { return collisionRect; }
    public float getSpeed() { return speed; }
    public float getStateTimeForPlayerMove() { return stateTimeForPlayerMove; }
    public boolean isMoving() { return isMoving; }
    public void setPosition(Vector2 position) { this.position = position; }
    public void setHealth(float health) { this.health = Math.min(MAX_HEALTH, health); }
    public void setSpeed(float speed) { this.speed = speed; }
    public void setStateTimeForPlayerMove(float stateTime) { this.stateTimeForPlayerMove = stateTime; }
}
