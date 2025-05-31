package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Sfx;
import tilldawn.View.*;

public class ProfileMenuController {

    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleProfileMenuButtons() {

        if (view == null) {
            return;
        }

        if (view.getChangeUsername().isChecked()) {
            Sfx.click(1);
            view.getChangeUsername().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ChangeUsernameView());

        } else if (view.getChangePassword().isChecked()) {
            Sfx.click(1);
            view.getChangePassword().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ChangePasswordView());

        } else if (view.getChangeAvatar().isChecked()) {
            Sfx.click(1);
            view.getChangeAvatar().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ChangeAvatarView());

        } else if (view.getDeleteAccount().isChecked()) {
            Sfx.click(1);
            view.getDeleteAccount().setChecked(false);

            String username = Main.getMain().getUserManager().getLoggedInUser().getUsername();

            Main.getMain().getUserManager().setLoggedInUser(null);

            if (Main.getMain().getUserManager().deleteUser(username)) {
                view.showTemporaryMessage("Your account deleted successfully.",()->{
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenuView());
                });
            }

        } else if (view.getExit().isChecked()) {
            Sfx.click(1);
            view.getExit().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView());

        }
    }
}
