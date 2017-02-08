package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullWeapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.LandMine;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.WizardOrb;

/**
 * Created by chris on 2/6/2017.
 */
public class Mine extends Trap {
    public Mine(){
        sprite = new Sprite(new Texture("LandMineItem.png"));
        dmg = 1000f;
        lvl = 1;
        ammo = 5;
        type = "projectile";

        projectile = new LandMine();
        projectileSprite = projectile.getSprite();
        cooldown = 40;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2) {
        LandMine landMine = new LandMine(x1, y1, x2, y2);
        ammo--;
        if(ammo <= 0){
            if(pl.getGui().getSelected() == 0) {
                pl.getGui().setItem1(new NullWeapon());
                pl.getGui().setEquipped(pl.getGui().getItem1());
            }
            else if(pl.getGui().getSelected() == 1) {
                pl.getGui().setItem2(new NullWeapon());
                pl.getGui().setEquipped(pl.getGui().getItem2());
            }
            else if(pl.getGui().getSelected() == 2) {
                pl.getGui().setItem3(new NullWeapon());
                pl.getGui().setEquipped(pl.getGui().getItem3());
            }
            else if(pl.getGui().getSelected() == 3) {
                pl.getGui().setItem4(new NullWeapon());
                pl.getGui().setEquipped(pl.getGui().getItem4());
            }
        }
        return landMine;
    }
}
