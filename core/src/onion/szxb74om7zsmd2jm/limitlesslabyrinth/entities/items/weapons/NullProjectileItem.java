package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.NullProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;

/**
 * Created by chris on 2/12/2017.
 */
public class NullProjectileItem extends Weapon {
    public NullProjectileItem(){
        sprite = new Sprite(new Texture("nullItem.png"));
        dmg = 0f;
        lvl = 1;
        type = "projectile";
        cooldown = 40;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2){
        return new NullProjectile(x1, y1, dmg);
    }
}
