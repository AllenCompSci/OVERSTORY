package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Entity;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.*;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.turrets.Turret;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/12/2017.
 */
public class TurretItem extends Trap {
    public TurretItem(){
        sprite = new Sprite(spriteTextures.TurretItemSprite);
        dmg = 0;
        lvl = 1;
        type = "turret";
        ammo = 1;
        cooldown = 0;
    }

    @Override
    public Turret placeTurret(float x, float y) {
        Turret turret = new Turret(x, y, new NullWeapon());
        ammo--;
        if (ammo <= 0) {
            if (Play.getGui().getSelected() == 0) {
                Play.getGui().setItem1(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem1());
            } else if (Play.getGui().getSelected() == 1) {
                Play.getGui().setItem2(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem2());
            } else if (Play.getGui().getSelected() == 2) {
                Play.getGui().setItem3(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem3());
            } else if (Play.getGui().getSelected() == 3) {
                Play.getGui().setItem4(new NullWeapon());
                Play.getGui().setEquipped(Play.getGui().getItem4());
            }
        }
        return turret;
    }
}
