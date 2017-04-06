package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.LaserBullet;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.ShurikenProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 4/6/2017.
 */
public class LaserGun extends Weapon{
    private LaserBullet s1;
    private LaserBullet s2;
    private LaserBullet s0;

    public LaserGun(int level){
        sprite = new Sprite(spriteTextures.LaserGunSprite);
        dmg = (float) (100f * Math.pow(level, 1.1));
        lvl = level;
        type = "projectile";
        cooldown = 1;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        s0 = new LaserBullet(x1, y1, x2, y2, dmg, Origin, this);
        s1 = new LaserBullet(x1, y1, (float) (x2 - 80 * Math.sin(s0.getTheta())), (float) (y2 + 80 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s2 = new LaserBullet(x1, y1, (float) (x2 + 80 * Math.sin(s0.getTheta())), (float) (y2 - 80 * Math.cos(s0.getTheta())), dmg, Origin, this);
        if(Origin == "Enemy"){
            Play.getEnemyProjectiles().add(s1);
            Play.getEnemyProjectiles().add(s2);
        }
        else {
            Play.getProjectiles().add(s1);
            Play.getProjectiles().add(s2);
        }
        return new LaserBullet(x1, y1, x2, y2, dmg, Origin, this);
    }
}
