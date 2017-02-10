package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;

/**
 * Created by chris on 1/31/2017.
 */
public class Fists extends Weapon{
    public Fists(){
        sprite = new Sprite(new Texture("fists.png"));
        dmg = 10f;
        lvl = 1;
        type = "melee";
    }
}
