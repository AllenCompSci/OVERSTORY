package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.ShurikenProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/6/2017.
 */


public class Shuriken extends Weapon{
    private ShurikenProjectile s1;
    private ShurikenProjectile s2;
    private ShurikenProjectile s0;

    public Shuriken(int level){
        sprite = new Sprite(spriteTextures.ShurikenItemSprite);
        lvl = level;
        dmg = 1f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){
            dmg += 1 * i;
        }
        type = "projectile";//
        cooldown = 20;

    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin) {
        s0 = new ShurikenProjectile(x1, y1, x2, y2, dmg, this);
        s1 = new ShurikenProjectile(x1, y1, (float) (x2 - 80 * Math.sin(s0.getTheta())), (float) (y2 + 80 * Math.cos(s0.getTheta())), dmg, this);
        s2 = new ShurikenProjectile(x1, y1, (float) (x2 + 80 * Math.sin(s0.getTheta())), (float) (y2 - 80 * Math.cos(s0.getTheta())), dmg, this);
        if(Origin == "Enemy"){
            Play.getEnemyProjectiles().add(s1);
            Play.getEnemyProjectiles().add(s2);
        }
        else {
            Play.getProjectiles().add(s1);
            Play.getProjectiles().add(s2);
        }
        return new ShurikenProjectile(x1, y1, x2, y2, dmg, this);
    }
}
