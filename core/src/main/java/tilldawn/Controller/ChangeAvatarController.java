package tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import tilldawn.Main;
import tilldawn.Model.Sfx;
import tilldawn.View.ChangeAvatarView;
import tilldawn.View.ProfileMenuView;

import java.awt.FileDialog;
import java.awt.Frame;

public class ChangeAvatarController {

    private ChangeAvatarView view;

    public void setView(ChangeAvatarView view) {
        this.view = view;
    }

    public void handleChangeAvatarButtons() {
        if (view == null) return;

        if (view.getFileChooserButton().isPressed()) {
            Sfx.click(1);
            view.getFileChooserButton().setChecked(false);
            openFileChooser();
        }

        if (view.getBackButton().isPressed()) {
            Sfx.click(1);
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView());
        }
    }

    private void openFileChooser() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Choose Avatar Image", FileDialog.LOAD);
        fileDialog.setFilenameFilter((dir, name) ->
            name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            String filePath = fileDialog.getDirectory() + fileDialog.getFile();
            String relativePath = "avatars/custom/" + fileDialog.getFile();
            try {
                FileHandle source = Gdx.files.absolute(filePath);
                FileHandle dest = Gdx.files.local(relativePath);
                source.copyTo(dest);
                Main.getMain().getUserManager().getLoggedInUser().setAvatarAddress(relativePath);
            } catch (Exception e) {
                Gdx.app.error("Avatar", "Error copying avatar file", e);
            }
        }
    }
}
