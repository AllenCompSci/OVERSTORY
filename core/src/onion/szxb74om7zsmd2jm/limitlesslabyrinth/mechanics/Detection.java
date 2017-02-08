package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.Gdx;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
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
    Play play = new Play();

    public Detection(float x, float y, float width, float height, double radius){
        this.x = x + width/2;
        this.y = y + height/2;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    /** Checks whether player is within the radius of the entity */
    public boolean isInRadius(Enemy enemy){
        distance = Math.sqrt(Math.pow((enemy.getSprite().getX() + enemy.getSprite().getWidth()/2) - (play.getPlayer().getSprite().getX() + play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((enemy.getSprite().getY() + enemy.getSprite().getHeight()/2) - (play.getPlayer().getSprite().getY() + play.getPlayer().getSprite().getHeight()/2), 2));
        //if(radius >= distance)Gdx.app.log("IN RANGE", "SUCCESS");
        return radius >= distance;
    }

    public boolean isInSmallRadius(Enemy enemy){
        distance = Math.sqrt(Math.pow((enemy.getSprite().getX() + enemy.getSprite().getWidth()/2) - (play.getPlayer().getSprite().getX() + play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((enemy.getSprite().getY() + enemy.getSprite().getHeight()/2) - (play.getPlayer().getSprite().getY() + play.getPlayer().getSprite().getHeight()/2), 2));
        //if(radius >= distance)Gdx.app.log("IN RANGE", "SUCCESS");
        return (radius/3) >= distance;
    }

    public boolean isInBigRadius(Enemy enemy){
        distance = Math.sqrt(Math.pow((enemy.getSprite().getX() + enemy.getSprite().getWidth()/2) - (play.getPlayer().getSprite().getX() + play.getPlayer().getSprite().getWidth()/2), 2) + Math.pow((enemy.getSprite().getY() + enemy.getSprite().getHeight()/2) - (play.getPlayer().getSprite().getY() + play.getPlayer().getSprite().getHeight()/2), 2));
        //if(radius >= distance)Gdx.app.log("IN RANGE", "SUCCESS");
        return (radius*10) <= distance;
    }

    public boolean isProjectileInRadius(Enemy enemy){
        for(Projectile i : play.getProjectiles()){
            distance = Math.sqrt(Math.pow((i.getSprite().getX() + i.getSprite().getWidth()/2) - (enemy.getSprite().getX() + enemy.getSprite().getWidth()/2), 2) +
                    Math.pow((i.getSprite().getY() + i.getSprite().getHeight()/2) - (enemy.getSprite().getY() + enemy.getSprite().getHeight()/2), 2));
            if (radius/3 >= distance - i.getSprite().getHeight()/2){
                /** If projectile hits enemy, runs whatever happens in projectiles contact function */
                i.contact();
                return true;
            }
        }
        return false;
    }

    public float projectileInRadiusDmg(Enemy enemy){
        for(Projectile i : play.getProjectiles()){
            distance = Math.sqrt(Math.pow((i.getSprite().getX() + i.getSprite().getWidth()/2) - (enemy.getSprite().getX() + enemy.getSprite().getWidth()/2), 2) +
                    Math.pow((i.getSprite().getY() + i.getSprite().getHeight()/2) - (enemy.getSprite().getY() + enemy.getSprite().getHeight()/2), 2));
            if (radius/3 >= distance - i.getSprite().getHeight()/2){
                /** If projectile hits enemy, runs whatever happens in projectiles contact function */
                return i.getDmg();
            }
        }
        return 0;
    }
}
