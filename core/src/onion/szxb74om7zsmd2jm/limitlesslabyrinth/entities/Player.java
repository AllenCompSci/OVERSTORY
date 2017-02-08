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
    private static int xp = 0;
    private static int level = 1;
    private static int xpToLevel = 10;
    //private String state = "still";
    private float elapsedTime;
    private static int waveAmount = 1000;
    public enum FACE{UP, DOWN, LEFT, RIGHT};
    public Sprite front, back, left, right;
    public static FACE charFace;
    @Override
    public void setDmg(float dmg) {
        super.setDmg(dmg * (1 + ((10 * level) - 10)));
    }

    private Animation playerWalkingDown;
    private Animation playerWalkingLeft;
    private Animation playerWalkingRight;
    private Animation playerWalkingUp;

    public Player(float x, float y, int level, TiledMapTileLayer collisionLayer){
        super(x, y, level, collisionLayer);
        this.sprite = new Sprite(new Texture("knight/knightstanding.png"));
         charFace = FACE.DOWN;
        front = new Sprite(new Texture("front.png"));
        back = new Sprite(new Texture("back.png"));
        left = new Sprite(new Texture("left.png"));
        right = new Sprite(new Texture("right.png"));

        /* UNCOMMENT FOR Mr. Hudson smiles.
        this.sprite = front;
        */
        this.health = 100f;
        this.fullHealth = health;
        this.dmg = pl.getGui().getEquipped().getDmg();
        this.collisionLayer = collisionLayer;
        playerWalkingDown = Play.fourFrameAnimationCreator("knight/KnightWalking.png",2,2);
        sprite.setPosition(sprite.getWidth() * x, sprite.getHeight() * y);
    }

    @Override
    public void draw(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        charFace = FACE.UP;
        move();
        //spriteFace();
        if(charFace == FACE.DOWN)
        {
            batch.draw((TextureRegion) playerWalkingDown.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        }
        else
        {
            sprite.draw(batch);
        }

        //Starts a new wave of enemies only when all the enemies of the last wave have been killed
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && pl.getEnemies().size == 0){
            pl.setSpawnCount(waveAmount);
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
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pl.getGui().getEquipped().getType() == "projectile" && !pl.getGui().getIsRefreshing()[pl.getGui().getSelected()]){
            pl.getProjectiles().add(pl.getGui().getEquipped().getProjectile(sprite.getX() + sprite.getWidth()/4, sprite.getY() + sprite.getHeight()/4, pl.getPlayer().getSprite().getX() + (Gdx.input.getX() - Gdx.graphics.getWidth()/2), pl.getPlayer().getSprite().getY() - (Gdx.input.getY() - Gdx.graphics.getHeight()/2)));
            pl.getGui().getRefreshItem()[pl.getGui().getSelected()].setScale(1f);
           // pl.getGui().setIsRefreshing(true, pl.getGui().getSelected());
        }
    }
    
     public void spriteFace(){
        float tempX = sprite.getX();
        float tempY = sprite.getY();
        if(charFace == FACE.LEFT)
        {
            this.sprite = left;
        }
        else if(charFace == FACE.RIGHT)
            this.sprite = right;
        else if(charFace == FACE.UP)
            this.sprite = back;
        else
            this.sprite = front;
        sprite.setPosition(tempX, tempY);
    }
}

