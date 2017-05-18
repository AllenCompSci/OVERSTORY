package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by chris on 1/31/2017.
 */
public class NullWeapon extends Weapon {
    public NullWeapon(){
        sprite = new Sprite(spriteTextures.NullProjectileItemSprite);
        type = "melee";
        dmg = 0f;
        lvl = 0;
        XPtoLVL = 10;
        cooldown = 40;//
    }
}
