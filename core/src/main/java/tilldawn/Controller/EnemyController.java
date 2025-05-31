package tilldawn.Controller;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import tilldawn.Main;
import tilldawn.Model.Collidables.Enemy.Enemy;
import tilldawn.Model.Collidables.Enemy.Eyebat;
import tilldawn.Model.Collidables.Enemy.TentacleMonster;
import tilldawn.Model.DefaultKeys.DefaultsKeys;
import tilldawn.Model.DefaultKeys.InputManager;
import tilldawn.Model.Drop;
import tilldawn.Model.Sfx;

import java.util.ArrayList;
import java.util.Iterator;


public class EnemyController {

    private final ArrayList<Vector2> deathPoints = new ArrayList<>();
    private boolean containEyebat = false;
    private boolean containMonster = false;
    private float eyebatDeathTimer = 0.0f;
    private float monsterDeathTimer = 0.0f;

    public void draw(float delta) {

        for (Drop drop : Main.getCurrentGameView().getDrops()) {
            drop.draw();
        }

        for (Enemy enemy : Main.getCurrentGameView().enemies()) {
            enemy.draw();
        }

        TextureRegion death = Main.getMain().getGameAssetManager().getDeathAnimation().getKeyFrame(delta,false);

        for (Vector2 point : deathPoints) {
            Main.getBatch().draw(death,point.x - death.getRegionWidth()/2f,point.y - death.getRegionHeight()/2f);
        }

        if (containEyebat) {
            if (eyebatDeathTimer > 0.5f) {
                eyebatDeathTimer = 0.0f;
                Sfx.eyebatDeath(1);
            }
        }

        if (containMonster) {
            if (monsterDeathTimer > 0.5f) {
                monsterDeathTimer = 0.0f;
                Sfx.monsterDeath(1);
            }
        }

        deathPoints.clear();
        containEyebat = false;
        containMonster = false;
    }

    public void update(float delta) {

        Eyebat.spawn(delta);
        TentacleMonster.spawn(delta);

        eyebatDeathTimer += delta;
        monsterDeathTimer += delta;

        Iterator<Enemy> iterator = Main.getCurrentGameView().enemies().iterator();
        while (iterator.hasNext()) {

            Enemy enemy = iterator.next();

            if (enemy.isDead()) {

                Main.getCurrentGameView().getPlayer().increaseKillsNumber();
                deathPoints.add(enemy.getPosition().cpy());
                Main.getCurrentGameView().getDrops().add(new Drop(enemy.getPosition().cpy()));

                if (enemy instanceof Eyebat) {
                    containEyebat = true;
                } else {
                    containMonster = true;
                }

                Main.getCurrentGameView().getCollidables().remove(enemy);
                iterator.remove();
                continue;
            }

            enemy.update(delta,Main.getCurrentGameView().getPlayer().getPosition().cpy());

            if (InputManager.isKeyJustPressed(DefaultsKeys.EnemiesGoAway) || InputManager.isKeyPressed(DefaultsKeys.EnemiesGoAway)) {
                for (Enemy e : Main.getCurrentGameView().enemies()) {
                    e.applyKnockDown();
                }
            }

        }

    }

}
