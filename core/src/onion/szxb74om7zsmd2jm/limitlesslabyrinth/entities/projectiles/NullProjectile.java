package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/12/2017.
 */
public class NullProjectile extends Projectile {

    public NullProjectile(float x, float y, float dmg, Item fromItem){
        this.fromItem = fromItem;
        this.dmg = dmg;
        this.x = x;
        this.y = y;
        sprite = new Sprite(spriteTextures.invisProjectileSprite);
    }

    @Override
    public void draw() {
    }

    @Override
    public void contact() {
    }
}
