package tilldawn.Model;

import com.badlogic.gdx.Gdx;
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

public class Player {

    private final int characterIndex;
    private Texture playerTexture;
    private Animation<TextureRegion> walkAnimation;
    private TextureRegion currentFrame;
    private Sprite playerSprite;
    private Vector2 position = new Vector2();
    private float angle;
    private float health = 100;
    private static final float maxHealth = 100;
    private final Rectangle collisionRect;
    private float speed = 300;
    private float stateTime = 0;
    private boolean isMoving = false;

    public Player(int characterIndex) {

        this.characterIndex = characterIndex;
        this.setTextureAndSprite();
        this.position.set(1600,1600);
        this.collisionRect = new Rectangle(
            position.x,
            position.y,
            playerSprite.getWidth() * playerSprite.getScaleX(),
            playerTexture.getHeight() * playerSprite.getScaleY()
        );
    }

    private void setTextureAndSprite() {

        switch (characterIndex) {
            case 1: {

                playerTexture = new Texture("Sprite/Idle/char1/Idle_01.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter1WalkAnimation();
            }
            break;

            case 2: {
                playerTexture = new Texture("Sprite/Idle/char2/Idle_02.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter2WalkAnimation();
            }
            break;

            case 3: {
                playerTexture = new Texture("Sprite/Idle/char3/Idle_03.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter3WalkAnimation();
            }
            break;

            case 4: {
                playerTexture = new Texture("Sprite/Idle/char4/Idle_04.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter4WalkAnimation();
            }
            break;

            case 5: {
                playerTexture = new Texture("Sprite/Idle/char5/Idle_05.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter5WalkAnimation();
            }
            break;

            case 6: {
                playerTexture = new Texture("Sprite/Idle/char6/Idle_06.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter6WalkAnimation();
            }
            break;

            case 7: {
                playerTexture = new Texture("Sprite/Idle/char7/Idle_07.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter7WalkAnimation();
            }
            break;

            case 8: {
                playerTexture = new Texture("Sprite/Idle/char8/Idle_08.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter8WalkAnimation();
            }
            break;

            case 9: {
                playerTexture = new Texture("Sprite/Idle/char9/Idle_09.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter9WalkAnimation();
            }
            break;

            case 10: {
                playerTexture = new Texture("Sprite/Idle/char10/Idle_010.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter10WalkAnimation();
            }
            break;

            case 11: {
                playerTexture = new Texture("Sprite/Idle/char11/Idle_011.png");
                playerSprite = new Sprite(playerTexture);
                walkAnimation = Main.getMain().getGameAssetManager().getCharacter11WalkAnimation();
            }
            break;
        }

        playerSprite.setOriginCenter();
        playerSprite.setScale(3f);

    }

    public int getCharacterIndex() {
        return characterIndex;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
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

    public float getSpeed() {
        return speed;
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void update(float delta, Vector3 mouseWorldPosition, Vector2 moveInput) {
        updateMovement(delta, moveInput);
        updateRotation(mouseWorldPosition);
        updateAnimation(delta, moveInput);
        updateSprite();
        updateCollisionRect();
    }

    private void updateMovement(float delta, Vector2 moveInput) {
        if (!moveInput.isZero()) {
            moveInput.nor().scl(speed * delta);
            position.add(moveInput);
        }
        position.x = MathUtils.clamp(position.x, playerSprite.getWidth() +5,3195 - playerSprite.getWidth());
        position.y = MathUtils.clamp(position.y, playerSprite.getHeight() +5,3195 - playerSprite.getHeight());
    }

    private void updateRotation(Vector3 mouseWorldPosition) {
        angle = new Vector2(mouseWorldPosition.x, mouseWorldPosition.y).sub(position).angleDeg();
    }

    private void updateAnimation(float delta, Vector2 moveInput) {
        stateTime += delta;
        isMoving = !moveInput.isZero();
        currentFrame = isMoving ? walkAnimation.getKeyFrame(stateTime, true) : walkAnimation.getKeyFrame(0);
    }

    private void updateSprite() {
        playerSprite.setRegion(currentFrame);
        playerSprite.setRotation(angle);
        playerSprite.setPosition(position.x - playerSprite.getWidth() / 2, position.y - playerSprite.getHeight() / 2);
    }

    private void updateCollisionRect() {
        collisionRect.setPosition(playerSprite.getX(), playerSprite.getY());
    }

    public void draw(SpriteBatch batch) {
        playerSprite.draw(batch);
    }
}


