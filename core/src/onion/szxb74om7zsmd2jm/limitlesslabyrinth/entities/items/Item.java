package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Entity;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Bow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullWeapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.turrets.Turret;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 1/31/2017.
 */
public class Item {
    public Sprite getSprite() {
        return sprite;
    }
    protected Sprite sprite;
    protected Sprite projectileSprite;
    protected Projectile projectile;
    protected float dmg;
    protected float basedmg;
    protected long cooldown;

    public int getLvl() {
        return lvl;
    }

    protected int lvl;
    protected String type;
    public float getDmg() {
        return dmg;
    }

    public long getItemXP() {
        return itemXP;
    }

    public void setItemXP(long itemXP) {
        this.itemXP = itemXP;
    }

    protected long itemXP = 0;

    public long getXPtoLVL() {
        return XPtoLVL;
    }

    public void setXPtoLVL(long XPtoLVL) {
        this.XPtoLVL = XPtoLVL;
    }

    protected long XPtoLVL = 40;
    public long getCooldown() {
        return cooldown;
    }
    public String getType() {
        return type;
    }

    public Item(){

    }
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        return new Projectile();
    }

    public Turret placeTurret(float x, float y){
        return new Turret(x,y,new NullWeapon());
    }

    public void SWAPVAL(){

    }

    public void LVLup(){
        lvl++;
        this.dmg += basedmg * (lvl - 1);
    }

    public void LVLup(String weaponname){

        lvl++;
        this.dmg += basedmg * (lvl - 1);
       // this.dmg += lvl * 100;
    }
}
