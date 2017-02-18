package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Player;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import java.util.Arrays;

/**
 * Created by Taylor on 2/18/2017.
 */
public class AOEeffect extends Projectile {
    float stateTime;
    int distance;
    int endDist;
    int count;
    int count1;
    Player.FACE dir;
    final int NUM = 8; // MUST BE EVEN;
    boolean [][]aoe;
    pos [][]AOE;
    public AOEeffect(float x1, float y1, Player.FACE dir, float dmg, int distance, Item fromItem, int count1){
        aoe = new boolean[NUM][NUM];
        for(int i = 0; i < NUM; i++)
            Arrays.fill(aoe[i], false);
        aoe[NUM/2][NUM/2] = true; // WHERE YOU ARE
        AOE = new pos[NUM][NUM];

        this.fromItem = fromItem;
        this.count1 = count1;
        this.dmg = dmg;
        this.dir = dir;
        this.distance = distance;
        count = 8;
        endDist = 151 - distance;
        sprite = new Sprite(spriteTextures.basic64);
        stateTime = 0f;
        float left = x - (32 * NUM/2);
        float top = y - (left - x);
        for(int i = 0; i < NUM; i++)
            for(int j = 0; j < NUM; j++)
                AOE[i][j] = new pos(left - i*32, top - j*32);

    }

    public void expand(){
        boolean [][] cpy = aoe.clone();
        if(!aoe[0][NUM/2])
        for(int i = 0; i < NUM; i++)
            for(int j = 0; j < NUM; j++){
                if( (j < (NUM-1)) && cpy[i][j+1]) { // BELOW
                    aoe[i][j] = true;
                }
                else if((i > 0) && cpy[i-1][j]) { // LEFT
                    aoe[i][j] = true;
                }
                else if((i < (NUM-1)) && cpy[i+1][j]){ // RIGHT
                    aoe[i][j] = true;
                }
                else if((j > 0) && cpy[i][j-1]){ //ABOVE
                    aoe[i][j] = true;
                }
            }
    }
    @Override
    public void contact() {

        fromItem.setItemXP(fromItem.getItemXP() + 1);
        /** Checks for item Level Up */
        if(fromItem.getItemXP() >= fromItem.getXPtoLVL()){
            fromItem.LVLup();
            fromItem.setXPtoLVL(fromItem.getXPtoLVL() * 2);
            System.out.println("ITEM LEVELED UP");
        }


    }

    @Override
    public void draw() {
        distance ++;
        stateTime += Gdx.graphics.getDeltaTime();

        for(int i = 0; i < NUM; i++){
            for(int j = 0; j < NUM; j++){
                if(aoe[i][j])
                Play.getProjectiles().add(new singleMagicStrike(AOE[i][j].getPOSX(), AOE[i][j].getPOSY(), dir, dmg, distance, fromItem, count1, stateTime));
            }
        }
        if(((int)distance % (int)count == 0)){
            expand();
        }
        if(distance > endDist){
            remove();
        }
    }
}

class pos{
    float X;
    float Y;
    public pos(float x, float y){
        X = x;
        Y = y;
    }
    public float getPOSX(){
        return X;
    }
    public float getPOSY(){
        return Y;
    }
}
