package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Spell;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by Taylor on 2/12/2017.
 */
public class Magic extends Weapon {
    public Magic(){
        sprite = new Sprite(new Texture("SPELL.png"));
        dmg = 100f;
        lvl = 1;
        type = "projectile";
        cooldown = 40;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2){
        return new Spell(x1, y1, x2, y2, dmg*3.9f, 0);
    }


}
