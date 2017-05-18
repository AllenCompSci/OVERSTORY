package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by chris on 1/31/2017.
 */
public class Fists extends Weapon{
    public Fists(int level){
        sprite = new Sprite(spriteTextures.FistSprite);
        lvl = level;
        dmg = 10f + (lvl - 1) * 100;
        type = "melee";
    }
}
//