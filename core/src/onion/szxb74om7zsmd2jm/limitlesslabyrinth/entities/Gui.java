package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Bow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Fists;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullWeapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by 226812 on 1/27/2017.
 */
public class Gui {
    private Sprite playerHealthBar;
    private Sprite playerLostHealthBar;
    private Sprite itemBox1;
    private Sprite itemBox2;
    private Sprite itemBox3;
    private Sprite itemBox4;
    private static Item item1;
    private static Item item2;
    private static Item item3;
    private static Item item4;
    private static Item Equipped;
    private Texture HealthBar = new Texture("playerhealthbar.png");
    private Texture LostHealthBar = new Texture("playerredbar.png");
    private Texture ItemBox = new Texture("itemBox.png");
    private Texture SelectedBox = new Texture("selectedBox.png");
    private Backpack backpack = new Backpack();
    private static float healthBarX = 0;
    private static boolean isBackpackOpen = false;
    private static Play pl = new Play();
    public Item getEquipped() {
        return Equipped;
    }
    public void setEquipped(Item equipped) {
        Equipped = equipped;
    }
    public boolean getIsBackpackOpen() {
        return isBackpackOpen;
    }
    public void setIsBackpackOpen(boolean isBackpackOpen) {
        this.isBackpackOpen = isBackpackOpen;
    }
    public Item getItem1() {
        return item1;
    }
    public void setItem1(Item item1) {
        Gui.item1 = item1;
    }
    public Item getItem2() {
        return item2;
    }
    public void setItem2(Item item2) {
        Gui.item2 = item2;
    }
    public Item getItem3() {
        return item3;
    }
    public void setItem3(Item item3) {
        Gui.item3 = item3;
    }
    public Item getItem4() {
        return item4;
    }
    public void setItem4(Item item4) {
        Gui.item4 = item4;
    }

    public Gui(){
        playerHealthBar = new Sprite(HealthBar);
        playerLostHealthBar = new Sprite(LostHealthBar);
        itemBox1 = new Sprite(SelectedBox);
        itemBox2 = new Sprite(ItemBox);
        itemBox3 = new Sprite(ItemBox);
        itemBox4 = new Sprite(ItemBox);
        item1 = new Fists();
        item2 = new Bow();
        item3 = new NullWeapon();
        item4 = new NullWeapon();
        Equipped = item1;
    }

    public void input(){
        /** Switching between item boxes */
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            itemBox1 = new Sprite(SelectedBox);
            itemBox2 = new Sprite(ItemBox);
            itemBox3 = new Sprite(ItemBox);
            itemBox4 = new Sprite(ItemBox);
            Equipped = item1;
            pl.getPlayer().setDmg(Equipped.getDmg());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            itemBox2 = new Sprite(SelectedBox);
            itemBox1 = new Sprite(ItemBox);
            itemBox3 = new Sprite(ItemBox);
            itemBox4 = new Sprite(ItemBox);
            Equipped = item2;
            pl.getPlayer().setDmg(Equipped.getDmg());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            itemBox3 = new Sprite(SelectedBox);
            itemBox2 = new Sprite(ItemBox);
            itemBox1 = new Sprite(ItemBox);
            itemBox4 = new Sprite(ItemBox);
            Equipped = item3;
            pl.getPlayer().setDmg(Equipped.getDmg());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
            itemBox4 = new Sprite(SelectedBox);
            itemBox2 = new Sprite(ItemBox);
            itemBox3 = new Sprite(ItemBox);
            itemBox1 = new Sprite(ItemBox);
            Equipped = item4;
            pl.getPlayer().setDmg(Equipped.getDmg());
        }

        /** Backpack open / close */
        if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
            if(isBackpackOpen) {
                isBackpackOpen = false;
            }
            else {
                isBackpackOpen = true;
            }
        }


        /** Refer here to know how to remove health from player properly */
        /*if(Gdx.input.isKeyJustPressed(Input.Keys.N)){
            pl.getPlayer().setHealth(pl.getPlayer().getHealth() - 10f);
            healthBarX += ((10f / pl.getPlayer().getFullHealth()) * playerHealthBar.getWidth()) / 2;
            playerHealthBar.setScale(playerHealthBar.getScaleX() - 10f / pl.getPlayer().getFullHealth(), playerHealthBar.getScaleY());
        }*/
    }

    public void update(){

        /** Health Bar Update */
        playerHealthBar.setPosition(pl.getCamera().position.x - pl.getCamera().viewportWidth/2 + 10 - healthBarX, pl.getCamera().position.y + pl.getCamera().viewportHeight/2 - 28);
        playerLostHealthBar.setPosition(pl.getCamera().position.x - pl.getCamera().viewportWidth/2 + 10, pl.getCamera().position.y + pl.getCamera().viewportHeight/2 - 28);
        playerLostHealthBar.draw(pl.getRenderer().getBatch());
        playerHealthBar.draw(pl.getRenderer().getBatch());

        /** itemBox update */
        itemBox1.setPosition(pl.getCamera().position.x - 150, pl.getCamera().position.y - pl.getCamera().viewportHeight/2 + 70);
        itemBox2.setPosition(pl.getCamera().position.x - 50, pl.getCamera().position.y - pl.getCamera().viewportHeight/2 + 70);
        itemBox3.setPosition(pl.getCamera().position.x + 50, pl.getCamera().position.y - pl.getCamera().viewportHeight/2 + 70);
        itemBox4.setPosition(pl.getCamera().position.x + 150, pl.getCamera().position.y - pl.getCamera().viewportHeight/2 + 70);
        itemBox1.draw(pl.getRenderer().getBatch());
        itemBox2.draw(pl.getRenderer().getBatch());
        itemBox3.draw(pl.getRenderer().getBatch());
        itemBox4.draw(pl.getRenderer().getBatch());

        /** Items Update */
        item1.getSprite().setPosition(itemBox1.getX(), itemBox1.getY());
        item2.getSprite().setPosition(itemBox2.getX(), itemBox2.getY());
        item3.getSprite().setPosition(itemBox3.getX(), itemBox3.getY());
        item4.getSprite().setPosition(itemBox4.getX(), itemBox4.getY());
        item1.getSprite().draw(pl.getRenderer().getBatch());
        item2.getSprite().draw(pl.getRenderer().getBatch());
        item3.getSprite().draw(pl.getRenderer().getBatch());
        item4.getSprite().draw(pl.getRenderer().getBatch());

        /** Backpack draw */
        if(isBackpackOpen){
            backpack.input();
            backpack.draw();
        }

    }
}
