package tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import tilldawn.Main;
import tilldawn.Model.DefaultKeys.DefaultsKeys;
import tilldawn.Model.DefaultKeys.InputManager;
import tilldawn.View.EndGameView;
import tilldawn.View.GameView;

public class GameController {

    private GameView view;
    private final OrthographicCamera camera;
    private final OrthographicCamera hudCamera;
    private static final float viewWidth = Gdx.graphics.getWidth();
    private static final float viewHeight = Gdx.graphics.getHeight();
    private WorldController worldController;
    private PlayerController playerController;
    private TreeController treeController;
    private BulletController bulletController;
    private WeaponController weaponController;
    private EnemyController enemyController;
    private final ShapeRenderer shapeRenderer;
    private final BitmapFont font = new BitmapFont();
    private String message = null;
    private float messageTimer = 0f;

    public GameController() {
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, viewWidth, viewHeight);
        this.hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, viewWidth, viewHeight);
        this.shapeRenderer = new ShapeRenderer();
    }

    public void setTemporaryMessage(String message, float duration) {
        this.message = message;
        this.messageTimer = duration;
    }

    public void setView(GameView view) {
        this.view = view;
        setWorldController();
        setPlayerController();
        setTreeController();
        setBulletController();
        setWeaponController();
        setEnemyController();
    }

    private void setBulletController() {
        this.bulletController = new BulletController();
    }

    private void setEnemyController() {
        this.enemyController = new EnemyController();
    }

    private void setWorldController() {
        this.worldController = new WorldController(view.getWorld(), camera);
    }

    private void setPlayerController() {
        this.playerController = new PlayerController(view.getPlayer());
    }

    private void setTreeController() {
        this.treeController = new TreeController(view.getTrees());
        this.treeController.generateInitialTrees();
    }

    private void setWeaponController() {
        this.weaponController = new WeaponController();
    }

    public void updateGame(float delta) {
        checkFinishing();
        view.addToPassedTime(delta);

        if (messageTimer > 0) {
            messageTimer -= delta;
            if (messageTimer <= 0) {
                message = null;
            }
        }

        Vector3 mouseWorldPosition = mouseWorldPosition();
        Vector2 playerMovement = playerMovement();

        bulletController.update(delta);
        weaponController.updateWeapon(view.getPlayer(), delta);
        weaponController.autoAimUpdate();
        playerController.update(delta, playerMovement, mouseWorldPosition);
        worldController.update(view.getPlayer().getPosition(), mouseWorldPosition);
        treeController.updateTrees(camera);
        enemyController.update(delta);
    }

    public void draw(float delta) {
        worldController.draw();
        treeController.draw(camera);
        playerController.draw(delta);
        weaponController.draw(view.getPlayer());
        bulletController.draw();
        enemyController.draw(delta);
    }

    public void drawUHd() {
        Main.getBatch().setProjectionMatrix(hudCamera.combined);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);

        font.getData().setScale(1.5f);

        float barHeight = 80f;
        Color barColor = new Color(0.1f, 0.1f, 0.1f, 0.7f);
        Color textColor = new Color(1f, 1f, 1f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(barColor);
        shapeRenderer.rect(0, Gdx.graphics.getHeight() - barHeight, Gdx.graphics.getWidth(), barHeight);
        shapeRenderer.end();

        float top = Gdx.graphics.getHeight() - 30f;
        float leftPadding = 30f;
        float rightPadding = Gdx.graphics.getWidth() - 30f;

        Main.getBatch().begin();
        font.setColor(textColor);

        if (message != null) {
            float messageWidth = message.length() * 20;
            float messageX = (Gdx.graphics.getWidth() - messageWidth) / 2;
            font.draw(Main.getBatch(), message, messageX, top);
        } else {
            int maxHearts = 5;
            float initialHealth = view.getPlayer().getMaxHealth();
            float currentHealth = view.getPlayer().getHealth();
            int heartsToDraw = Math.round((currentHealth / initialHealth) * maxHearts);

            Texture heartTexture = Main.getMain().getGameAssetManager().getHeartTexture();
            float heartY = top - heartTexture.getHeight()/2f - 10;
            for (int i = 0; i < heartsToDraw; i++) {
                Main.getBatch().draw(heartTexture,
                    (float) Gdx.graphics.getWidth() /2 + (i-5)*20 + i * (heartTexture.getWidth() + 5),
                    heartY);
            }

            int min = Main.getCurrentGameView().getTotalMinutes() - (int)Math.ceil(Main.getCurrentGameView().getPassedTime() / 60);
            int seconds = 60 - (int)(Main.getCurrentGameView().getPassedTime()) % 60;
            String timeText = String.format("%02d:%02d", min, seconds);
            font.draw(Main.getBatch(), "Time: " + timeText, leftPadding, top);

            font.draw(Main.getBatch(), "Kills: " + view.getPlayer().getKillsNumber(), leftPadding + 200, top);

            font.draw(Main.getBatch(), "Level: " + view.getPlayer().getLevel().toString(), leftPadding + 400, top);

            font.draw(Main.getBatch(), "Ammo: " + view.getPlayer().getDefaultWeapon().getCurrentAmmo(), leftPadding + 600, top);

            float xpBarWidth = 300f;
            float xpBarX = rightPadding - xpBarWidth;
            font.draw(Main.getBatch(), "XP Progress", xpBarX, top + 20);
        }

        Main.getBatch().end();

        if (message == null) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            float xpBarHeight = 20f;
            float xpBarY = top - xpBarHeight - 15f;
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(rightPadding - 300f, xpBarY, 300f, xpBarHeight);

            float progress = 1f - ((float) view.getPlayer().neededXpForNextLevel() / (float) view.getPlayer().nextLevelXp());
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(rightPadding - 300f, xpBarY, 300f * progress, xpBarHeight);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(rightPadding - 300f, xpBarY, 300f, xpBarHeight);
            shapeRenderer.end();
        }

        font.getData().setScale(1.0f);
    }

    private Vector2 playerMovement() {
        Vector2 moveInput = new Vector2();

        if (InputManager.isKeyPressed(DefaultsKeys.MoveUp)) {
            moveInput.y += 1;
        }
        if (InputManager.isKeyPressed(DefaultsKeys.MoveDown)) {
            moveInput.y -= 1;
        }
        if (InputManager.isKeyPressed(DefaultsKeys.MoveRight)) {
            moveInput.x += 1;
        }
        if (InputManager.isKeyPressed(DefaultsKeys.MoveLeft)) {
            moveInput.x -= 1;
        }

        return moveInput;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    private Vector3 mouseWorldPosition() {
        Vector3 mouseWorldPosition = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouseWorldPosition);
        return mouseWorldPosition;
    }

    private void checkFinishing() {

        if (view.getPlayer().getHealth() <= 0) {
            finishGame(true);
            return;
        }

        if (view.getPassedTime() >= view.getTotalMinutes() * 60) {
            finishGame(view.getPlayer().getHealth() <= 0);
        }

    }

    private void finishGame(boolean gameOver) {

        int minutes = (int) (view.getPassedTime()/60);
        int seconds = (int) (view.getPassedTime() % 60);

        if (view.getPassedTime() >= view.getTotalMinutes() * 60) {
            minutes = view.getTotalMinutes();
            seconds = 0;
        }

        int score = (int) (view.getPassedTime() * view.getPlayer().getKillsNumber());

        if (Main.getMain().getUserManager().getLoggedInUser() != null) {
            Main.getMain().getUserManager().getLoggedInUser().addToScore(score);
            Main.getMain().getUserManager().getLoggedInUser().addToNumberOfKills(view.getPlayer().getKillsNumber());
            Main.getMain().getUserManager().getLoggedInUser().addMaxSurviveTime((int)view.getPassedTime());
            Main.getMain().getUserManager().saveUsers();
        }
        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor/tap.png"));
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();
        
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new EndGameView(gameOver,minutes,seconds, view.getPlayer().getKillsNumber(),score));
    }
}
