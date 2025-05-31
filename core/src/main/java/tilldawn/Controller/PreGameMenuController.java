package tilldawn.Controller;

import tilldawn.Main;
import tilldawn.Model.Sfx;
import tilldawn.Model.Weapons.Revolver;
import tilldawn.Model.Weapons.Shotgun;
import tilldawn.Model.Weapons.Smg;
import tilldawn.Model.Weapons.Weapon;
import tilldawn.View.GameView;
import tilldawn.View.MainMenuView;
import tilldawn.View.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public void handlePreGameMenuButtons() {

        if (view == null) {
            return;
        }

        if (view.getBackButton().isChecked()) {

            Sfx.click(1);
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView());

        } else if (view.getStartButton().isChecked()) {

            Sfx.click(1);
            view.getStartButton().setChecked(false);
            Main.getMain().getScreen().dispose();

            int charIndex = 1;
            if (view.getHeroSelectBox().getSelected().equals("Diamond")) {
                charIndex = 2;
            } else if (view.getHeroSelectBox().getSelected().equals("Scarlet")) {
                charIndex = 3;
            } else if (view.getHeroSelectBox().getSelected().equals("Lilith")) {
                charIndex = 4;
            } else if (view.getHeroSelectBox().getSelected().equals("Dasher")) {
                charIndex = 5;
            }

            Weapon defaultWeapon = null;
            if (view.getSelectedWeapon().equals("Revolver")) {
                defaultWeapon = new Revolver(Main.getMain().isAutoReload());
            } else if (view.getSelectedWeapon().equals("Shotgun")) {
                defaultWeapon = new Shotgun(Main.getMain().isAutoReload());
            } else {
                defaultWeapon = new Smg(Main.getMain().isAutoReload());
            }

            int minutes = Integer.parseInt(view.getSelectedTime());

            Main.setCurrentGameView(new GameView(charIndex,minutes,defaultWeapon));
            Main.getMain().setScreen(Main.getCurrentGameView());
        }
    }
}
