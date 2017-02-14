package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.AnimatedEnemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;


/**
 * Created by taylor hudson on 2/8/2017.
 */
public class Hydra extends AnimatedEnemy {
    public Hydra(float x, float y, int level, TiledMapTileLayer collisionLayer)
    {
        super(x, y, level, collisionLayer, 1, 2, .2f, Play.MonsterType.HYDRA);
    }

    @Override
    public int determineXP(int level) {
        return 10 * level;
    }

    @Override
    public float determineHealth(int level) {
        return 500f * (float) level;
    }


}

