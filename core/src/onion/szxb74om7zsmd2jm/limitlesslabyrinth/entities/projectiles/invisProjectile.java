package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Entity;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/11/2017.
 */
public class invisProjectile extends Projectile {
    float projectileDmg;
    private String origin;

    public invisProjectile(float x, float y, float dmg, Array<Enemy> hit, Item fromItem, String Origin){
        this.fromItem = fromItem;
        origin = Origin;
        enemiesHit = hit;
        this.dmg = 0;
        this.projectileDmg = dmg;
        this.x = x;
        this.y = y;
        sprite = new Sprite(spriteTextures.invisProjectileSprite);
        name = "invis";

        sprite.setPosition(x - sprite.getWidth()/2 ,y - sprite.getHeight()/2);
        time = System.currentTimeMillis() + 100;
    }

    @Override
    public void draw() {
    }

    public void contact(Entity enemy) {

        Play.getProjectiles().add(new LightningOrb(x,y, enemy.getSprite().getX(), enemy.getSprite().getY(), projectileDmg, enemiesHit, fromItem, origin));
        remove();
    }
}
