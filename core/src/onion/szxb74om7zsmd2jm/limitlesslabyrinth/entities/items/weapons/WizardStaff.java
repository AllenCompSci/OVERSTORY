package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by 226812 on 2/6/2017.
 */
public class WizardStaff extends Weapon{
    public WizardStaff(int level){
        sprite = new Sprite(spriteTextures.WizardStaffSprite);
        lvl = level;
        type = "projectile";
        dmg = 2.5f ;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){
            dmg += 2.5 * i;
        }
        cooldown = 40;
        XPtoLVL = 10 * lvl;//
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        return new WizardOrb(x1, y1, x2, y2, dmg, Origin, this);
    }
}
