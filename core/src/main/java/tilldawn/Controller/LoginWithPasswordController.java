package tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import tilldawn.Main;
import tilldawn.Model.Result;
import tilldawn.Model.User;
import tilldawn.View.LoginWithForgotPasswordOptionMenuView;
import tilldawn.View.LoginWithPasswordMenuView;
import tilldawn.View.MainMenuView;

public class LoginWithPasswordController {

    private LoginWithPasswordMenuView view;

    public void setView(LoginWithPasswordMenuView view) {
        this.view = view;
    }

    public void handleLoginWithPasswordMenuButtons() {

        if (view != null) {

            if (view.getLoginButton().isChecked()) {

                if (view.isUsernameEmpty() || view.isPasswordEmpty()) {
                    view.getLoginButton().setChecked(false);
                    return;
                }

                view.getLoginButton().setChecked(false);

                Result temp = loginWithPassword(view.getUsernameField().getText(), view.getPasswordField().getText());
                view.showTemporaryMessage(temp.message, () -> {
                    if (temp.success) {
                        Main.getMain().getScreen().dispose();
                        Main.getMain().setScreen(new MainMenuView());
                    }
                });
            } else if (view.getForgetPasswordButton().isChecked()) {
                view.getForgetPasswordButton().setChecked(false);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginWithForgotPasswordOptionMenuView());

            } else if (view.getLoginAsGuestButton().isChecked()) {
                view.getLoginAsGuestButton().setChecked(false);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView());
            }

        }

    }

    private Result loginWithPassword(String username, String password) {

        User temp = Main.getMain().getUserManager().getUser(username);

        if (temp == null) {
            return new Result(false, "User not found!");
        }

        if (!temp.getPassword().equals(password)) {
            return new Result(false, "Wrong password!");
        }

        Main.getMain().getUserManager().setLoggedInUser(temp);

        return new Result(true, "You Logged in successfully!");

    }


}
