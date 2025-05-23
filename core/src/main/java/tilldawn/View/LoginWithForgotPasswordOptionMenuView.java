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
import tilldawn.Controller.LoginWithForgotPasswordOptionController;
import tilldawn.Main;

public class LoginWithForgotPasswordOptionMenuView implements Screen {

    private Stage stage;
    private final TextButton changePasswordButton;
    private final Label label;
    private final Label messageLabel;
    private final TextField usernameField;
    private final TextField newPasswordField;
    private final TextField securityQuestionField;
    private final Table table;
    private final LoginWithForgotPasswordOptionController controller;

    public LoginWithForgotPasswordOptionMenuView() {
        this.controller = new LoginWithForgotPasswordOptionController();
        this.changePasswordButton = new TextButton("Change password", Main.getMain().getGameAssetManager().getSkin());
        this.label = new Label("Account recovery", Main.getMain().getGameAssetManager().getSkin());
        this.usernameField = new TextField("Username: ", Main.getMain().getGameAssetManager().getSkin());
        this.newPasswordField = new TextField("New password: ", Main.getMain().getGameAssetManager().getSkin());
        this.securityQuestionField = new TextField("Place of birth: ", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel = new Label("", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel.setVisible(false);

        newPasswordField.setPasswordMode(false);
        addClearOnFirstClick(usernameField, "Username: ");
        addClearOnFirstClick(newPasswordField, "New password: ");
        addClearOnFirstClick(securityQuestionField, "Place of birth: ");

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

                    if (field == newPasswordField) {
                        field.setPasswordMode(true);
                        field.setPasswordCharacter('*');
                    }
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
        table.add(usernameField).width(500).height(100);
        table.row().pad(20, 0, 0, 0);
        table.add(newPasswordField).width(500).height(100);
        table.row().pad(20, 0, 0, 0);
        table.add(securityQuestionField).width(500).height(100);
        table.row().pad(20, 0, 0, 0);
        table.add(changePasswordButton).width(500);
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
        controller.handleLoginWithForgotPasswordOptionMenuButtons();
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

    public TextField getNewPasswordField() {
        return newPasswordField;
    }

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public boolean isUsernameEmpty() {
        return usernameField.getText().trim().isEmpty() || usernameField.getText().equals("Username: ");
    }

    public boolean isNewPasswordEmpty() {
        return newPasswordField.getText().trim().isEmpty() || newPasswordField.getText().equals("Password: ");
    }

    public boolean isSecurityQuestionEmpty() {
        return securityQuestionField.getText().trim().isEmpty() || securityQuestionField.getText().equals("Place of birth: ");
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
