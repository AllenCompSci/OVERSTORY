package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullWeapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.LandMine;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/6/2017.
 */
public class Mine extends Trap {
    public Mine(int level){
        sprite = new Sprite(spriteTextures.LandMineItemSprite);
        dmg = 1000f + (lvl - 1) * 100;
        lvl = level;
        ammo = 90;
        type = "trap";

        cooldown = 40;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin) {
        itemXP += lvl;
        LandMine landMine = new LandMine(x1, y1, x2, y2, dmg, this);
        ammo--;
        if(ammo <= 0){
            if(Play.getGui().getSelected() == 0) {
                Play.getGui().setItem1(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem1());
            }
            else if(Play.getGui().getSelected() == 1) {
                Play.getGui().setItem2(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem2());
            }
            else if(Play.getGui().getSelected() == 2) {
                Play.getGui().setItem3(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem3());
            }
            else if(Play.getGui().getSelected() == 3) {
                Play.getGui().setItem4(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem4());
            }
        }
        return landMine;
    }
}
