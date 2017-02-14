package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;

/**
 * Created by chris on 1/31/2017.
 */
public class NullWeapon extends Weapon {
    public NullWeapon(){
        sprite = new Sprite(new Texture("nullItem.png"));
        type = "melee";
        dmg = 0f;
        lvl = 1;
    }
}
