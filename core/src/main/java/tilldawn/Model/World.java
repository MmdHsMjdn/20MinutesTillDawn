package tilldawn.Model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import tilldawn.Main;

public class World {

    private final int tileSize = 32;
    private final int widthInTiles = 100;
    private final int heightInTiles = 100;
    private final int worldWidth = widthInTiles * tileSize;
    private final int worldHeight = heightInTiles * tileSize;
    private final Texture baseTileTexture;

    public World() {
        this.baseTileTexture = Main.getMain().getGameAssetManager().getBaseTileTexture();
    }

    public void update(Vector2 playerPosition , OrthographicCamera camera, Vector3 mouseWorldPosition) {
        float viewWidth = camera.viewportWidth;
        float viewHeight = camera.viewportHeight;

        camera.position.x = MathUtils.clamp(playerPosition.x, viewWidth/2f-10, worldWidth - viewWidth/2f+10);
        camera.position.y = MathUtils.clamp(playerPosition.y, viewHeight/2f-10, worldHeight - viewHeight/2f+10);
        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);
    }

    public void draw(OrthographicCamera camera) {

        int startX = (int) (camera.position.x - camera.viewportWidth/2) / tileSize;
        int endX = (int) (camera.position.x + camera.viewportWidth) / tileSize;
        int startY = (int) (camera.position.y - camera.viewportHeight) / tileSize;
        int endY = (int) (camera.position.y + camera.viewportHeight) / tileSize;

        startX = MathUtils.clamp(startX, 0, widthInTiles -1);
        endX = MathUtils.clamp(endX, 0, widthInTiles -1);
        startY = MathUtils.clamp(startY, 0, heightInTiles -1);
        endY = MathUtils.clamp(endY, 0, heightInTiles -1);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                Main.getBatch().draw(baseTileTexture, x*tileSize, y*tileSize, tileSize, tileSize);
            }
        }

    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getTileSize() {
        return tileSize;
    }
}
