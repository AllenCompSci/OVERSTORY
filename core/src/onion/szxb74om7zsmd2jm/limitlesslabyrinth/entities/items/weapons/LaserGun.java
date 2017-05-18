package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.LaserBullet;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.ShurikenProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 4/6/2017.
 */
public class LaserGun extends Weapon{
    private LaserBullet s1;
    private LaserBullet s2;
    private LaserBullet s3;
    private LaserBullet s4;
    private LaserBullet s5;
    private LaserBullet s6;
    private LaserBullet s7;
    private LaserBullet s8;
    private LaserBullet s9;
    private LaserBullet s10;
    private LaserBullet s11;
    private LaserBullet s12;
    private LaserBullet s0;
//
    public LaserGun(int level){
        sprite = new Sprite(spriteTextures.LaserGunSprite);
        lvl = level;
        dmg = 3.3f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){
            dmg += 3.3 * i;
        }
        type = "projectile";
        cooldown = 50;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        s0 = new LaserBullet(x1, y1, x2, y2, dmg, Origin, this);
        s1 = new LaserBullet(x1, y1, (float) (x2 - 10 * Math.sin(s0.getTheta())), (float) (y2 + 10 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s2 = new LaserBullet(x1, y1, (float) (x2 + 10 * Math.sin(s0.getTheta())), (float) (y2 - 10 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s3 = new LaserBullet(x1, y1, (float) (x2 - 20 * Math.sin(s0.getTheta())), (float) (y2 + 20 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s4 = new LaserBullet(x1, y1, (float) (x2 + 20 * Math.sin(s0.getTheta())), (float) (y2 - 20 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s5 = new LaserBullet(x1, y1, (float) (x2 - 30 * Math.sin(s0.getTheta())), (float) (y2 + 30 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s6 = new LaserBullet(x1, y1, (float) (x2 + 30 * Math.sin(s0.getTheta())), (float) (y2 - 30 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s7 = new LaserBullet(x1, y1, (float) (x2 - 40 * Math.sin(s0.getTheta())), (float) (y2 + 40 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s8 = new LaserBullet(x1, y1, (float) (x2 + 40 * Math.sin(s0.getTheta())), (float) (y2 - 40 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s9 = new LaserBullet(x1, y1, (float) (x2 - 50 * Math.sin(s0.getTheta())), (float) (y2 + 50 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s10 = new LaserBullet(x1, y1, (float) (x2 + 50 * Math.sin(s0.getTheta())), (float) (y2 - 50 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s11 = new LaserBullet(x1, y1, (float) (x2 - 60 * Math.sin(s0.getTheta())), (float) (y2 + 60 * Math.cos(s0.getTheta())), dmg, Origin, this);
        s12 = new LaserBullet(x1, y1, (float) (x2 + 60 * Math.sin(s0.getTheta())), (float) (y2 - 60 * Math.cos(s0.getTheta())), dmg, Origin, this);
        if(Origin == "Enemy"){
            Play.getEnemyProjectiles().add(s1);
            Play.getEnemyProjectiles().add(s2);
            Play.getEnemyProjectiles().add(s3);
            Play.getEnemyProjectiles().add(s4);
            Play.getEnemyProjectiles().add(s5);
            Play.getEnemyProjectiles().add(s6);
            Play.getEnemyProjectiles().add(s7);
            Play.getEnemyProjectiles().add(s8);
            Play.getEnemyProjectiles().add(s9);
            Play.getEnemyProjectiles().add(s10);
            Play.getEnemyProjectiles().add(s11);
            Play.getEnemyProjectiles().add(s12);
        }
        else {
            Play.getProjectiles().add(s1);
            Play.getProjectiles().add(s2);
            Play.getProjectiles().add(s3);
            Play.getProjectiles().add(s4);
            Play.getProjectiles().add(s5);
            Play.getProjectiles().add(s6);
            Play.getProjectiles().add(s7);
            Play.getProjectiles().add(s8);
            Play.getProjectiles().add(s9);
            Play.getProjectiles().add(s10);
            Play.getProjectiles().add(s11);
            Play.getProjectiles().add(s12);
        }
        return new LaserBullet(x1, y1, x2, y2, dmg, Origin, this);
    }
}
