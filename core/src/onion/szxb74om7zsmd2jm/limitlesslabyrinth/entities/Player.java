package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Arrow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 1/19/2017.
 */
public class Player extends Entity {
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
    private int xp = 0;
    private int level = 1;
    private int xpToLevel = 10;
    //private String state = "still";
    private float elapsedTime;
    private int waveAmount = 10;
    public int getEnemiesAlive() {
        return enemiesAlive;
    }
    public void setEnemiesAlive(int enemiesAlive) {
        this.enemiesAlive = enemiesAlive;
    }
    private int enemiesAlive = 0;

    private Animation playerWalkingDown;
    private Animation playerWalkingLeft;
    private Animation playerWalkingRight;
    private Animation playerWalkingUp;

    public Player(float x, float y, int level, TiledMapTileLayer collisionLayer){
        super(x, y, level, collisionLayer);
        this.sprite = new Sprite(new Texture("knight/knightstanding.png"));
        this.health = 100f;
        this.fullHealth = health;
        this.dmg = pl.getGui().getEquipped().getDmg();
        this.collisionLayer = collisionLayer;
        playerWalkingDown = Play.fourFrameAnimationCreator("knight/knightwalking.png");
        sprite.setPosition(sprite.getWidth() * x, sprite.getHeight() * y);
    }

    @Override
    public void draw(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(state.equals("down")){
            batch.draw((TextureRegion) playerWalkingDown.getKeyFrame(elapsedTime, true), (float)sprite.getX(), (float)sprite.getY());
        }
        if(state.equals("normal")) {
            sprite.draw(batch);
        }
        move();

        //Starts a new wave of enemies only when all the enemies of the last wave have been killed
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && enemiesAlive == 0){
            pl.setSpawnCount(waveAmount);
            enemiesAlive = waveAmount;
            waveAmount *= 2;
        }

        //checks whether xp is enough to level up
        if(xpToLevel - xp <= 0){
            level++;
            dmg += level;
            xpToLevel *= 2;
            Gdx.app.log("Level", String.valueOf(level));
        }

        /** Fire projectile */
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pl.getGui().getEquipped().getType() == "projectile" && !pl.getGui().getIsRefreshing()[pl.getGui().getSelected()]) {
            pl.getProjectiles().add(pl.getGui().getEquipped().getProjectile(sprite.getX() + sprite.getWidth() / 4, sprite.getY() + sprite.getHeight() / 4, pl.getPlayer().getSprite().getX() + (Gdx.input.getX() - Gdx.graphics.getWidth() / 2), pl.getPlayer().getSprite().getY() - (Gdx.input.getY() - Gdx.graphics.getHeight() / 2)));
            pl.getGui().getRefreshItem()[pl.getGui().getSelected()].setScale(1f);
            pl.getGui().setIsRefreshing(true, pl.getGui().getSelected());
        }
    }
}

