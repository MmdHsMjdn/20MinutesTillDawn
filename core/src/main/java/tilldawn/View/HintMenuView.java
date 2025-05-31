package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Main;
import tilldawn.Model.Sfx;

public class HintMenuView implements Screen {

    private Stage stage;
    private TextButton backButton;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        Table leftTable = new Table();
        Table rightTable = new Table();
        Table abilityTable = new Table();

        Label.LabelStyle labelStyle = Main.getMain().getGameAssetManager().getSkin().get(Label.LabelStyle.class);
        labelStyle.font.getData().setScale(1.1f);

        Label titleLabel = new Label("Game Hints & Cheats", labelStyle);
        titleLabel.setFontScale(1.5f);
        titleLabel.setAlignment(Align.center);

        Label heroTitle = new Label("Hero Stats", labelStyle);
        heroTitle.setFontScale(1.3f);

        Label heroHeader = new Label(String.format("%-12s %-6s %s", "HERO", "SPEED", "HP"), labelStyle);
        Label shanaStats = new Label(String.format("%-12s %-6d %d", "Shana", 4, 4), labelStyle);
        Label diamondStats = new Label(String.format("%-12s %-6d %d", "Diamond", 1, 7), labelStyle);
        Label dasherStats = new Label(String.format("%-12s %-6d %d", "Dasher", 10, 2), labelStyle);
        Label lilithStats = new Label(String.format("%-12s %-6d %d", "Lilith", 3, 5), labelStyle);
        Label scarletStats = new Label(String.format("%-12s %-6d %d", "Scarlet", 5, 3), labelStyle);

        Label cheatTitle = new Label("Cheat Codes", labelStyle);
        cheatTitle.setFontScale(1.3f);

        Label cheatL = new Label("L - Add character level", labelStyle);
        Label cheatH = new Label("H - Increase health", labelStyle);
        Label cheatT = new Label("T - Decrease game time", labelStyle);
        Label cheatX = new Label("X - Enemies run away", labelStyle);

        Label abilityTitle = new Label("Abilities", labelStyle);
        abilityTitle.setFontScale(1.3f);
        abilityTitle.setAlignment(Align.center);

        Label vitality = new Label("VITALITY: Increase max HP", labelStyle);
        vitality.setAlignment(Align.center);
        Label damager = new Label("DAMAGER: +25% weapon damage for 10s", labelStyle);
        damager.setAlignment(Align.center);
        Label proCrease = new Label("PROCREASE: Projectile weapon unit increase", labelStyle);
        proCrease.setAlignment(Align.center);
        Label amoCrease = new Label("AMOCREASE: +5 max ammo", labelStyle);
        amoCrease.setAlignment(Align.center);
        Label speedy = new Label("SPEEDY: 2x movement speed for 10s", labelStyle);
        speedy.setAlignment(Align.center);

        leftTable.add(heroTitle).padBottom(15).row();
        leftTable.add(heroHeader).padBottom(10).row();
        leftTable.add(shanaStats).padBottom(5).row();
        leftTable.add(diamondStats).padBottom(5).row();
        leftTable.add(dasherStats).padBottom(5).row();
        leftTable.add(lilithStats).padBottom(5).row();
        leftTable.add(scarletStats).padBottom(5).row();

        rightTable.add(cheatTitle).padBottom(15).row();
        rightTable.add(cheatL).padBottom(5).row();
        rightTable.add(cheatH).padBottom(5).row();
        rightTable.add(cheatT).padBottom(5).row();
        rightTable.add(cheatX).padBottom(5).row();

        abilityTable.add(abilityTitle).padBottom(15).row();
        abilityTable.add(vitality).width(400).padBottom(5).row();
        abilityTable.add(damager).width(400).padBottom(5).row();
        abilityTable.add(proCrease).width(400).padBottom(5).row();
        abilityTable.add(amoCrease).width(400).padBottom(5).row();
        abilityTable.add(speedy).width(400).padBottom(5).row();

        backButton = new TextButton("Back", Main.getMain().getGameAssetManager().getSkin());
        backButton.getLabel().setFontScale(1.2f);

        mainTable.add(titleLabel).colspan(2).padTop(30).padBottom(20).row();
        mainTable.add(leftTable).padRight(40).top();
        mainTable.add(rightTable).padLeft(40).top().row();
        mainTable.add(abilityTable).colspan(2).center().padTop(20).row();
        mainTable.add(backButton).colspan(2).width(200).height(50).padTop(30);

        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (backButton.isPressed()) {
            Sfx.click(1);
            Main.getMain().setScreen(new MainMenuView());
            this.dispose();
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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
