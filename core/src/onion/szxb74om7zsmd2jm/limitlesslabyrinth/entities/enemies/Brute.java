package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Entity;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;

/**
 * Created by 226864 on 1/31/2017.
 * et tu, brute?
 */
public class Brute extends Enemy {
    public Brute(float x, float y, int level, TiledMapTileLayer collisionLayer)
    {
        super(x, y, level, collisionLayer);
        this.xpDrop = determineXP(level);
        this.sprite = new Sprite(spriteTextures.bruteTexture);
        this.health = determineHealth(level);
        this.fullHealth = health;
        this.dmg = 10f;
        sprite.setPosition(collisionLayer.getTileWidth() * x, collisionLayer.getTileHeight() * y);
        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
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
