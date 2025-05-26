package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.GameController;
import tilldawn.Main;
import tilldawn.Model.Collidables.Bullet;
import tilldawn.Model.Collidables.Collidable;
import tilldawn.Model.Collidables.Enemy.Enemy;
import tilldawn.Model.Collidables.Tree;
import tilldawn.Model.Player;
import tilldawn.Model.World;

import java.util.ArrayList;

public class GameView implements Screen, InputProcessor {

    private Stage stage;
    private final GameController controller;
    private final Player player;
    private final World world;
    private final ArrayList<Collidable> collidables = new ArrayList<>();
    private final ArrayList<Tree> trees = new ArrayList<>();
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final int totalMinutes;
    private float passedTime = 0.0f;

    public GameView(int index, int totalMinutes) {
        this.controller = new GameController();
        this.player = new Player(index);
        this.world = new World();
        this.totalMinutes = totalMinutes;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
        controller.setView(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Main.getBatch().begin();
        controller.updateGame(delta);
        controller.draw(delta);
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }


    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public GameController getController() {
        return controller;
    }

    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Enemy> enemies() {
        return enemies;
    }

    public float getPassedTime() {
        return passedTime;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void addToPassedTime(float time) {
        passedTime += time;
    }
}
