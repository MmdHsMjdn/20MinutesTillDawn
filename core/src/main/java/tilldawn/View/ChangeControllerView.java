package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.ChangeControllersController;
import tilldawn.Main;
import tilldawn.Model.DefaultKeys.DefaultsKeys;

public class ChangeControllerView implements Screen {

    private Stage stage;
    private final Label titleLabel;
    private final Label messageLabel;
    private final TextButton moveUpButton;
    private final TextButton moveDownButton;
    private final TextButton moveLeftButton;
    private final TextButton moveRightButton;
    private final TextButton reloadButton;
    private final TextButton autoAimButton;
    private final TextButton ShootButton;
    private final TextButton backButton;
    private final Table table;
    private final ChangeControllersController controller;

    private boolean waitingForKey = false;
    private DefaultsKeys currentKeyBeingSet = null;
    private float messageTimer = 0;

    public ChangeControllerView() {
        this.controller = new ChangeControllersController();
        this.titleLabel = new Label("Change controllers", Main.getMain().getGameAssetManager().getSkin());
        titleLabel.setFontScale(1.5f);

        this.messageLabel = new Label("", Main.getMain().getGameAssetManager().getSkin());
        messageLabel.setVisible(false);

        this.moveUpButton = new TextButton("Move up", Main.getMain().getGameAssetManager().getSkin());
        this.moveDownButton = new TextButton("Move down", Main.getMain().getGameAssetManager().getSkin());
        this.moveRightButton = new TextButton("Move right", Main.getMain().getGameAssetManager().getSkin());
        this.moveLeftButton = new TextButton("Move left", Main.getMain().getGameAssetManager().getSkin());
        this.reloadButton = new TextButton("Reload", Main.getMain().getGameAssetManager().getSkin());
        this.autoAimButton = new TextButton("Auto aim", Main.getMain().getGameAssetManager().getSkin());
        this.ShootButton = new TextButton("Shoot", Main.getMain().getGameAssetManager().getSkin());
        this.backButton = new TextButton("Back", Main.getMain().getGameAssetManager().getSkin());

        this.table = new Table();
        this.controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.top().center();

        table.add(titleLabel).colspan(2).padTop(80).padBottom(70);
        table.row();

        table.defaults().width(500).height(60).pad(15);
        table.add(moveUpButton).padRight(30);
        table.add(moveDownButton).padLeft(30);
        table.row().padBottom(20);

        table.add(moveRightButton).padRight(30);
        table.add(moveLeftButton).padLeft(30);
        table.row().padBottom(20);

        table.add(reloadButton).padRight(30);
        table.add(autoAimButton).padLeft(30);
        table.row().padBottom(20);

        table.add(ShootButton).padRight(30);
        table.add(backButton).padLeft(30);
        table.row().padBottom(40);

        stage.addActor(messageLabel);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if (!waitingForKey) {
            try {
                controller.handleChangeControllerButtons();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (waitingForKey && currentKeyBeingSet != null) {
            for (int i = 0; i < Input.Keys.MAX_KEYCODE; ++i) {
                if (Gdx.input.isKeyJustPressed(i)) {
                    boolean accepted = currentKeyBeingSet.setInput(i, DefaultsKeys.InputType.Key);
                    finishInputSetting(accepted);
                    break;
                }
            }

            for (int j = 0; j <= 2; j++) {
                if (Gdx.input.isButtonJustPressed(j)) {
                    boolean accepted = currentKeyBeingSet.setInput(j, DefaultsKeys.InputType.Mouse);
                    finishInputSetting(accepted);
                    break;
                }
            }
        }

        if (messageTimer > 0) {
            messageTimer -= delta;
            if (messageTimer <= 0) {
                messageLabel.setVisible(false);
                table.setVisible(true);
            }
        }
    }

    private void finishInputSetting(boolean success) {
        waitingForKey = false;
        currentKeyBeingSet = null;
        messageLabel.setText(success ? "Changed successfully" : "This key or button is already reserved");
        messageLabel.setVisible(true);
        messageLabel.setPosition((float) Gdx.graphics.getWidth() / 2 - messageLabel.getText().length * 5, (float) Gdx.graphics.getHeight() / 2);
        table.setVisible(false);
        messageTimer = 3f;
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    public TextButton getMoveUpButton() { return moveUpButton; }
    public TextButton getMoveDownButton() { return moveDownButton; }
    public TextButton getMoveLeftButton() { return moveLeftButton; }
    public TextButton getMoveRightButton() { return moveRightButton; }
    public TextButton getReloadButton() { return reloadButton; }
    public TextButton getAutoAimButton() { return autoAimButton; }
    public TextButton getShootButton() { return ShootButton; }
    public TextButton getBackButton() { return backButton; }

    public void getNewKey(DefaultsKeys key) {
        this.waitingForKey = true;
        this.currentKeyBeingSet = key;
        messageLabel.setText("Enter the new key or button");
        messageLabel.setVisible(true);
        messageLabel.setPosition((float) Gdx.graphics.getWidth() / 2 - messageLabel.getText().length * 5, (float) Gdx.graphics.getHeight() / 2);
        table.setVisible(false);
    }
}
