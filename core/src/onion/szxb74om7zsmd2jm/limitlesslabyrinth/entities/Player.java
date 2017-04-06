package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.invisProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Pathfinding;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.MusicDirector;

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
    private static float regenRate = 10;


    public static float getFullHealth() {
        return fullHealth;
    }
    protected static float fullHealth;
    public static float getHealth() {
        return health;
    }
    public static void setHealth(float health) {
        Player.health = health;
    }
    protected static float health;


    //private String state = "still";
    private float elapsedTime;
    private static int waveAmount = 2;
    public enum FACE{UP, DOWN, LEFT, RIGHT};
    public Sprite front, back, left, right;
    public static FACE charFace = FACE.DOWN;
    public static FACE getCharFace(){ return charFace;}
    public static float CharX, CharY;
    public static boolean isWalking = false;
    public String playerType;
    private Sprite outfit;
    int selection;
    int NUMOUTFITS = 8;
    boolean RUNE = true;
    boolean OUTFIT = true;
    protected float dmgTaken;
    static MusicDirector playerSounds = new MusicDirector(MusicDirector.SoundName.PLAYERHIT);

    @Override
    public void setDmg(float dmg) {
        super.setDmg(dmg * (1 + ((10 * level) - 10)));
    }

    private Animation playerWalkingDown;
    private Animation playerWalkingLeft;
    private Animation playerWalkingRight;
    private Animation playerWalkingUp;

    public void reset(){
        xp = 0;
        level = 1;
        xpToLevel = 10;
        waveAmount = 200;
        establishHealth();
        isWalking = false;
    }

    public void selectOutfit(int selection){
        this.selection = selection;
        switch(3){
            case 0:
                playerType = "Conjurer/";
                break;
            case 1:
                playerType = "Evoker/";
                break;
            case 2:
                playerType = "Champion/";
                break;
            case 3:
                playerType = "Chaos Acolyte/";
                break;
            case 4:
                playerType = "Beast Tamer/";
                break;
            case 5:
                playerType ="Spirit Caller/";
                break;
            case 6:
                playerType = "Puppeteer/";
                break;
            default :
                playerType = "Death Hearld/";
                break;
        }
    }
    public void setOutfit(){
        // This is bad...aa
        front = new Sprite(new Texture("player/"+playerType+"front.png"));
        back = new Sprite(new Texture("player/"+playerType+"back.png"));
        left = new Sprite(new Texture("player/"+playerType+"left.png"));
        right = new Sprite(new Texture("player/"+playerType+"right.png"));
        playerWalkingDown = Play.fourFrameAnimationCreator("player/"+playerType+"front(2x8).png",2,8);
        playerWalkingLeft = Play.fourFrameAnimationCreator("player/"+playerType+"left(2x8).png",2,8);
        playerWalkingRight = Play.fourFrameAnimationCreator("player/"+playerType+"right(2x8).png",2,8);
        playerWalkingUp = Play.fourFrameAnimationCreator("player/"+playerType+"back(2x8).png",2,8);
    }
    public void changeOutfit(){
        selectOutfit((int)(Math.random()*NUMOUTFITS));
        setOutfit();
    }
    public void changeOutfit(int selection){
        selection = (selection+1) % NUMOUTFITS;
        selectOutfit(selection);
        setOutfit();
    }
    public Player(float x, float y, int level, TiledMapTileLayer collisionLayer){
        super(x, y, level, collisionLayer);

        this.sprite = new Sprite(new Texture("knight/knightstanding.png"));
        charFace = FACE.DOWN;
        changeOutfit();
       /*
        front = new Sprite(new Texture("front.png"));
        back = new Sprite(new Texture("back.png"));
        left = new Sprite(new Texture("left.png"));
        right = new Sprite(new Texture("right.png"));
*/
        /* UNCOMMENT FOR Mr. Hudson smiles.  */
        this.outfit = front;
        this.sprite = new Sprite(spriteTextures.basic32);

        this.dmg = Play.getGui().getEquipped().getDmg();
        this.collisionLayer = collisionLayer;
        //playerWalkingDown = Play.fourFrameAnimationCreator("knight/KnightWalking.png",2,2);
//        playerWalkingUp = Play.fourFrameAnimationCreator("knight/knightwalkingup.png", 2, 2);

    sprite.setPosition(collisionLayer.getTileWidth() * Play.getPlayerPOS()[0][0], collisionLayer.getTileHeight() * Play.getPlayerPOS()[0][1]);

        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
    }

    public void establishHealth(){
        this.health = 1000f;
        this.fullHealth = health;
    }

    @Override
    public void draw(Batch batch) {
        if(health < fullHealth){
            giveHealth(regenRate);
        }
        if(health > fullHealth){
            takeDMG(health - fullHealth);
        }

        elapsedTime += Gdx.graphics.getDeltaTime();
        //sprite.draw(batch);
        //charFace = FACE.DOWN;
        move();
        updatePOS();
        spriteFace();

        if(isWalking) {
            if (charFace == FACE.DOWN) {
                batch.draw((TextureRegion) playerWalkingDown.getKeyFrame(elapsedTime, true), sprite.getX() - collisionLayer.getTileWidth(), sprite.getY());
            } else if (charFace == FACE.UP) {
                batch.draw((TextureRegion) playerWalkingUp.getKeyFrame(elapsedTime, true), sprite.getX() - collisionLayer.getTileWidth(), sprite.getY());
            } else if (charFace == FACE.LEFT) {
                batch.draw((TextureRegion) playerWalkingLeft.getKeyFrame(elapsedTime, true), sprite.getX() - collisionLayer.getTileWidth(), sprite.getY());
            } else if (charFace == FACE.RIGHT) {
                batch.draw((TextureRegion) playerWalkingRight.getKeyFrame(elapsedTime, true), sprite.getX() - collisionLayer.getTileWidth(), sprite.getY());
            }
        }
        else
        {
            outfit.draw(batch);
        }

        //checks whether xp is enough to level up
        if(xpToLevel - xp <= 0){
            level++;
            dmg += level;
            xpToLevel *= 2;
            Gdx.app.log("Level", String.valueOf(level));
        }

        /** Fire projectile */
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && (Play.getGui().getEquipped().getType() == "projectile" || Play.getGui().getEquipped().getType() == "rune"  || Play.getGui().getEquipped().getType() == "trap") && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]){
            Play.getProjectiles().add(Play.getGui().getEquipped().getProjectile(sprite.getX() + sprite.getWidth()/4, sprite.getY() + sprite.getHeight()/4, Play.getPlayer().getSprite().getX() + (Gdx.input.getX() - Gdx.graphics.getWidth()/2), Play.getPlayer().getSprite().getY() - (Gdx.input.getY() - Gdx.graphics.getHeight()/2), "Player"));
            Play.getGui().getRefreshItem()[Play.getGui().getSelected()].setScale(1f);
            Play.getGui().setIsRefreshing(true, Play.getGui().getSelected());
        }

        /** Places Turret */
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Play.getGui().getEquipped().getType() == "turret" && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]){
            Play.getTurrets().get(Play.getMapPath()).add(Play.getGui().getEquipped().placeTurret(sprite.getX() + sprite.getWidth()/2,sprite.getY()));
            Play.getGui().getRefreshItem()[Play.getGui().getSelected()].setScale(1f);
            Play.getGui().setIsRefreshing(true, Play.getGui().getSelected());
            //new Pathfinding();// TEST ASTAR Not ready yet
        }
        /** PRESS R to ROTATE RUNE **/
        if(Gdx.input.isKeyPressed(Input.Keys.R) && (Play.getGui().getEquipped().getType() == "rune" && !Play.getGui().getIsRefreshing()[Play.getGui().getSelected()]) && RUNE){
            Play.getGui().getEquipped().SWAPVAL();
            RUNE = false;
        }
        /** PRESS O to CYCLE THRU OUTFITS **/
        if(Gdx.input.isKeyJustPressed(Input.Keys.O)){
            changeOutfit(selection);
        }
        if(!RUNE && !Gdx.input.isKeyPressed(Input.Keys.R)){
            RUNE = true;
        }

        /** Checking if hit by projectile */

        dmgTaken = detection.projectileInRadiusDmg(this);

        for(Projectile i : Play.getEnemyProjectiles()){
            if(i.getName() == "invis"){
                if (!i.getPlayersHit().contains(this, true)) {
                    if (detection.isInvisProjectileInRadius(this, (invisProjectile) i)) {
                        i.getPlayersHit().add(this);
                        health -= dmgTaken;
                        Play.getGui().setHealthBarX(Play.getGui().getHealthBarX() + ((dmgTaken / fullHealth) * sprite.getWidth()) / 2);
                        Play.getGui().getPlayerHealthBar().setScale(Play.getGui().getPlayerHealthBar().getScaleX() - dmgTaken / fullHealth, Play.getGui().getPlayerHealthBar().getScaleY());
                    }
                }
            }
            else {
                if (!i.getPlayersHit().contains(this, true)) {
                    if (detection.isProjectileInRadius(this, i)) {
                        i.getPlayersHit().add(this);
                        takeDMG(dmgTaken);
                    }
                }
            }
        }
    }
    
     public void spriteFace(){

        if(charFace == FACE.LEFT)
        {
            this.outfit = left;
        }
        else if(charFace == FACE.RIGHT)
            this.outfit = right;
        else if(charFace == FACE.UP)
            this.outfit = back;
        else
            this.outfit = front;
        sprite.setPosition(CharX, CharY);
         outfit.setPosition(CharX - collisionLayer.getTileWidth(), CharY);
    }
    private void updatePOS(){
        CharX = sprite.getX();
        CharY = sprite.getY();
    }

    public void takeDMG(float dmg){
        Play.getPlayer().setHealth(Play.getPlayer().getHealth() - dmg);
        Play.getGui().setHealthBarX(Play.getGui().getHealthBarX() + ((dmg / Play.getPlayer().getFullHealth()) * Play.getGui().getPlayerHealthBar().getWidth()) / 2);
        Play.getGui().getPlayerHealthBar().setScale(Play.getGui().getPlayerHealthBar().getScaleX() - dmg / Play.getPlayer().getFullHealth(), Play.getGui().getPlayerHealthBar().getScaleY());
        //playerSounds.playSound(MusicDirector.SoundName.PLAYERHIT);
    }

    public void giveHealth(float health){
        Play.getPlayer().setHealth(Play.getPlayer().getHealth() + health);
        Play.getGui().setHealthBarX(Play.getGui().getHealthBarX() - ((health / Play.getPlayer().getFullHealth()) * Play.getGui().getPlayerHealthBar().getWidth()) / 2);
        Play.getGui().getPlayerHealthBar().setScale(Play.getGui().getPlayerHealthBar().getScaleX() + health / Play.getPlayer().getFullHealth(), Play.getGui().getPlayerHealthBar().getScaleY());
    }
}

