package tilldawn.Controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Collidables.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TreeController {

    private final ArrayList<Tree> trees;

    public TreeController(ArrayList<Tree> trees) {
        this.trees = trees;
    }

    public void updateTrees(OrthographicCamera camera) {

        final int screenMargin = 2500;
        final int minDistance = 300;
        final int maxSpawnAttempts = 5;
        final int baseTrees = 8;

        float visibleLeft = camera.position.x - 960;
        float visibleRight = camera.position.x + 960;
        float visibleBottom = camera.position.y - 540;
        float visibleTop = camera.position.y + 540;


        float activeLeft = visibleLeft - screenMargin;
        float activeRight = visibleRight + screenMargin;
        float activeBottom = visibleBottom - screenMargin;
        float activeTop = visibleTop + screenMargin;

        Iterator<Tree> iterator = trees.iterator();
        while (iterator.hasNext()) {
            Tree tree = iterator.next();
            Vector2 pos = tree.getPosition();
            if (pos.x < activeLeft - 3000 || pos.x > activeRight + 3000 ||
                pos.y < activeBottom - 3000 || pos.y > activeTop + 3000) {
                Main.getCurrentGameView().getCollidables().remove(tree);
                iterator.remove();
            }
        }


        Random rand = new Random();
        int existingAroundCamera = 0;

        for (Tree t : trees) {
            Vector2 pos = t.getPosition();
            if (pos.x >= activeLeft && pos.x <= activeRight &&
                pos.y >= activeBottom && pos.y <= activeTop) {
                existingAroundCamera++;
            }
        }

        int targetCount = baseTrees + (int) (screenMargin * 0.02f);
        int treesToAdd = Math.max(0, targetCount - existingAroundCamera);

        for (int i = 0; i < treesToAdd; i++) {

            boolean placed = false;
            int attempts = 0;

            while (!placed && attempts < maxSpawnAttempts) {

                float x = rand.nextBoolean() ?
                    activeLeft + rand.nextInt(screenMargin) :
                    activeRight - rand.nextInt(screenMargin);

                float y = rand.nextBoolean() ?
                    activeBottom + rand.nextInt(screenMargin) :
                    activeTop - rand.nextInt(screenMargin);

                if (x < visibleLeft || x > visibleRight || y < visibleBottom || y > visibleTop) {


                    boolean valid = true;
                    for (Tree t : trees) {
                        Vector2 tPos = t.getPosition();
                        if (Math.abs(tPos.x - x) < minDistance &&
                            Math.abs(tPos.y - y) < minDistance) {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) {
                        Tree temp = new Tree(new Vector2(x, y));
                        trees.add(temp);
                        Main.getCurrentGameView().getCollidables().add(temp);
                        placed = true;
                    }
                }
                attempts++;
            }
        }
    }

    public void generateInitialTrees() {

        final int initialAreaSize = 5000;
        final float density = 0.03f;
        final int minDistance = 300;
        final int playerSafeRadius = 500;

        Random rand = new Random();
        Vector2 startCenter = new Vector2(160000, 160000);

        float startLeft = startCenter.x - (float) initialAreaSize / 2;
        float startBottom = startCenter.y - (float) initialAreaSize / 2;

        int targetCount = (int) (initialAreaSize * initialAreaSize * density / (70 * 92));

        for (int i = 0; i < targetCount; i++) {
            boolean placed = false;
            int attempts = 0;

            while (!placed && attempts < 5) {
                float x = startLeft + rand.nextFloat() * initialAreaSize;
                float y = startBottom + rand.nextFloat() * initialAreaSize;

                float dx = Math.abs(x - 160000);
                float dy = Math.abs(y - 160000);
                if (dx < playerSafeRadius && dy < playerSafeRadius) {
                    attempts++;
                    continue;
                }


                boolean valid = true;
                for (Tree t : trees) {
                    Vector2 pos = t.getPosition();
                    if (Math.abs(pos.x - x) < minDistance &&
                        Math.abs(pos.y - y) < minDistance) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    Tree temp = new Tree(new Vector2(x, y));
                    trees.add(temp);
                    Main.getCurrentGameView().getCollidables().add(temp);
                    placed = true;
                }
                attempts++;
            }
        }
    }


    public void draw(OrthographicCamera camera) {
        for (Tree tree : trees) {
            tree.draw(camera);
        }
    }

}
