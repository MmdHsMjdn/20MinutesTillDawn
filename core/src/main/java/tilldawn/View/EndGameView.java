package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Main;
import tilldawn.Model.AudioManager;
import tilldawn.Model.Bgm;

public class EndGameView implements Screen {

    private Stage stage;
    private TextButton backButton;

    private final boolean gameOver;
    private final int minutes;
    private final int seconds;
    private final int numberOfKills;
    private final int score;

    public EndGameView(boolean gameOver, int minutes, int seconds, int numberOfKills, int score) {
        this.gameOver = gameOver;
        this.minutes = minutes;
        this.seconds = seconds;
        this.numberOfKills = numberOfKills;
        this.score = score;
        AudioManager.stopAllMusic();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Label.LabelStyle labelStyle = Main.getMain().getGameAssetManager().getSkin().get(Label.LabelStyle.class);
        labelStyle.font.getData().setScale(1.2f);

        Label titleLabel = new Label(gameOver ? "GAME OVER" : "VICTORY!", labelStyle);
        titleLabel.setFontScale(2f);

        String username;
        if (Main.getMain().getUserManager().getLoggedInUser() == null) {
            username = "Guest";
        } else {
            username = Main.getMain().getUserManager().getLoggedInUser().getUsername();
        }

        Label usernameLabel = new Label("Player: " + username, labelStyle);

        Label timeLabel = new Label(String.format("Time Survived: %02d:%02d", minutes, seconds), labelStyle);

        Label killsLabel = new Label("Kills: " + numberOfKills, labelStyle);

        Label scoreLabel = new Label("Score: " + score, labelStyle);

        backButton = new TextButton("Back to Main Menu", Main.getMain().getGameAssetManager().getSkin());
        backButton.getLabel().setFontScale(1.2f);

        table.add(titleLabel).padBottom(40).row();
        table.add(usernameLabel).padBottom(20).row();
        table.add(timeLabel).padBottom(20).row();
        table.add(killsLabel).padBottom(20).row();
        table.add(scoreLabel).padBottom(40).row();
        table.add(backButton).width(600).height(60).padTop(30);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (backButton.isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView());
            Bgm.playChampionsLeague();
            return;
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
