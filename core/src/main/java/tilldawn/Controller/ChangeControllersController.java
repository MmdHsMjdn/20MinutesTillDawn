package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.DefaultKeys.DefaultsKeys;
import tilldawn.Model.Sfx;
import tilldawn.View.ChangeControllerView;
import tilldawn.View.SettingMenuView;

public class ChangeControllersController {

    private ChangeControllerView view;

    public void setView(ChangeControllerView view) {
        this.view = view;
    }

    public void handleChangeControllerButtons() throws InterruptedException {

        if (view == null) {
            return;
        }

        if (view.getMoveUpButton().isChecked()) {
            Sfx.click(1);
            view.getMoveUpButton().setChecked(false);
            view.getNewKey(DefaultsKeys.MoveUp);
        } else if (view.getMoveDownButton().isChecked()) {
            Sfx.click(1);
            view.getMoveDownButton().setChecked(false);
            view.getNewKey(DefaultsKeys.MoveDown);
        } else if (view.getMoveLeftButton().isChecked()) {
            Sfx.click(1);
            view.getMoveLeftButton().setChecked(false);
            view.getNewKey(DefaultsKeys.MoveLeft);
        } else if (view.getMoveRightButton().isChecked()) {
            Sfx.click(1);
            view.getMoveRightButton().setChecked(false);
            view.getNewKey(DefaultsKeys.MoveRight);
        } else if (view.getReloadButton().isChecked()) {
            Sfx.click(1);
            view.getReloadButton().setChecked(false);
            view.getNewKey(DefaultsKeys.Reload);
        } else if (view.getAutoAimButton().isChecked()) {
            Sfx.click(1);
            view.getAutoAimButton().setChecked(false);
            view.getNewKey(DefaultsKeys.AutoAim);
        } else if (view.getShootButton().isChecked()) {
            Sfx.click(1);
            view.getShootButton().setChecked(false);
            view.getNewKey(DefaultsKeys.Shoot);
        } else if (view.getBackButton().isChecked()) {
            Sfx.click(1);
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingMenuView());
        }
    }
}
