package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g3d.model.*;
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
    private static int waveAmount = 200;
    public enum FACE{UP, DOWN, LEFT, RIGHT};
    public Sprite front, back, left, right;
    public static FACE charFace;
    public static float CharX, CharY;
    public static boolean isWalking = false;
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
       /*
        front = new Sprite(new Texture("front.png"));
        back = new Sprite(new Texture("back.png"));
        left = new Sprite(new Texture("left.png"));
        right = new Sprite(new Texture("right.png"));
*/
       front = new Sprite(new Texture("player/frontPurple.png"));
        back = new Sprite(new Texture("player/backPurple.png"));
        left = new Sprite(new Texture("player/leftPurple.png"));
        right = new Sprite(new Texture("player/rightPurple.png"));
        /* UNCOMMENT FOR Mr. Hudson smiles.  */
        this.sprite = front;

        this.health = 100f;
        this.fullHealth = health;
        this.dmg = Play.getGui().getEquipped().getDmg();
        this.collisionLayer = collisionLayer;
        //playerWalkingDown = Play.fourFrameAnimationCreator("knight/KnightWalking.png",2,2);
//        playerWalkingUp = Play.fourFrameAnimationCreator("knight/knightwalkingup.png", 2, 2);
        playerWalkingDown = Play.fourFrameAnimationCreator("player/front(2x8).png",2,8);
        playerWalkingLeft = Play.fourFrameAnimationCreator("player/left(2x8).png",2,8);
        playerWalkingRight = Play.fourFrameAnimationCreator("player/right(2x8).png",2,8);
        playerWalkingUp = Play.fourFrameAnimationCreator("player/back(2x8).png",2,8);
        sprite.setPosition(sprite.getWidth() * x, sprite.getHeight() * y);

    }

    @Override
    public void draw(Batch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        //sprite.draw(batch);
        //charFace = FACE.DOWN;
        move();
        updatePOS();
        spriteFace();

        if(isWalking) {
            if (charFace == FACE.DOWN) {
                batch.draw((TextureRegion) playerWalkingDown.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
            } else if (charFace == FACE.UP) {
                batch.draw((TextureRegion) playerWalkingUp.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
            } else if (charFace == FACE.LEFT) {
                batch.draw((TextureRegion) playerWalkingLeft.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
            } else if (charFace == FACE.RIGHT) {
                batch.draw((TextureRegion) playerWalkingRight.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
            }
        }
        else
        {
            sprite.draw(batch);
        }
       /*  */
        //Starts a new wave of enemies only when all the enemies of the last wave have been killed
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && Play.getEnemies().size == 0){
            Play.setSpawnCount(waveAmount);
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
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && (Play.getGui().getEquipped().getType() == "projectile" || Play.getGui().getEquipped().getType() == "trap") && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]){
            Play.getProjectiles().add(Play.getGui().getEquipped().getProjectile(sprite.getX() + sprite.getWidth()/4, sprite.getY() + sprite.getHeight()/4, Play.getPlayer().getSprite().getX() + (Gdx.input.getX() - Gdx.graphics.getWidth()/2), Play.getPlayer().getSprite().getY() - (Gdx.input.getY() - Gdx.graphics.getHeight()/2)));
            Play.getGui().getRefreshItem()[Play.getGui().getSelected()].setScale(1f);
            Play.getGui().setIsRefreshing(true, Play.getGui().getSelected());
        }

        /** Places Turret */
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Play.getGui().getEquipped().getType() == "turret" && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]){
            Play.getTurrets().add(Play.getGui().getEquipped().placeTurret(sprite.getX() + sprite.getWidth()/2,sprite.getY()));
            Play.getGui().getRefreshItem()[Play.getGui().getSelected()].setScale(1f);
            Play.getGui().setIsRefreshing(true, Play.getGui().getSelected());
        }
    }
    
     public void spriteFace(){

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
        sprite.setPosition(CharX, CharY);
    }
    private void updatePOS(){
        CharX = sprite.getX();
        CharY = sprite.getY();
    }
}

