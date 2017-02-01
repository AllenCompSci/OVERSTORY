package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private Weapon item1;
    private Weapon item2;
    private Weapon item3;
    private Weapon item4;
    private Weapon Equipped;
    private Texture HealthBar = new Texture("playerhealthbar.png");
    private Texture LostHealthBar = new Texture("playerredbar.png");
    private Texture ItemBox = new Texture("itemBox.png");
    private Texture SelectedBox = new Texture("selectedBox.png");
    private static float healthBarX = 0;
    private static Play pl = new Play();
    public Weapon getEquipped() {
        return Equipped;
    }
    public void setEquipped(Weapon equipped) {
        Equipped = equipped;
    }



    public Gui(){
        playerHealthBar = new Sprite(HealthBar);
        playerLostHealthBar = new Sprite(LostHealthBar);
        itemBox1 = new Sprite(SelectedBox);
        itemBox2 = new Sprite(ItemBox);
        itemBox3 = new Sprite(ItemBox);
        itemBox4 = new Sprite(ItemBox);
        item1 = new Fists();
        item2 = new NullWeapon();
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
    }
}
