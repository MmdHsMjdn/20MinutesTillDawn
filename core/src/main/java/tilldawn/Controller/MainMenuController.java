package tilldawn.Controller;

import tilldawn.Main;
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

            view.getHintMenuButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new HintMenuView());

        } else if (view.getLogoutButton().isChecked()) {

            view.getLogoutButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().getUserManager().setLoggedInUser(null);
            Main.getMain().setScreen(new LoginWithPasswordMenuView());

        } else if (view.getPreGameButton().isChecked()) {

            view.getPreGameButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PreGameMenuView());

        } else if (view.getResumeSavedGameButton().isChecked()) {

            view.getResumeSavedGameButton().setChecked(false);
            //TODO

        } else if (view.getProfileButton().isChecked()) {

            view.getProfileButton().setChecked(false);

            if (Main.getMain().getUserManager().getLoggedInUser() != null) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView());
            } else {
                view.showTemporaryMessage("You should login first!",()->{});
            }

        } else if (view.getScoreboardButton().isChecked()) {

            view.getScoreboardButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ScoreboardMenuView());

        } else if (view.getSettingButton().isChecked()) {

            view.getSettingButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingMenuView());

        }
    }


}
