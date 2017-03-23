package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.Gdx;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Entity;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.LightningOrb;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.invisProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 1/22/2017.
 */
public class Detection {
    double radius;
    float x;
    float y;
    float width;
    float height;
    double distance;

    public Detection(float x, float y, float width, float height, double radius){
        this.x = x + width/2;
        this.y = y + height/2;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    /** Checks whether player is within the radius of the entity */
    public boolean isInRadius(Enemy enemy){
        distance = Math.sqrt(Math.pow((enemy.getSprite().getX() + enemy.getSprite().getWidth()/2) - (Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((enemy.getSprite().getY() + enemy.getSprite().getHeight()/2) - (Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight()/2), 2));
        //if(radius >= distance)Gdx.app.log("IN RANGE", "SUCCESS");
        return radius >= distance;
    }

    public boolean isInSmallRadius(Enemy enemy){
        distance = Math.sqrt(Math.pow((enemy.getSprite().getX() + enemy.getSprite().getWidth()/2) - (Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((enemy.getSprite().getY() + enemy.getSprite().getHeight()/2) - (Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight()/2), 2));
        //if(radius >= distance)Gdx.app.log("IN RANGE", "SUCCESS");
        return (radius/3) >= distance;
    }

    public boolean isInBigRadius(Enemy enemy){
        distance = Math.sqrt(Math.pow((enemy.getSprite().getX() + enemy.getSprite().getWidth()/2) - (Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((enemy.getSprite().getY() + enemy.getSprite().getHeight()/2) - (Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight()/2), 2));
        //if(radius >= distance)Gdx.app.log("IN RANGE", "SUCCESS");
        return (radius*9) >= distance;
    }

    public boolean isProjectileInRadius(Entity enemy, Projectile i){
            distance = Math.sqrt(Math.pow((i.getSprite().getX() + i.getSprite().getWidth()/2) - (enemy.getSprite().getX() + enemy.getSprite().getWidth()/2), 2) +
                    Math.pow((i.getSprite().getY() + i.getSprite().getHeight()/2) - (enemy.getSprite().getY() + enemy.getSprite().getHeight()/2), 2));
            if (radius/3 >= distance - i.getSprite().getHeight()/2){
                /** If projectile hits enemy, runs whatever happens in projectiles contact function */
                i.contact();
                return true;
            }
        return false;
    }

    public float projectileInRadiusDmg(Enemy enemy){
        for(Projectile i : Play.getProjectiles()){
            distance = Math.sqrt(Math.pow((i.getSprite().getX() + i.getSprite().getWidth()/2) - (enemy.getSprite().getX() + enemy.getSprite().getWidth()/2), 2) +
                    Math.pow((i.getSprite().getY() + i.getSprite().getHeight()/2) - (enemy.getSprite().getY() + enemy.getSprite().getHeight()/2), 2));
            if (radius/3 >= distance - i.getSprite().getHeight()/2){
                return i.getDmg();
            }
        }
        return 0;
    }

    public float projectileInRadiusDmg(Player enemy){
        for(Projectile i : Play.getEnemyProjectiles()){
            distance = Math.sqrt(Math.pow((i.getSprite().getX() + i.getSprite().getWidth()/2) - (enemy.getSprite().getX() + enemy.getSprite().getWidth()/2), 2) +
                    Math.pow((i.getSprite().getY() + i.getSprite().getHeight()/2) - (enemy.getSprite().getY() + enemy.getSprite().getHeight()/2), 2));
            if (radius/3 >= distance - i.getSprite().getHeight()/2){
                return i.getDmg();
            }
        }
        return 0;
    }

    public boolean isInvisProjectileInRadius(Entity enemy, invisProjectile i){
        distance = Math.sqrt(Math.pow((i.getSprite().getX() + i.getSprite().getWidth()/2) - (enemy.getSprite().getX() + enemy.getSprite().getWidth()/2), 2) +
                Math.pow((i.getSprite().getY() + i.getSprite().getHeight()/2) - (enemy.getSprite().getY() + enemy.getSprite().getHeight()/2), 2));
        if (radius * 3 >= distance - i.getSprite().getHeight()/2){
            /** If projectile hits enemy, runs whatever happens in projectiles contact function */
            i.contact(enemy);
            return true;
        }
        return false;
    }

}
