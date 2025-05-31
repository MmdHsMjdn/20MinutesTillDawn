package tilldawn.Controller;

import jdk.jfr.internal.tool.View;
import tilldawn.Main;
import tilldawn.Model.AudioManager;
import tilldawn.Model.Sfx;
import tilldawn.View.ChangeControllerView;
import tilldawn.View.MainMenuView;
import tilldawn.View.SettingMenuView;

public class SettingMenuController {

    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
    }

    public void handleSettingMenuButtons() {

        if (view == null) {
            return;
        }

        if (view.getChangeMusicButton().isChecked()) {
            Sfx.click(1);
            view.getChangeMusicButton().setChecked(false);
            AudioManager.changeMusic();
        } else if (view.getIncreaseVolumeButton().isChecked()) {
            Sfx.click(1);
            view.getIncreaseVolumeButton().setChecked(false);
            float temp = AudioManager.getMusicVolume();
            temp = Math.min(temp + 0.1f, 1.0f);
            AudioManager.setMusicVolume(temp);
        } else if (view.getDecreaseVolumeButton().isChecked()) {
            Sfx.click(1);
            view.getDecreaseVolumeButton().setChecked(false);
            float temp = AudioManager.getMusicVolume();
            temp = Math.max(temp - 0.1f, 0f);
            AudioManager.setMusicVolume(temp);
        } else if (view.getAutoReloadOnButton().isChecked()) {
            Sfx.click(1);
            view.getAutoReloadOnButton().setChecked(false);
            Main.getMain().setAutoReload(true);
        } else if (view.getAutoReloadOffButton().isChecked()) {
            Sfx.click(1);
            view.getAutoReloadOffButton().setChecked(false);
            Main.getMain().setAutoReload(false);
        } else if (view.getChangeControllersButton().isChecked()) {
            Sfx.click(1);
            view.getChangeControllersButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ChangeControllerView());
        } else if (view.getTurnOffSFXButton().isChecked()) {
            Sfx.click(1);
            view.getTurnOffSFXButton().setChecked(false);
            Main.getMain().setSFXOn(false);
        } else if (view.getTurnOnSFXButton().isChecked()) {
            Sfx.click(1);
            view.getTurnOnSFXButton().setChecked(false);
            Main.getMain().setSFXOn(true);
        } else if (view.getBackButton().isChecked()) {
            Sfx.click(1);
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView());
        }
    }
}
