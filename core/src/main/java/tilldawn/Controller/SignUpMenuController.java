package tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import tilldawn.Main;
import tilldawn.Model.Result;
import tilldawn.View.LoginWithPasswordMenuView;
import tilldawn.View.SignUpMenuView;

public class SignUpMenuController {
    private SignUpMenuView view;

    public void setView(SignUpMenuView view) {
        this.view = view;
    }

    public void handleSignUpMenuButtons() {

        if (view != null) {

            if (view.getSignUpButton().isChecked()) {

                if (view.isPasswordEmpty() || view.isUsernameEmpty() || view.isSecurityQuestionEmpty()) {
                    view.getSignUpButton().setChecked(false);
                    return;
                }

                view.getSignUpButton().setChecked(false);

                Result temp = Main.getMain().getUserManager().addUser(view.getUsernameField().getText(),
                    view.getPasswordField().getText(), view.getSecurityQuestionField().getText());

                view.showTemporaryMessage(temp.message, () -> {
                    if (temp.success) {
                        Main.getMain().getScreen().dispose();
                        Main.getMain().setScreen(new LoginWithPasswordMenuView());
                    }
                });

            } else {

                if (view.getLoginButton().isChecked()) {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new LoginWithPasswordMenuView());
                }

            }
        }

    }
}
