package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.AnimatedEnemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by taylor hudson on 2/8/2017.
 */
public class Demon extends AnimatedEnemy {

    public Demon(float x, float y, int level, TiledMapTileLayer collisionLayer)
    {
        super(x, y, level, collisionLayer, 2, 4, .2f, Play.MonsterType.DEMON, "random");
    }

    @Override
    public int determineXP(int level) {
        return 1000 * level;
    }

    @Override
    public float determineHealth(int level) {
        return 1500f * (float) level;
    }

}