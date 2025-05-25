package tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import tilldawn.View.GameView;

public class GameController {

    private GameView view;
    private final OrthographicCamera camera;
    private static final float viewWidth = Gdx.graphics.getWidth();
    private static final float viewHeight = Gdx.graphics.getHeight();
    private WorldController worldController;
    private PlayerController playerController;
    private TreeController treeController;
    private BulletController bulletController;
    private WeaponController weaponController;

    public GameController() {
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, viewWidth, viewHeight);
    }

    public void setView(GameView view) {

        this.view = view;
        setWorldController();
        setPlayerController();
        setTreeController();
        setBulletController();
        setWeaponController();

    }

    private void setBulletController() {
        bulletController = new BulletController();
    }

    private void setWorldController() {
        this.worldController = new WorldController(view.getWorld() , camera);
    }

    private void setPlayerController() {
        this.playerController = new PlayerController(view.getPlayer());
    }

    private void setTreeController() {
        this.treeController = new TreeController(view.getTrees());
        this.treeController.generateInitialTrees();
    }

    private void setWeaponController() {
        this.weaponController = new WeaponController();
    }

    public void updateGame(float delta) {

        Vector3 mouseWorldPosition = mouseWorldPosition();
        Vector2 playerMovement = playerMovement();

        weaponController.updateWeapon(view.getPlayer(),delta);
        playerController.update(delta,playerMovement,mouseWorldPosition);
        worldController.update(view.getPlayer().getPosition() , mouseWorldPosition);
        treeController.updateTrees(camera);
        bulletController.update(delta);

    }

    public void draw(float delta) {

        worldController.draw();
        treeController.draw(camera);
        playerController.draw(delta);
        weaponController.draw(view.getPlayer());
        bulletController.draw();
    }

    private Vector2 playerMovement() {
        Vector2 moveInput = new Vector2();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveInput.y +=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveInput.y -=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveInput.x +=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveInput.x -=1;
        }

        return moveInput;
    }

    private Vector3 mouseWorldPosition() {
        Vector3 mouseWorldPosition = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouseWorldPosition);
        return mouseWorldPosition;
    }

}
