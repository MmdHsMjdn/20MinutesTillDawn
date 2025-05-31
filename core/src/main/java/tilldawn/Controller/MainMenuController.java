package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Sfx;
import tilldawn.View.*;

public class MainMenuController {

    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {

        if (view == null) {
            return;
        }

        if (view.getHintMenuButton().isChecked()) {

            Sfx.click(1);

            view.getHintMenuButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new HintMenuView());

        } else if (view.getLogoutButton().isChecked()) {

            Sfx.click(1);

            view.getLogoutButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().getUserManager().setLoggedInUser(null);
            Main.getMain().setScreen(new LoginWithPasswordMenuView());

        } else if (view.getPreGameButton().isChecked()) {

            Sfx.click(1);

            view.getPreGameButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PreGameMenuView());

        } else if (view.getResumeSavedGameButton().isChecked()) {

            Sfx.click(1);
            view.getResumeSavedGameButton().setChecked(false);

        } else if (view.getProfileButton().isChecked()) {

            Sfx.click(1);
            view.getProfileButton().setChecked(false);

            if (Main.getMain().getUserManager().getLoggedInUser() != null) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView());
            } else {
                view.showTemporaryMessage("You should login first!",()->{});
            }

        } else if (view.getScoreboardButton().isChecked()) {

            Sfx.click(1);
            view.getScoreboardButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ScoreboardView());

        } else if (view.getSettingButton().isChecked()) {

            Sfx.click(1);
            view.getSettingButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingMenuView());

        }
    }


}
