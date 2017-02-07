package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;

/**
 * Created by chris on 2/6/2017.
 */
public class Mine extends Trap {
    public Mine(){
        sprite = new Sprite(new Texture("LandMineItem.png"));
        dmg = 1000f;
        lvl = 1;
        type = "trap";

        /** CHANGE TO MINE PROJECTILE */
        /** WORK IN PROGRESS */
        projectile = new WizardOrb();
        projectileSprite = projectile.getSprite();
        cooldown = 0;
    }
}
