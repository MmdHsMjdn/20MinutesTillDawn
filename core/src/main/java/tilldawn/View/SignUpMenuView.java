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
import tilldawn.Controller.SignUpMenuController;
import tilldawn.Main;

public class SignUpMenuView implements Screen {

    private Stage stage;
    private final TextButton signUpButton;
    private final Label label;
    private final Label messageLabel;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField securityQuestionField;
    private final Table table;
    private final SignUpMenuController controller;

    public SignUpMenuView() {
        this.controller = new SignUpMenuController();
        this.signUpButton = new TextButton("Sign up", Main.getMain().getGameAssetManager().getSkin());
        this.label = new Label("Sign up menu", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel = new Label("", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel.setVisible(false);

        this.usernameField = new TextField("Username: ", Main.getMain().getGameAssetManager().getSkin());
        this.passwordField = new TextField("Password: ", Main.getMain().getGameAssetManager().getSkin());
        this.securityQuestionField = new TextField("Place of birth: ", Main.getMain().getGameAssetManager().getSkin());

        passwordField.setPasswordMode(false);

        addClearOnFirstClick(usernameField, "Username: ");
        addClearOnFirstClick(passwordField, "Password: ");
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

                    if (field == passwordField) {
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
        table.add(passwordField).width(500).height(100);
        table.row().pad(20, 0, 0, 0);
        table.add(securityQuestionField).width(500).height(100);
        table.row().pad(20, 0, 50, 0);
        table.add(signUpButton);
        table.row();
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
        controller.handleSignUpMenuButtons();
    }

    @Override
    public void resize(int i, int i1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    public TextButton getSignUpButton() {
        return signUpButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
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

    public boolean isUsernameFilled() {
        return !usernameField.getText().trim().isEmpty() && !usernameField.getText().equals("Username: ");
    }

    public boolean isPasswordFilled() {
        return !passwordField.getText().trim().isEmpty() && !passwordField.getText().equals("Password: ");
    }

    public boolean isSecurityQuestionFilled() {
        return !securityQuestionField.getText().trim().isEmpty() && !securityQuestionField.getText().equals("Place of birth: ");
    }

}
