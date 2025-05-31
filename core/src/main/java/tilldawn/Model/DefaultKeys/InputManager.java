package tilldawn.Model.DefaultKeys;

import com.badlogic.gdx.Gdx;

public class InputManager {

    public static boolean isKeyPressed(DefaultsKeys key) {

        if (key.getType() == DefaultsKeys.InputType.Key) {

            return Gdx.input.isKeyPressed(key.getCode());

        } else if (key.getType() == DefaultsKeys.InputType.Mouse) {

            return Gdx.input.isButtonPressed(key.getCode());

        }

        return false;
    }

    public static boolean isKeyJustPressed(DefaultsKeys key) {

        if (key.getType() == DefaultsKeys.InputType.Key) {

            return Gdx.input.isKeyJustPressed(key.getCode());

        } else if (key.getType() == DefaultsKeys.InputType.Mouse) {

            return Gdx.input.isButtonJustPressed(key.getCode());

        }

        return false;
    }
}
