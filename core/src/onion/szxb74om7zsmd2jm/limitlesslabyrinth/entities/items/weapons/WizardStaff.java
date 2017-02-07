package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;

/**
 * Created by 226812 on 2/6/2017.
 */
public class WizardStaff extends Weapon{
    public WizardStaff(){
        sprite = new Sprite(new Texture("staff.png"));
        dmg = 10f;
        lvl = 1;
        type = "projectile";
        projectile = new WizardOrb();
        projectileSprite = projectile.getSprite();
        cooldown = 0;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2){
        return new WizardOrb(x1, y1, x2, y2);
    }
}
