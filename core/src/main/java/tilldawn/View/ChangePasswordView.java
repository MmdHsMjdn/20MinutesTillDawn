package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.ChangePasswordController;
import tilldawn.Main;

public class ChangePasswordView implements Screen {

    private Stage stage;
    private final TextButton changePasswordButton;
    private final TextButton backButton;
    private final Label label;
    private final Label messageLabel;
    private final TextField newPasswordField;
    private final Table table;
    private final ChangePasswordController controller;

    public ChangePasswordView() {
        this.controller = new ChangePasswordController();
        this.label = new Label("Change password", Main.getMain().getGameAssetManager().getSkin());
        this.changePasswordButton = new TextButton("Change password", Main.getMain().getGameAssetManager().getSkin());
        this.backButton = new TextButton("Back", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel = new Label("", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel.setVisible(false);

        this.newPasswordField = new TextField("New password: ", Main.getMain().getGameAssetManager().getSkin());
        this.newPasswordField.setPasswordMode(false);

        addClearOnFirstClick(newPasswordField, "New password: ");

        this.table = new Table();
        controller.setView(this);
    }

    private void addClearOnFirstClick(final TextField field, final String defaultText) {
        field.addListener(new ClickListener() {
            private boolean cleared = false;

            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (!cleared && field.getText().equals(defaultText)) {
                    field.setText("");
                    cleared = true;
                    field.setPasswordMode(true);
                    field.setPasswordCharacter('*');
                }
            }
        });
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(label);
        table.row().pad(40, 0, 0, 0);
        table.add(newPasswordField).width(500).height(100);
        table.row().pad(40, 0, 0, 0);
        table.add(changePasswordButton).width(500).height(100);
        table.row().pad(40, 0, 0, 0);
        table.add(backButton).width(500).height(100);
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
        controller.handleChangePasswordViewButtons();
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

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextField getNewPasswordField() {
        return newPasswordField;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public boolean isNewPasswordEmpty() {
        return newPasswordField.getText().trim().isEmpty() || newPasswordField.getText().equals("New password: ");
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
