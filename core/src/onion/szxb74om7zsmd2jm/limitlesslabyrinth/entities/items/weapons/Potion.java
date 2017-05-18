package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.Trap;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.LandMine;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.NullProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 5/14/2017.
 */
public class Potion extends Trap {
    public Potion(int level){
        sprite = new Sprite(spriteTextures.HealthPotionSprite);
        lvl = level;
        dmg = 333.3f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){
            dmg += 333.3 * i;
        }
        type = "trap";
        cooldown = 40;
        ammo = 1;
    }

    @Override//
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        Player.giveHealth(dmg);
        itemXP += lvl;
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
        return new NullProjectile(x1,y1,dmg,this);
    }
}
