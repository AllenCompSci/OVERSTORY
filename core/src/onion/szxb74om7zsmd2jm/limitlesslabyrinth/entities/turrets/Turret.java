package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.turrets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Enemy;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.Entity;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.enemies.RandomEnemySpawn;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullProjectileItem;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullWeapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/12/2017.
 */
public class Turret extends Entity{
    public Item getItemHeld() {
        return itemHeld;
    }

    private Item itemHeld;
    private Item tempItem;
    private double distance = 0;
    private long AttackTime = 0;
    private float slope;
    private double theta;

    public Turret(float x, float y, Item itemHeld){
        super(x,y);
        sprite = new Sprite(spriteTextures.TurretOffSprite);
        this.itemHeld = itemHeld;
        dmg = itemHeld.getDmg();
        sprite.setPosition(x,y);
        this.collisionLayer = (TiledMapTileLayer) Play.getMap().getLayers().get(1);
    }

    @Override
    public void move() {

    }

    public void draw() {
        sprite.draw(Play.getRenderer().getBatch());
        fire();
        checkWeaponSwap();
        ONorOFF();
    }

    private void fire(){
        if(AttackTime < System.currentTimeMillis()) {
            for (Enemy i : Play.getEnemies()) {
                distance = Math.sqrt(Math.pow((i.getSprite().getX() + i.getSprite().getWidth() / 2) - (sprite.getX() + sprite.getWidth() / 2), 2) +
                        Math.pow((i.getSprite().getY() + i.getSprite().getHeight() / 2) - (sprite.getY() + sprite.getHeight() / 2), 2));
                if(distance < 1000 && (itemHeld.getType() == "projectile" || itemHeld.getType() == "rune")) {

                    slope = ((i.getSprite().getY() - sprite.getY()) / (i.getSprite().getX() - sprite.getX()));
                    theta = Math.atan(slope);
                    sprite.setRotation((float) Math.toDegrees(theta));
                    if (i.getSprite().getX() < sprite.getX()) {
                        sprite.setFlip(true, true);
                    }
                    if (i.getSprite().getX() > sprite.getX()) {
                        sprite.setFlip(false, false);
                    }
                    Play.getProjectiles().add(itemHeld.getProjectile(sprite.getX(), sprite.getY(), i.getSprite().getX() + i.getSprite().getWidth() / 2, i.getSprite().getY() + i.getSprite().getHeight() / 2, "Turret"));
                    AttackTime = (itemHeld.getCooldown() == 0) ? System.currentTimeMillis() + itemHeld.getCooldown() + 40 : System.currentTimeMillis() + itemHeld.getCooldown() + 160;
                    break;
                }
                else if(distance < 500 && itemHeld.getType() == "melee"){
                    i.takeDMG(itemHeld.getDmg());
                    AttackTime = System.currentTimeMillis() + 200;
                    break;
                }

            }
        }
    }

    private void checkWeaponSwap(){
        distance = Math.sqrt(Math.pow((Play.getPlayer().getSprite().getX() + Play.getPlayer().getSprite().getWidth() / 2) - (sprite.getX() + sprite.getWidth() / 2), 2) +
                Math.pow((Play.getPlayer().getSprite().getY() + Play.getPlayer().getSprite().getHeight() / 2) - (sprite.getY() + sprite.getHeight() / 2), 2));
        if(distance < 50 && Gdx.input.isKeyJustPressed(Input.Keys.F) && (Play.getGui().getEquipped().getType() == "projectile" || Play.getGui().getEquipped().getType() == "melee"|| Play.getGui().getEquipped().getType() == "rune")){
            tempItem = itemHeld;
            if(Play.getGui().getSelected() == 0){
                itemHeld = Play.getGui().getItem1();
                Play.getGui().setItem1(tempItem);
                Play.getGui().setEquipped(Play.getGui().getItem1());
            }
            if(Play.getGui().getSelected() == 1){
                itemHeld = Play.getGui().getItem2();
                Play.getGui().setItem2(tempItem);
                Play.getGui().setEquipped(Play.getGui().getItem2());
            }
            if(Play.getGui().getSelected() == 2){
                itemHeld = Play.getGui().getItem3();
                Play.getGui().setItem3(tempItem);
                Play.getGui().setEquipped(Play.getGui().getItem3());
            }
            if(Play.getGui().getSelected() == 3){
                itemHeld = Play.getGui().getItem4();
                Play.getGui().setItem4(tempItem);
                Play.getGui().setEquipped(Play.getGui().getItem4());
            }
        }
    }

    private void ONorOFF(){
        if(itemHeld.getDmg() == 0){
            sprite.setTexture(spriteTextures.TurretOffSprite);
        }
        else {
            sprite.setTexture(spriteTextures.TurretOnSprite);
        }
    }
}
