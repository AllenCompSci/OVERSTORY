package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.bullet;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by chris on 5/12/2017.
 */
public class MiniGun extends Weapon {
    public MiniGun(int level){
        sprite = new Sprite(spriteTextures.MiniGunSprite);
        lvl = level;
        type = "projectile";
        dmg = 2f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){
            dmg += 2 * i;//
        }
        cooldown = 0;
        XPtoLVL = 500 * lvl;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        return new bullet(x1, y1, x2, y2, dmg, this);
    }
}
