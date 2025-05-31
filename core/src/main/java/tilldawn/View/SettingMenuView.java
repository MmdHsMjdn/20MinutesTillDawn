package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.SettingMenuController;
import tilldawn.Main;

public class SettingMenuView implements Screen {

    private Stage stage;
    private final Label titleLabel;
    private final TextButton changeMusicButton;
    private final TextButton increaseVolumeButton;
    private final TextButton decreaseVolumeButton;
    private final TextButton autoReloadOnButton;
    private final TextButton autoReloadOffButton;
    private final TextButton changeControllersButton;
    private final TextButton turnOffSFXButton;
    private final TextButton turnOnSFXButton;
    private final TextButton backButton;
    private final Table table;
    private final SettingMenuController controller;

    public SettingMenuView() {
        this.controller = new SettingMenuController();
        this.titleLabel = new Label("Settings", Main.getMain().getGameAssetManager().getSkin());
        titleLabel.setFontScale(1.5f);

        this.changeMusicButton = new TextButton("Change Music", Main.getMain().getGameAssetManager().getSkin());
        this.increaseVolumeButton = new TextButton("Volume +", Main.getMain().getGameAssetManager().getSkin());
        this.decreaseVolumeButton = new TextButton("Volume -", Main.getMain().getGameAssetManager().getSkin());
        this.autoReloadOnButton = new TextButton("Auto-Reload On", Main.getMain().getGameAssetManager().getSkin());
        this.autoReloadOffButton = new TextButton("Auto-Reload Off", Main.getMain().getGameAssetManager().getSkin());
        this.changeControllersButton = new TextButton("Controllers", Main.getMain().getGameAssetManager().getSkin());
        this.turnOffSFXButton = new TextButton("SFX Off", Main.getMain().getGameAssetManager().getSkin());
        this.turnOnSFXButton = new TextButton("SFX On", Main.getMain().getGameAssetManager().getSkin());
        this.backButton = new TextButton("Back to Menu", Main.getMain().getGameAssetManager().getSkin());

        this.table = new Table();
        controller.setView(this);
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

        table.add(changeMusicButton).padRight(30);
        table.add(increaseVolumeButton).padLeft(30);
        table.row().padBottom(20);

        table.add(decreaseVolumeButton).padRight(30);
        table.add(autoReloadOnButton).padLeft(30);
        table.row().padBottom(20);

        table.add(autoReloadOffButton).padRight(30);
        table.add(changeControllersButton).padLeft(30);
        table.row().padBottom(20);

        table.add(turnOnSFXButton).padRight(30);
        table.add(turnOffSFXButton).padLeft(30);
        table.row().padBottom(40);

        table.add(backButton).colspan(2).width(350).height(70).padTop(100);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleSettingMenuButtons();
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


    public TextButton getChangeMusicButton() {
        return changeMusicButton;
    }

    public TextButton getIncreaseVolumeButton() {
        return increaseVolumeButton;
    }

    public TextButton getDecreaseVolumeButton() {
        return decreaseVolumeButton;
    }

    public TextButton getAutoReloadOnButton() {
        return autoReloadOnButton;
    }

    public TextButton getAutoReloadOffButton() {
        return autoReloadOffButton;
    }

    public TextButton getChangeControllersButton() {
        return changeControllersButton;
    }

    public TextButton getTurnOffSFXButton() {
        return turnOffSFXButton;
    }

    public TextButton getTurnOnSFXButton() {
        return turnOnSFXButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
