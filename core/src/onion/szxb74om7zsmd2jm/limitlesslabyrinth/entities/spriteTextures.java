package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.graphics.Texture;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/8/2017.
 */
public class spriteTextures {

    public static Texture explosionTexture = new Texture("explosion.png");
    public static Texture explosionSpritesTexture = new Texture("explosionSprites.png");
    public static Texture shurikenProjectileTexture = new Texture("shurikenProjectile.png");
    public static Texture ashTexture = new Texture("still1.png");
    public static Texture goblinTexture = new Texture("thor32.png");
    public static Texture bruteTexture = new Texture("brute.png");
    public static Texture demonTexture = new Texture("demon(64x64)(4col2row)(256x128).png");
    public static Texture demonStandingTexture = new Texture("demon standing.png");
    public static Texture dragonTexture = new Texture("redDragon(64x64)(4col2row)(256x128).png");
    public static Texture dragonStandingTexture = new Texture("dragon.png");
    public static Texture hydraTexture = new Texture("hydra 2col1row.png");
    public static Texture hydraStandingTexture = new Texture("Hydra.png");
    public static Texture healthBar = new Texture("greenbar.png");
    public static Texture lostHealthBar = new Texture("redbar.png");

    public static Texture LightningOrbSprites = new Texture("LightningOrb.png");
    public static Texture LightningOrb = new Texture("LightningOrbArea.png");
    public static Texture invisProjectileSprite = new Texture("invisArea.png");


    public static Texture sheet(Play.MonsterType monster){
        switch(monster){
            case DEMON:
                return demonTexture;
            case DRAGON:
                return dragonTexture;
            case HYDRA:
                return hydraTexture;

        }
        return null;
    }

    public static Texture stand(Play.MonsterType monster){
        switch(monster){
            case DEMON:
                return demonStandingTexture;
            case HYDRA:
                return hydraStandingTexture;
            case DRAGON:
                return dragonStandingTexture;
        }
        return null;
    }

}
