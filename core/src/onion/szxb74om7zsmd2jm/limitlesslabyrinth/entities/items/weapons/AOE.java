package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sun.org.apache.xpath.internal.operations.Or;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.AOEeffect;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Spell;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by Taylor on 2/18/2017.
 */
public class AOE extends Weapon {
    public AOE(int level){
        sprite = new Sprite(spriteTextures.spellbook);
        lvl = level;
        dmg = 3.3f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){
            dmg += 3.3 * i;
        }
        type = "projectile";
        cooldown = 80;//
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        return new AOEeffect(x1, y1, Player.getCharFace(), dmg*1.5f, 0, this, (int)(Math.random()*17), Origin);
    }


}
