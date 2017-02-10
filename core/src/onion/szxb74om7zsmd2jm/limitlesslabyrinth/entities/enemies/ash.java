package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;

/**
 * Created by chris on 1/29/2017.
 */
public class ash extends Enemy{

    public ash(float x, float y, int level, TiledMapTileLayer collisionLayer) {
        super(x, y, level, collisionLayer);
        this.sprite = new Sprite(spriteTextures.ashTexture);
        this.health = 100f;
        this.fullHealth = health;
        this.dmg = 10f;
        this.xpDrop = 1;
        sprite.setPosition(collisionLayer.getTileWidth() * x, collisionLayer.getTileHeight() * y);
        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
    }
}
