package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Result;
import tilldawn.Model.Sfx;
import tilldawn.View.ChangeUsernameView;
import tilldawn.View.ProfileMenuView;

public class ChangeUsernameController {

    private ChangeUsernameView view;

    public void setView(ChangeUsernameView view) {
        this.view = view;
    }

    public void handleChangeUsernameViewButtons() {

        if (view == null) {
            return;
        }

        if (view.getBackButton().isChecked()) {
            Sfx.click(1);
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView());
        }

        if (view.getChangeUsernameButton().isChecked()) {
            Sfx.click(1);
            view.getChangeUsernameButton().setChecked(false);

            if (view.isNewUsernameFieldEmpty()) {
                return;
            }

            Result temp = Main.getMain().getUserManager().changeUsername(Main.getMain().getUserManager().getLoggedInUser().getUsername() , view.getNewUsernameField().getText());

            view.showTemporaryMessage(temp.message,()->{
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView());
            });

        }
    }
}
