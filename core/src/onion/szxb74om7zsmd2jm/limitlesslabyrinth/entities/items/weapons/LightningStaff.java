package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.LightningOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/11/2017.
 */
public class LightningStaff extends Weapon{
    public LightningStaff(int level){
        sprite = new Sprite(spriteTextures.LightningStaffSprite);
        lvl = level;
        dmg = 3.3f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){
            dmg += 3.3 * i;
        }
        type = "projectile";
        cooldown = 40;
    }//

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        return new LightningOrb(x1, y1, x2, y2, dmg, new Array<Enemy>(), this, Origin);
    }
}
