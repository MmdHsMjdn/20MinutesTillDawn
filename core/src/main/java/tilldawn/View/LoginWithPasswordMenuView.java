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
import tilldawn.Controller.LoginWithPasswordController;
import tilldawn.Main;

public class LoginWithPasswordMenuView implements Screen {

    private Stage stage;
    private final TextButton LoginButton;
    private final TextButton ForgetPasswordButton;
    private final TextButton loginAsGuestButton;
    private final Label label;
    private final Label messageLabel;
    private final TextField usernameField;
    private final TextField passwordField;
    private final Table table;
    private final LoginWithPasswordController controller;

    public LoginWithPasswordMenuView() {
        this.controller = new LoginWithPasswordController();
        this.LoginButton = new TextButton("Login", Main.getMain().getGameAssetManager().getSkin());
        this.loginAsGuestButton = new TextButton("Login as guest", Main.getMain().getGameAssetManager().getSkin());
        this.ForgetPasswordButton = new TextButton("Forget password?", Main.getMain().getGameAssetManager().getSkin());
        this.label = new Label("Login menu", Main.getMain().getGameAssetManager().getSkin());
        this.usernameField = new TextField("Username: ", Main.getMain().getGameAssetManager().getSkin());
        this.passwordField = new TextField("Password: ", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel = new Label("", Main.getMain().getGameAssetManager().getSkin());
        this.messageLabel.setVisible(false);

        passwordField.setPasswordMode(false);
        addClearOnFirstClick(usernameField, "Username: ");
        addClearOnFirstClick(passwordField, "Password: ");

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
        table.row().pad(30, 0, 40, 0);
        table.add(LoginButton).width(500).left();
        table.row().pad(0, 0, 40, 0);
        table.add(loginAsGuestButton).width(500);
        table.row().pad(0, 0, 40, 0);
        table.add(ForgetPasswordButton).width(500).right();
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
        controller.handleLoginWithPasswordMenuButtons();

    }

    public TextButton getLoginButton() {
        return LoginButton;
    }

    public TextButton getForgetPasswordButton() {
        return ForgetPasswordButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
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

    public TextButton getLoginAsGuestButton() {
        return loginAsGuestButton;
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

    public boolean isUsernameEmpty() {
        return usernameField.getText().trim().isEmpty() || usernameField.getText().equals("Username: ");
    }

    public boolean isPasswordEmpty() {
        return passwordField.getText().trim().isEmpty() || passwordField.getText().equals("Password: ");
    }
}
