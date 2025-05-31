package tilldawn.Model.DefaultKeys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;

public enum DefaultsKeys {

    MoveUp(Input.Keys.W,InputType.Key),// changeable
    MoveDown(Input.Keys.S,InputType.Key),// changeable
    MoveLeft(Input.Keys.A,InputType.Key),// changeable
    MoveRight(Input.Keys.D,InputType.Key),// changeable
    Reload(Input.Keys.R,InputType.Key),// changeable
    ChangeWeapon(Input.Keys.C,InputType.Key),
    IncreaseHealth(Input.Keys.H,InputType.Key),
    IncreaseLevel(Input.Keys.L,InputType.Key),
    EnemiesGoAway(Input.Keys.X,InputType.Key),
    DecreaseTime(Input.Keys.T,InputType.Key),
    AutoReload(Input.Keys.Z,InputType.Key),
    AutoAim(Input.Keys.SPACE,InputType.Key),// changeable
    Shoot(Input.Buttons.LEFT,InputType.Mouse);// changeable

    public enum InputType {
        Key,
        Mouse
    }

    private int code;
    private InputType type;

    DefaultsKeys(int defaultCode, InputType type) {
        this.code = defaultCode;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public InputType getType() {
        return type;
    }

    public boolean setInput(int newCode, InputType newType) {
        if (isCodeUsed(newCode,newType)) {
            return false;
        }
        this.code = newCode;
        this.type = newType;

        Preferences preferences = Gdx.app.getPreferences("DefaultsKeys");
        preferences.putInteger(this.name() + "_code", newCode);
        preferences.putString(this.name() + "_type", type.name());
        preferences.flush();

        return true;
    }

    public static void loadFromPreferences() {

        Preferences prefs = Gdx.app.getPreferences("DefaultsKeys");

        for (DefaultsKeys key : DefaultsKeys.values()) {

            int savedCode = prefs.getInteger(key.name() + "_code" , key.code);
            String savedTypeString = prefs.getString(key.name() + "_type" , key.type.name());

            try {
                InputType savedType = InputType.valueOf(savedTypeString);
                key.code = savedCode;
                key.type = savedType;
            } catch (Exception ignored) {

            }
        }
    }

    private static boolean isCodeUsed(int code, InputType type) {
        for (DefaultsKeys key : DefaultsKeys.values()) {
            if (key.getCode() == code && key.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
