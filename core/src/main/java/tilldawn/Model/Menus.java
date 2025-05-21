package tilldawn.Model;

import com.badlogic.gdx.Screen;
import tilldawn.View.*;


public enum Menus {

    HintMenu(new HintMenuView()),
    LoginMenu(new LoginMenuView()),
    MainMenu(new MainMenuView()),
    PauseMenu(new PauseMenuView()),
    PreGameMenu(new PreGameMenuView()),
    ProfileMenu(new ProfileMenuView()),
    ScoreboardMenu(new ScoreboardMenuView()),
    SettingsMenu(new SettingMenuView()),
    SignUpMenu(new SignUpMenuView());

    public final Screen screen;

    private Menus(Screen screen) {
        this.screen = screen;
    }

}
