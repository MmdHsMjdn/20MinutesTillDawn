package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.ChangeAvatarController;
import tilldawn.Main;

public class ChangeAvatarView implements Screen {

    private Stage stage;
    private final Table table;

    private final Label titleLabel;
    private final SelectBox<String> avatarSelectBox;
    private final TextButton fileChooserButton;
    private final TextButton backButton;
    private final ChangeAvatarController controller;

    public ChangeAvatarView() {
        this.controller = new ChangeAvatarController();
        this.titleLabel = new Label("Change Avatar", Main.getMain().getGameAssetManager().getSkin());
        titleLabel.setFontScale(1.5f);

        Array<String> options = new Array<>();
        options.addAll("Avatar 1", "Avatar 2");
        this.avatarSelectBox = new SelectBox<>(Main.getMain().getGameAssetManager().getSkin());
        avatarSelectBox.setItems(options);
        avatarSelectBox.setSelected("Avatar 1");

        this.fileChooserButton = new TextButton("Choose Custom Avatar", Main.getMain().getGameAssetManager().getSkin());
        this.backButton = new TextButton("Back", Main.getMain().getGameAssetManager().getSkin());

        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.top().center();

        table.add(titleLabel).colspan(2).padTop(50).padBottom(30);
        table.row().padBottom(20);

        table.defaults().width(600).height(100).pad(15);
        table.add(new Label("Select Avatar:", Main.getMain().getGameAssetManager().getSkin())).padRight(20);
        table.add(avatarSelectBox).width(400).padLeft(20);
        table.row().padBottom(30);

        table.add(fileChooserButton).colspan(2);
        table.row().padBottom(40);

        table.add(backButton).colspan(2).padTop(30);

        stage.addActor(table);

        avatarSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                String selected = avatarSelectBox.getSelected();
                if (selected.equals("Avatar 1")) {
                    Main.getMain().getUserManager().getLoggedInUser().setAvatarAddress("Sprite/Avatar/male.jpg");
                } else if (selected.equals("Avatar 2")) {
                    Main.getMain().getUserManager().getLoggedInUser().setAvatarAddress("Sprite/Avatar/female.jpg");
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleChangeAvatarButtons();
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

    public SelectBox<String> getAvatarSelectBox() {
        return avatarSelectBox;
    }

    public TextButton getFileChooserButton() {
        return fileChooserButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
