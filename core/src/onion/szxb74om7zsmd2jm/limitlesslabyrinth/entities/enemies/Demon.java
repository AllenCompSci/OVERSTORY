package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;

/**
 * Created by taylor hudson on 2/8/2017.
 */
public class Demon extends Enemy {
    private Animation<TextureRegion> animation;
    float stateTime;
    public Demon(float x, float y, int level, TiledMapTileLayer collisionLayer)
    {
        super(x, y, level, collisionLayer);
        this.xpDrop = determineXP(level);
        Texture spriteSheet = new Texture(Gdx.files.internal("demon(64x64)(4col2row)(256x128).png"));
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
        this.sprite = new Sprite(new Texture("demon standing.png"));
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
        pl.getRenderer().getBatch().draw(currentFrame, x,y);
        //sprite.draw(batch);
        healthBar.setPosition(sprite.getX() - healthBarX, sprite.getY() + sprite.getHeight());
        lostHealthBar.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight());
        lostHealthBar.draw(batch);
        healthBar.draw(batch);
        move();
        //Enemy checking for player
        if(detection.isInRadius(this)){
            //Enemy is hit
            if(pl.getGui().getEquipped().getType() == "melee" && !pl.getGui().getIsRefreshing()[pl.getGui().getSelected()]) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    //Enemy loses health and is represented on the health bar
                    health -= pl.getPlayer().getDmg();
                    healthBarX += ((pl.getPlayer().getDmg() / fullHealth) * sprite.getWidth()) / 2;
                    healthBar.setScale(healthBar.getScaleX() - pl.getPlayer().getDmg() / fullHealth, healthBar.getScaleY());
                    pl.getGui().getRefreshItem()[pl.getGui().getSelected()].setScale(1f);
                    pl.getGui().setIsRefreshing(true, pl.getGui().getSelected());
                }
            }
        }

        /** Checking if hit by projectile */
        if(detection.isProjectileInRadius(this)){
            health -= pl.getPlayer().getDmg();
            healthBarX += ((pl.getPlayer().getDmg() / fullHealth) * sprite.getWidth()) / 2;
            healthBar.setScale(healthBar.getScaleX() - pl.getPlayer().getDmg() / fullHealth, healthBar.getScaleY());
        }




    }
}