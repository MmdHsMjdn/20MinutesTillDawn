package tilldawn.Controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import tilldawn.Model.World;

public class WorldController {
    private final OrthographicCamera camera;
    private final World world;

    public WorldController(World world,OrthographicCamera camera) {
        this.camera = camera;
        this.world = world;
    }

    public void update(Vector2 playerPosition ,Vector3 mouseWorldPosition) {
        world.update(playerPosition,camera,mouseWorldPosition);
    }

    public void draw() {
        world.draw(camera);
    }
}
