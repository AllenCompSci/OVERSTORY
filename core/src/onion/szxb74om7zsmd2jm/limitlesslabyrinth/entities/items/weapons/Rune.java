package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Wall;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.pos;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.singleMagicStrike;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by Taylor on 2/19/2017.
 */
public class Rune extends Weapon {
    spriteTextures.RUNE rune;
    boolean [][] EFFECT;
    pos [][] AOE;
    pos CENTER;
    int NUM;
    public Rune(int level){
        rune = spriteTextures.RUNE.WILDGROWTH;
        setSprite();
        setEFFECT();
        setNUM();
        lvl = level;
        dmg = 3.3f;
        basedmg = dmg;
        for(int i = 0; i < lvl; i++){//
            dmg += 3.3 * i;
        }
        type = "rune";
        cooldown = 40;
    }
    public void switchRuneType(){
        rune = spriteTextures.getRUNETYPE();
        setSprite();
        setEFFECT();
        setNUM();
    }
    @Override
    public void SWAPVAL(){
        switchRuneType();
    }
    private void setSprite(){
        sprite = new Sprite(spriteTextures.runeSprite(rune));
    }
    private void setEFFECT() { EFFECT = spriteTextures.getARRAY(rune);}
    private void setNUM(){ NUM = EFFECT[0].length;  AOE = new pos [NUM][NUM]; }
    private void setAOE(float x, float y){
        float left = x + (32 * NUM/2);
        float top = y + (left - x);
        for(int i = 0; i < NUM; i++)
            for(int j = 0; j < NUM; j++) {
                AOE[i][j] = new pos(left - i * 32, top - j * 32);
                if(i == (NUM/2) && j == (NUM/2)){
                    CENTER = AOE[i][j];
                }
        }
    }
    private void RUNEBLAST() {
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < NUM; j++) {
                if (EFFECT[i][j]) {
                    Play.getProjectiles().add(new singleMagicStrike(AOE[i][j].getPOSX(), AOE[i][j].getPOSY(), dmg * 1.5f, this, 8, spriteTextures.RuneFX(rune)));
                }

            }
        }
    }
    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2, String Origin){
        if(rune == spriteTextures.RUNE.MAGICWALL){
            Play.addWall(new Wall(x2, y2, Play.fourFrameAnimationCreator(spriteTextures.magicwall,1,3,.2f), 30));
        }
        else if(rune == spriteTextures.RUNE.WILDGROWTH){
            Play.addWall(new Wall(x2, y2 + 32, Play.fourFrameAnimationCreator(spriteTextures.wildgrowth,1,3,.2f), 30));
        }
        else if(rune != spriteTextures.RUNE.SUDDENDEATH || rune != spriteTextures.RUNE.ICICLE) {
            setAOE(x2, y2);
            RUNEBLAST();
            return new singleMagicStrike(CENTER.getPOSX(), CENTER.getPOSY(), dmg*1.5f, this, 8, spriteTextures.RuneFX(rune));
        }
        return new singleMagicStrike(x2, y2, dmg*1.5f, this, 8, spriteTextures.RuneFX(rune));
    }

}
