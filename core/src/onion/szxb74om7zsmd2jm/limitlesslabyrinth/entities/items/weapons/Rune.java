package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.singleMagicStrike;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by Taylor on 2/19/2017.
 */
public class Rune extends Weapon {
    spriteTextures.RUNE rune;
    public Rune(){
        switchRuneType();
        dmg = 100f;
        lvl = 1;
        type = "projectile";
        cooldown = 40;
    }
    private void switchRuneType(){
        rune = spriteTextures.getRUNETYPE();
        setSprite();
    }
    private void setSprite(){
        sprite = new Sprite(spriteTextures.runeSprite(rune));
    }


    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2){
        return new singleMagicStrike(x2, y2, dmg*1.5f, this, 15, spriteTextures.RuneFX(rune));
    }

}
