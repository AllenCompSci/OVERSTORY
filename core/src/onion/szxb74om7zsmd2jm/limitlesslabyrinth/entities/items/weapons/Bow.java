package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;

/**
 * Created by 226812 on 2/2/2017.
 */
public class Bow extends Weapon {
    public Bow(){
        sprite = new Sprite(new Texture("Bow.png"));
        dmg = 50f;
        lvl = 1;
        type = "projectile";
        cooldown = 20;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2){
        return new Arrow(x1, y1, x2, y2);
    }
}