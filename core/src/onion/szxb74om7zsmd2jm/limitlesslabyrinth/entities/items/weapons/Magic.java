package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Spell;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by Taylor on 2/12/2017.
 */
public class Magic extends Weapon {
    public Magic(int level){
        sprite = new Sprite(spriteTextures.MagicItemSprite);
        lvl = level;
        dmg = 1.7f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){//
            dmg += 1.7 * i;
        }
        type = "projectile";
        cooldown = 40;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        return new Spell(x1, y1, Player.getCharFace(), dmg*1.5f, 0, this, (int)(Math.random()*17), System.currentTimeMillis() + 750);
    }


}
