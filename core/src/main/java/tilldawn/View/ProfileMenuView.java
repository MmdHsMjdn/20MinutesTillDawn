package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.ProfileMenuController;
import tilldawn.Main;

public class ProfileMenuView implements Screen {

    private Stage stage;
    private final TextButton changeUsername;
    private final TextButton changePassword;
    private final TextButton changeAvatar;
    private final TextButton deleteAccount;
    private final TextButton exit;
    private final Label label;
    private final Label messageLabel;
    private final Table table;
    private final ProfileMenuController controller;


    public ProfileMenuView() {
        this.controller = new ProfileMenuController();
        this.label = new Label("Profile menu", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel = new Label("", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel.setVisible(false);
        this.changeUsername = new TextButton("Change username", Main.getMain().getGameAssetManager().getSkin());
        this.changePassword = new TextButton("Change password", Main.getMain().getGameAssetManager().getSkin());
        this.changeAvatar = new TextButton("Change avatar", Main.getMain().getGameAssetManager().getSkin());
        this.deleteAccount = new TextButton("Delete account", Main.getMain().getGameAssetManager().getSkin());
        this.exit = new TextButton("Exit", Main.getMain().getGameAssetManager().getSkin());
        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(label);
        table.row().pad(40,0,0,0);
        table.add(changeUsername).width(500);
        table.row().pad(40,0,0,0);
        table.add(changePassword).width(500);
        table.row().pad(40,0,0,0);
        table.add(changeAvatar).width(500);
        table.row().pad(40,0,0,0);
        table.add(deleteAccount).width(500);
        table.row().pad(40,0,0,0);
        table.add(exit).width(500);
        table.row().pad(40,0,0,0);
        stage.addActor(messageLabel);
        stage.addActor(table);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleProfileMenuButtons();
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

    public TextButton getChangeUsername() {
        return changeUsername;
    }

    public TextButton getChangePassword() {
        return changePassword;
    }

    public TextButton getChangeAvatar() {
        return changeAvatar;
    }

    public TextButton getDeleteAccount() {
        return deleteAccount;
    }

    public TextButton getExit() {
        return exit;
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
