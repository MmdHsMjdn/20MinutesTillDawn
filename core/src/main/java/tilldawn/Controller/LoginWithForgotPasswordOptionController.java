package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Result;
import tilldawn.Model.Sfx;
import tilldawn.Model.ValidationRegexes;
import tilldawn.View.LoginWithForgotPasswordOptionMenuView;
import tilldawn.View.LoginWithPasswordMenuView;

public class LoginWithForgotPasswordOptionController {

    private LoginWithForgotPasswordOptionMenuView view;

    public void setView(LoginWithForgotPasswordOptionMenuView view) {
        this.view = view;
    }

    public void handleLoginWithForgotPasswordOptionMenuButtons() {

        if (view != null) {

            if (view.getChangePasswordButton().isChecked()) {

                Sfx.click(1);

                if (view.isUsernameEmpty() || view.isSecurityQuestionEmpty() || view.isNewPasswordEmpty()) {
                    view.getChangePasswordButton().setChecked(false);
                    return;
                }

                view.getChangePasswordButton().setChecked(false);

                Result temp = changePassword(view.getUsernameField().getText(),
                    view.getSecurityQuestionField().getText(), view.getNewPasswordField().getText());
                view.showTemporaryMessage(temp.message, () -> {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new LoginWithPasswordMenuView());
                });

            }
        }
    }

    private Result changePassword(String username, String placeOfBirth, String newPassword) {

        if (Main.getMain().getUserManager().getUser(username) == null) {
            return new Result(false, "User not found!");
        }

        if (!Main.getMain().getUserManager().getUser(username).getSecurityAnswer().equals(placeOfBirth)) {
            return new Result(false, "Wrong answer!");
        }

        if (ValidationRegexes.Password.getMatcher(newPassword) == null) {
            return new Result(false, "New password is too weak!");
        }

        Main.getMain().getUserManager().changePassword(username, newPassword);
        return new Result(true, "Password changed successfully.");
    }

}

