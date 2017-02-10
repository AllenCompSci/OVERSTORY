package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by taylor hudson on 2/8/2017.
 */
public class Dragon extends Enemy {
    private Animation<TextureRegion> animation;
    float stateTime;
    public Dragon (float x, float y, int level, TiledMapTileLayer collisionLayer)
    {
        super(x, y, level, collisionLayer);
        this.xpDrop = determineXP(level);
        Texture spriteSheet = spriteTextures.dragonTexture;
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight() / 2);
        TextureRegion[] spriteFrames = new TextureRegion[8];
        int index = 0;
        for(int i = 0; i < 2; i++) { // rows
            for (int j = 0; j < 4; j++) { // cols
                spriteFrames [index++] = tmp[i][j];
            }
        }
        animation = new Animation<TextureRegion>(.2f, spriteFrames);
        stateTime = 0f;
        this.sprite = new Sprite(spriteTextures.dragonStandingTexture);
        this.health = determineHealth(level);
        this.fullHealth = health;
        this.dmg = 10f;
        sprite.setPosition(collisionLayer.getTileWidth() * x, collisionLayer.getTileHeight() * y);
        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
    }

    @Override
    public int determineXP(int level) {
        return 10 * level;
    }

    @Override
    public float determineHealth(int level) {
        return 500f * (float) level;
    }

    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        float x = sprite.getX();
        float y = sprite.getY();
        sprite.setPosition(x,y);
        Play.getRenderer().getBatch().draw(currentFrame, x,y);
        //sprite.draw(batch);
        healthBar.setPosition(sprite.getX() - healthBarX, sprite.getY() + sprite.getHeight());
        lostHealthBar.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight());
        lostHealthBar.draw(batch);
        healthBar.draw(batch);
        move();
        //Enemy checking for player
        if(detection.isInRadius(this)){
            //Enemy is hit
            if(Play.getGui().getEquipped().getType() == "melee" && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    //Enemy loses health and is represented on the health bar
                    health -= Play.getPlayer().getDmg();
                    healthBarX += ((Play.getPlayer().getDmg() / fullHealth) * sprite.getWidth()) / 2;
                    healthBar.setScale(healthBar.getScaleX() - Play.getPlayer().getDmg() / fullHealth, healthBar.getScaleY());
                    Play.getGui().getRefreshItem()[Play.getGui().getSelected()].setScale(1f);
                    Play.getGui().setIsRefreshing(true, Play.getGui().getSelected());
                }
            }
        }

        /** Checking if hit by projectile */
        if(detection.isProjectileInRadius(this)){
            health -= Play.getPlayer().getDmg();
            healthBarX += ((Play.getPlayer().getDmg() / fullHealth) * sprite.getWidth()) / 2;
            healthBar.setScale(healthBar.getScaleX() - Play.getPlayer().getDmg() / fullHealth, healthBar.getScaleY());
        }




    }

}
