package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.MainMenuController;
import tilldawn.Main;
import tilldawn.Model.User;

public class MainMenuView implements Screen {

    private Stage stage;
    private final TextButton settingButton;
    private final TextButton profileButton;
    private final TextButton preGameButton;
    private final TextButton scoreboardButton;
    private final TextButton hintMenuButton;
    private final TextButton resumeSavedGameButton;
    private final TextButton logoutButton;
    private final Label label;
    private final Label messageLabel;
    private final TextField username;
    private final TextField score;
    private final Table table;
    private final MainMenuController controller;


    public MainMenuView() {
        this.controller = new MainMenuController();
        this.label = new Label("Main menu",Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel = new Label("", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel.setVisible(false);

        User user = Main.getMain().getUserManager().getLoggedInUser();

        if (user != null) {
            this.username = new TextField( user.getUsername(),Main.getMain().getGameAssetManager().getSkin());
            this.score = new TextField("Score: " + user.getScore(),Main.getMain().getGameAssetManager().getSkin());
        } else {
            this.username = new TextField("Guest(not logged in)",Main.getMain().getGameAssetManager().getSkin());
            this.score = new TextField("Score: You should login",Main.getMain().getGameAssetManager().getSkin());
        }

        this.settingButton = new TextButton("Setting", Main.getMain().getGameAssetManager().getSkin());
        this.profileButton = new TextButton("Profile menu", Main.getMain().getGameAssetManager().getSkin());
        this.preGameButton = new TextButton("Pre-Game menu", Main.getMain().getGameAssetManager().getSkin());
        this.scoreboardButton = new TextButton("Scoreboard menu", Main.getMain().getGameAssetManager().getSkin());
        this.hintMenuButton = new TextButton("Hint menu", Main.getMain().getGameAssetManager().getSkin());
        this.resumeSavedGameButton = new TextButton("Resume saved game", Main.getMain().getGameAssetManager().getSkin());
        this.logoutButton = new TextButton("Logout", Main.getMain().getGameAssetManager().getSkin());
        this.table = new Table();
        controller.setView(this);

    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center().top();

        table.add(label).colspan(2).padTop(40).padBottom(60);
        table.row();

        Table infoTable = new Table();
        infoTable.add(username).width(400).left().expandX().padRight(10);
        infoTable.add(score).width(400).right().expandX().padLeft(10);
        table.add(infoTable).colspan(2).width(1000).padBottom(80);
        table.row();

        Table buttonsTable = new Table();
        buttonsTable.defaults().width(500).height(80).space(30);

        buttonsTable.add(settingButton).padRight(50);
        buttonsTable.add(profileButton).padLeft(50);
        buttonsTable.row().padTop(30);

        buttonsTable.add(preGameButton).padRight(50);
        buttonsTable.add(scoreboardButton).padLeft(50);
        buttonsTable.row().padTop(30);

        buttonsTable.add(resumeSavedGameButton).padRight(50);
        buttonsTable.add(hintMenuButton).padLeft(50);

        table.add(buttonsTable).colspan(2).padBottom(100);
        table.row();

        table.add(logoutButton).colspan(2).width(400).padTop(50);

        stage.addActor(table);

        stage.addActor(messageLabel);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenuButtons();
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

    public TextButton getSettingButton() {
        return settingButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPreGameButton() {
        return preGameButton;
    }

    public TextButton getScoreboardButton() {
        return scoreboardButton;
    }

    public TextButton getHintMenuButton() {
        return hintMenuButton;
    }

    public TextButton getResumeSavedGameButton() {
        return resumeSavedGameButton;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }

    public void showTemporaryMessage(String message , Runnable action) {
        table.setVisible(false);
        messageLabel.setText(message);
        messageLabel.setVisible(true);
        messageLabel.setPosition(((float) Gdx.graphics.getWidth() - message.length()*10 )/ 2 , (float) Gdx.graphics.getHeight() / 2);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                messageLabel.setText("");
                messageLabel.setVisible(false);
                table.setVisible(true);
                if (action != null) {
                    action.run();
                }
            }

        } , 3);
    }
}
