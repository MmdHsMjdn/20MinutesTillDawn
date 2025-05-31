package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.PreGameMenuController;
import tilldawn.Main;

public class PreGameMenuView implements Screen {

    private Stage stage;
    private final Label titleLabel;

    private final SelectBox<String> heroSelectBox;
    private final SelectBox<String> weaponSelectBox;
    private final SelectBox<String> timeSelectBox;

    private final TextButton startButton;
    private final TextButton backButton;

    private final Table table;
    private final PreGameMenuController controller;

    public PreGameMenuView() {
        this.controller = new PreGameMenuController();
        this.titleLabel = new Label("Pre-Game Settings", Main.getMain().getGameAssetManager().getSkin());
        titleLabel.setFontScale(1.5f);

        this.heroSelectBox = new SelectBox<>(Main.getMain().getGameAssetManager().getSkin());
        this.weaponSelectBox = new SelectBox<>(Main.getMain().getGameAssetManager().getSkin());
        this.timeSelectBox = new SelectBox<>(Main.getMain().getGameAssetManager().getSkin());

        Array<String> heroes = new Array<>();
        heroes.addAll("Shana", "Diamond", "Scarlet", "Lilith", "Dasher");
        heroSelectBox.setItems(heroes);
        heroSelectBox.setSelected("Shana");

        Array<String> weapons = new Array<>();
        weapons.addAll("Revolver", "Shotgun", "SMG");
        weaponSelectBox.setItems(weapons);
        weaponSelectBox.setSelected("Revolver");

        Array<String> times = new Array<>();
        times.addAll("2", "5", "10", "20");
        timeSelectBox.setItems(times);
        timeSelectBox.setSelected("5");


        this.startButton = new TextButton("Start Game", Main.getMain().getGameAssetManager().getSkin());
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


        table.add(titleLabel).colspan(2).padTop(50).padBottom(50);
        table.row().padBottom(20);


        table.defaults().width(400).height(60).pad(15);


        table.add(new Label("Select Hero:", Main.getMain().getGameAssetManager().getSkin())).padRight(20);
        table.add(heroSelectBox).width(350).padLeft(20);
        table.row();


        table.add(new Label("Select Weapon:", Main.getMain().getGameAssetManager().getSkin())).padRight(20);
        table.add(weaponSelectBox).width(350).padLeft(20);
        table.row();


        table.add(new Label("Select Time (minutes):", Main.getMain().getGameAssetManager().getSkin())).padRight(20);
        table.add(timeSelectBox).width(350).padLeft(20);
        table.row().padBottom(40);

        table.add(startButton).colspan(2).padTop(50);
        table.row();
        table.add(backButton).colspan(2).padTop(10);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePreGameMenuButtons();
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


    public SelectBox<String> getHeroSelectBox() { return heroSelectBox; }
    public SelectBox<String> getWeaponSelectBox() { return weaponSelectBox; }
    public SelectBox<String> getTimeSelectBox() { return timeSelectBox; }

    public TextButton getStartButton() { return startButton; }
    public TextButton getBackButton() { return backButton; }

    public String getSelectedHero() { return heroSelectBox.getSelected(); }
    public String getSelectedWeapon() { return weaponSelectBox.getSelected(); }
    public String getSelectedTime() { return timeSelectBox.getSelected(); }
}
