package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.ShurikenProjectile;

/**
 * Created by chris on 2/6/2017.
 */


/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */
/** WORK IN PROGRESS */

public class Shuriken extends Weapon{
    public Shuriken(){
        sprite = new Sprite(new Texture("shuriken.png"));
        dmg = 10f;
        lvl = 1;
        type = "projectile";
        projectile = new ShurikenProjectile();
        projectileSprite = projectile.getSprite();
        cooldown = 20;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2) {
        pl.getProjectiles().add(new ShurikenProjectile(pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth()/2, pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight()/2, pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth(), pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight()));
        pl.getProjectiles().add(new ShurikenProjectile(pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth()/2, pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight()/2, pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth(), pl.getPlayer().getSprite().getY() - pl.getPlayer().getSprite().getHeight()/2));
        pl.getProjectiles().add(new ShurikenProjectile(pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth()/2, pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight()/2, pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth()/2, pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight()));
        return (new ShurikenProjectile(pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth()/2, pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight()/2, pl.getPlayer().getSprite().getX() - pl.getPlayer().getSprite().getWidth()/2, pl.getPlayer().getSprite().getY() - pl.getPlayer().getSprite().getHeight()));
    }
}
