package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.LaserGun;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.invisProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Dialog.Conversation;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Dialog.ConversationChoice;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Dialog.ConversationGraph;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Pathfinding;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.MusicDirector;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by chris on 1/19/2017.
 */
public class Player extends Entity {
    public void setXp(int xp) {
        this.xp = xp;
    }
    public static int getXp() {
        return xp;
    }
    private static int xp = 0;
    private static double addedRegen = 0;
    private static int level = 1;
    public static int getpLevel(){
        return level;
    }

    public static int getXpToLevel() {
        return xpToLevel;
    }

    private static int xpToLevel = 10;

    public static void addRegenRate() {
        addedRegen += .005;
        Player.regenRate = (float)((.02 * fullHealth) + (addedRegen * fullHealth));
    }

    private static float regenRate = 30;
    public static float getRegenRate(){
        return regenRate;
    }


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

    public static boolean leveledup = false;

    static Hashtable<String, Conversation> _conversations;
    static ConversationGraph _graph;
    static String quit = "q";
    static String _input = "";

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
        regenRate = 1;
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
    try {
        sprite.setPosition(collisionLayer.getTileWidth() * Play.getPlayerPOS()[0][0], collisionLayer.getTileHeight() * Play.getPlayerPOS()[0][1]);
    } catch(ArrayIndexOutOfBoundsException ex){
        System.out.println(ex.getMessage());
        throw ex;
    }
        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
    }

    public void establishHealth(){
        this.health = 100f;
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

        /**checks whether xp is enough to level up*/
        if(xpToLevel - xp <= 0){
            Skin s = new Skin();
            //s.ad
            level++;
            dmg += level;
            xpToLevel = ((int)((Math.pow(level, 2) * 40)));
            fullHealth *= 1.51;
            health = fullHealth;
            regenRate = (float)((.02 * health) + (addedRegen * health));
            Play.getGui().refillHealth();
            leveledup = true;
            Gdx.app.log("Level", String.valueOf(level));
           // Dialog d = new Dialog("Level", )
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.I))
        {
            //testConversation();
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

    public void testConversation()
    {


        _conversations = new Hashtable<String, Conversation>();

        Conversation start = new Conversation();
        start.setId("500");
        start.setDialog("Are memes cool yet?");

        Conversation yesAnswer = new Conversation();
        yesAnswer.setId("501");
        yesAnswer.setDialog("Awesome! :D");

        Conversation noAnswer = new Conversation();
        noAnswer.setId("502");
        noAnswer.setDialog("That's too bad. :(");

        _conversations.put(start.getId(), start);
        _conversations.put(yesAnswer.getId(), yesAnswer);
        _conversations.put(noAnswer.getId(), noAnswer);

        _graph = new ConversationGraph(_conversations, start.getId());

        ConversationChoice yesChoice = new ConversationChoice();
        yesChoice.setSourceId(start.getId());
        yesChoice.setDestinationId(yesAnswer.getId());
        yesChoice.setChoicePhrase("Y");

        ConversationChoice noChoice = new ConversationChoice();
        yesChoice.setSourceId(start.getId());
        yesChoice.setDestinationId(noAnswer.getId());
        yesChoice.setChoicePhrase("N");

        ConversationChoice startChoice01 = new ConversationChoice();
        startChoice01.setSourceId(yesAnswer.getId());
        startChoice01.setDestinationId(start.getId());
        startChoice01.setChoicePhrase("Go to beginning!");

        ConversationChoice startChoice02 = new ConversationChoice();
        startChoice02.setSourceId(noAnswer.getId());
        startChoice02.setDestinationId(start.getId());
        startChoice02.setChoicePhrase("Go to beginning!");

        System.out.println(_graph.toString());
        //System.out.println(_graph.displayCurrentConversation());
        //System.out.println(_graph.toJson());

        while(!_input.equalsIgnoreCase(quit))
        {
            Conversation conversation = getNextChoice(_graph);
            if(conversation == null)
            {
                continue;
            }
            _graph.setCurrentConversation(conversation.getId());
            System.out.println("Something happened");
            System.out.println(_graph.displayCurrentConversation());

        }




    }

    public static Conversation getNextChoice(ConversationGraph g) {
        ArrayList<ConversationChoice> choices = g.getCurrentChoices();
        for (ConversationChoice choice : choices) {
            //System.out.println(choice.getDestinationId() + " " + choice.getChoicePhrase());
        }
        System.out.println("Input: ");
        //_input = System.console().readLine();
        Scanner scanner = new Scanner(System.in);
        _input = scanner.next();

        Conversation choice = null;
        try {
            choice = g.getConversationByID(_input);
        } catch (NumberFormatException nfe) {
            return null;
        }
        return choice;
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

    public static void takeDMG(float dmg){
        Play.getPlayer().setHealth(Play.getPlayer().getHealth() - dmg);
        Play.getGui().setHealthBarX(Play.getGui().getHealthBarX() + ((dmg / Play.getPlayer().getFullHealth()) * Play.getGui().getPlayerHealthBar().getWidth()) / 2);
        Play.getGui().getPlayerHealthBar().setScale(Play.getGui().getPlayerHealthBar().getScaleX() - dmg / Play.getPlayer().getFullHealth(), Play.getGui().getPlayerHealthBar().getScaleY());
        //playerSounds.playSound(MusicDirector.SoundName.PLAYERHIT);
    }

    public static void giveHealth(float health){
        Play.getPlayer().setHealth(Play.getPlayer().getHealth() + health);
        Play.getGui().setHealthBarX(Play.getGui().getHealthBarX() - ((health / Play.getPlayer().getFullHealth()) * Play.getGui().getPlayerHealthBar().getWidth()) / 2);
        Play.getGui().getPlayerHealthBar().setScale(Play.getGui().getPlayerHealthBar().getScaleX() + health / Play.getPlayer().getFullHealth(), Play.getGui().getPlayerHealthBar().getScaleY());
    }

    public static void increaseHealth(){
        fullHealth *= 5;
        health = fullHealth;
        regenRate = (float)((.02 * health) + (addedRegen * health));
        Play.getGui().refillHealth();
    }
}

