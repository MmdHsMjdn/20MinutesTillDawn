package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Result;
import tilldawn.Model.Sfx;
import tilldawn.View.ChangePasswordView;
import tilldawn.View.ProfileMenuView;

public class ChangePasswordController {

    private ChangePasswordView view;

    public void setView(ChangePasswordView view) {
        this.view = view;
    }

    public void handleChangePasswordViewButtons() {

        if (view == null) {
            return;
        }

        if (view.getBackButton().isChecked()) {
            Sfx.click(1);
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView());
        }

        if (view.getChangePasswordButton().isChecked()) {
            Sfx.click(1);
            view.getChangePasswordButton().setChecked(false);

            if (view.isNewPasswordEmpty()) {
                return;
            }

            Result temp =
                Main.getMain().getUserManager().changePassword(Main.getMain().getUserManager().getLoggedInUser().getUsername(), view.getNewPasswordField().getText());

            view.showTemporaryMessage(temp.message, () -> {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView());
            });

        }
    }

}
