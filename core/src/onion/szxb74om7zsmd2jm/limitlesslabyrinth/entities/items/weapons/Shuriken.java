package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.ShurikenProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/6/2017.
 */


public class Shuriken extends Weapon{
    private ShurikenProjectile s1;
    private ShurikenProjectile s2;
    private ShurikenProjectile s0;

    public Shuriken(){
        sprite = new Sprite(new Texture("shuriken.png"));
        dmg = 20f;
        lvl = 1;
        type = "projectile";
        cooldown = 10;

    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2) {
        s0 = new ShurikenProjectile(x1, y1, x2, y2);
        s1 = new ShurikenProjectile(x1, y1, (float) (x2 - 80 * Math.sin(s0.getTheta())), (float) (y2 + 80 * Math.cos(s0.getTheta())));
        s2 = new ShurikenProjectile(x1, y1, (float) (x2 + 80 * Math.sin(s0.getTheta())), (float) (y2 - 80 * Math.cos(s0.getTheta())));
        Play.getProjectiles().add(s1);
        Play.getProjectiles().add(s2);
        return new ShurikenProjectile(x1, y1, x2, y2);
    }
}