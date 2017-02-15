package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by chris on 2/1/2017.
 */
public class Sword extends Weapon {
    public Sword(){
        sprite = new Sprite(spriteTextures.SwordItemSprite);
        dmg = 20f;
        lvl = 1;
        type = "melee";
        cooldown = 40;
    }
}
