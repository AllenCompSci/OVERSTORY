package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps;

import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Bow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.LaserGun;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullWeapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.WizardStaff;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by 226812 on 4/18/2017.
 */
public class EnemyTraps {
    protected float direction;
    protected Item projectile;
    protected long RateOfFire;
    protected float DetectionRadius;
    protected float distanceFromPlayer;
    protected int level;
    protected float spinRate;
    protected float x;
    protected float y;
    protected long time = 0;

    public EnemyTraps(float direction, String projectile, long RateOfFire, float DetectionRadius, int level, float spinRate, float x, float y){
        this.direction = (float) (direction * (Math.PI/180));
        this.RateOfFire = RateOfFire;
        this.DetectionRadius = DetectionRadius;
        this.spinRate = (float) (spinRate * (Math.PI/180));
        this.x = x * 32;
        this.y = y * 32 + 13;

        switch (projectile){
            case "arrow":
                this.projectile = new Bow(level);
                break;
            case "wizardorb" :
                this.projectile = new WizardStaff(level);
                break;
            case "laser" :
                this.projectile = new LaserGun(level);
                break;
            default:
                this.projectile = new Bow(level);
        }
    }

    public void update(){
        distanceFromPlayer = Math.abs((float) Math.sqrt(Math.pow((x) - (Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((y) - (Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight()/2), 2)));
        if(System.currentTimeMillis() > time && distanceFromPlayer <= DetectionRadius){
            fire();
            if(spinRate != 0)
                direction += spinRate;

            time = System.currentTimeMillis() + RateOfFire;
        }
    }

    public void fire(){
        System.out.println(Math.sin(direction));
        float offset = x + (float)(Math.cos(direction) * (180/Math.PI)) == x ? (float) .1 : 0;
        Play.getEnemyProjectiles().add(projectile.getProjectile(x,y,x + 7 * ((float)(Math.cos(direction) * (180/Math.PI))) + offset,y + 7 * ((float)((Math.sin(direction) * (180/Math.PI)))),"Enemy"));
    }

}
