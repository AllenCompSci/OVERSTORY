package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Spell;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by Taylor on 2/18/2017.
 */
public class AOE extends Weapon {
    public AOE(){
        sprite = new Sprite(spriteTextures.spellbook);
        dmg = 100f;
        lvl = 1;
        type = "projectile";
        cooldown = 80;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2){
        return new Spell(x1, y1, Player.getCharFace(), dmg*1.5f, 0, this, (int)(Math.random()*17));
    }


}
