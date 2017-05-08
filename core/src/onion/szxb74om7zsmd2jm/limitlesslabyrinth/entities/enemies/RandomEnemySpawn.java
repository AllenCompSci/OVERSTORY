package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.AnimatedEnemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by Taylor on 2/11/2017.
 */
public class RandomEnemySpawn extends AnimatedEnemy
{


    public RandomEnemySpawn(float x, float y, int level, TiledMapTileLayer collisionLayer, float speed, Play.MonsterType monster, String weapon) {

        super(x, y, level, collisionLayer, spriteTextures.ROW(monster), spriteTextures.COL(monster), speed, monster, weapon);
    }
}
