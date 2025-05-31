package tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import tilldawn.Controller.ScoreboardController;
import tilldawn.Main;
import tilldawn.Model.Sfx;
import tilldawn.Model.User;

import java.util.List;

public class ScoreboardView implements Screen {

    private Stage stage;
    private final ScoreboardController controller;
    private Table tableContainer;
    private SelectBox<String> sortSelectBox;
    private TextButton backButton;

    public ScoreboardView() {
        this.controller = new ScoreboardController();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = Main.getMain().getGameAssetManager().getSkin();
        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);
        labelStyle.font.getData().setScale(1.1f);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        Label titleLabel = new Label("Scoreboard", skin);
        titleLabel.setFontScale(1.8f);
        titleLabel.setAlignment(Align.center);

        Array<String> sortOptions = new Array<>();
        sortOptions.addAll("By Score", "By Name", "By Kills", "By Survival Time");

        sortSelectBox = new SelectBox<>(skin);
        sortSelectBox.setItems(sortOptions);
        sortSelectBox.setSelected("By Score");

        backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1.2f);
        backButton.setWidth(400);
        backButton.setHeight(60);

        mainTable.add(titleLabel).colspan(2).padTop(40).padBottom(40).expandX().center().row();

        Table sortRow = new Table();
        sortRow.add(new Label("Sort by:", skin)).padRight(50).left();
        sortSelectBox.setWidth(300);
        sortRow.add(sortSelectBox).left();
        mainTable.add(sortRow).colspan(2).padBottom(40).expandX().center().row();

        tableContainer = new Table();
        tableContainer.add(createTable(controller.getCurrentSortMethod(), skin)).expand().fill();
        mainTable.add(tableContainer).expand().fill().center().padBottom(40).row();

        mainTable.add(backButton).colspan(2).width(400).height(60).padBottom(40).center();

        stage.addActor(mainTable);
    }

    private void refreshTable() {
        tableContainer.clear();
        tableContainer.add(createTable(controller.getCurrentSortMethod(), Main.getMain().getGameAssetManager().getSkin())).expand().fill();
    }

    private Table createTable(String sortMethod, Skin skin) {
        Table table = new Table();
        table.top();

        Label header = new Label(getTableTitle(sortMethod), skin);
        header.setFontScale(1.3f);
        header.setAlignment(Align.center);
        table.add(header).colspan(3).padBottom(40).row();

        // تنظیم عرض ستون‌ها به صورت یکسان با header
        float rankWidth = 150;
        float usernameWidth = 300;
        float valueWidth = 150;
        float padding = 50;

        // Header row
        table.add(new Label("Rank", skin)).width(rankWidth).padRight(padding);
        table.add(new Label("Username", skin)).width(usernameWidth).padRight(padding);
        table.add(new Label(getValueColumnTitle(sortMethod), skin)).width(valueWidth).row();

        List<User> users = getSortedUsers(sortMethod);

        for (int i = 0; i < users.size() && i < 10; i++) {
            User user = users.get(i);

            boolean isCurrentUser = false;
            if (Main.getMain().getUserManager().getLoggedInUser() != null) {
                isCurrentUser = user.getUsername().equals(Main.getMain().getUserManager().getLoggedInUser().getUsername());
            }

            Label.LabelStyle rowStyle = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
            if (i < 3) {
                rowStyle.fontColor = Color.GOLD;
            } else if (isCurrentUser) {
                rowStyle.fontColor = Color.CYAN;
            }

            // Data rows - استفاده از همان عرض و paddingهای header
            table.add(new Label((i + 1) + ".", rowStyle)).width(rankWidth).padRight(padding);
            table.add(new Label(user.getUsername(), rowStyle)).width(usernameWidth).padRight(padding);
            String value = getValueForUser(user, sortMethod);
            table.add(new Label(value, rowStyle)).width(valueWidth).row();
        }

        return table;
    }

    private String getTableTitle(String sortMethod) {
        switch (sortMethod) {
            case "By Score":
                return "Top Scores";
            case "By Name":
                return "Alphabetical Order";
            case "By Kills":
                return "Top Killers";
            case "By Survival Time":
                return "Survival Time";
            default:
                return "Scoreboard";
        }
    }

    private String getValueColumnTitle(String sortMethod) {
        switch (sortMethod) {
            case "By Score":
                return "Score";
            case "By Kills":
                return "Kills";
            case "By Survival Time":
                return "Time (s)";
            default:
                return "Score";
        }
    }

    private List<User> getSortedUsers(String sortMethod) {
        switch (sortMethod) {
            case "By Score":
                return controller.getTopUsersByScore(10);
            case "By Name":
                return controller.getTopUsersByName(10);
            case "By Kills":
                return controller.getTopUsersByKills(10);
            case "By Survival Time":
                return controller.getTopUsersBySurvivalTime(10);
            default:
                return controller.getTopUsersByScore(10);
        }
    }

    private String getValueForUser(User user, String sortMethod) {
        switch (sortMethod) {
            case "By Score":
                return String.valueOf(user.getScore());
            case "By Kills":
                return String.valueOf(user.getNumberOfKills());
            case "By Survival Time":
                return String.valueOf(user.getMaxSurviveTime());
            default:
                return String.valueOf(user.getScore());
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        String selectedSort = sortSelectBox.getSelected();
        if (!selectedSort.equals(controller.getCurrentSortMethod())) {
            controller.setCurrentSortMethod(selectedSort);
            refreshTable();
        }

        if (backButton.isPressed()) {
            Sfx.click(1);
            Main.getMain().setScreen(new MainMenuView());
            dispose();
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
