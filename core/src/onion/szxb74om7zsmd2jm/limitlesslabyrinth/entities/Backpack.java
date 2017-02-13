package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.NullWeapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Shuriken;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Sword;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.Mine;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.TurretItem;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/1/2017.
 */
public class Backpack {
    private Sprite[] slots = new Sprite[16];
    private Item[] itemSlots = new Item[16];
    private Item tempItem;
    private Texture ItemBox = new Texture("itemBox.png");
    private Texture SelectedBox = new Texture("selectedBox.png");
    private static int selectedSlot = 0;

    public Backpack(){
        slots[0] = new Sprite(SelectedBox);
        itemSlots[0] = new Sword();
        for(int i = 1; i < slots.length; i++){
            slots[i] = new Sprite(ItemBox);
            itemSlots[i] = new NullWeapon();
        }
        itemSlots[1] = new Mine();
        itemSlots[2] = new TurretItem();
    }

    public void input(){
        /** Changes which slot is selected */
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            selectedSlot += 2;
            if(selectedSlot > 15) selectedSlot -= 16;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            selectedSlot -= 2;
            if(selectedSlot < 0) selectedSlot += 16;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && selectedSlot % 2 == 1){
            selectedSlot -= 1;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && selectedSlot % 2 == 0){
            selectedSlot += 1;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
        }

        /** Switches backpack selected slot with hot bar item */
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem1(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem1());
            Play.getPlayer().setDmg(Play.getGui().getItem1().getDmg());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem2(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem2());
            Play.getPlayer().setDmg(Play.getGui().getItem2().getDmg());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem3(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem3());
            Play.getPlayer().setDmg(Play.getGui().getItem3().getDmg());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem4(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem4());
            Play.getPlayer().setDmg(Play.getGui().getItem4().getDmg());
        }

        /** Delete unwanted items */

        if(Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)){
            itemSlots[selectedSlot] = new NullWeapon();
        }
    }

    public void draw(){
        /** Draws the backpack item slots */
        slots[0].setPosition(Play.getCamera().position.x + Play.getCamera().viewportWidth/2 - 150, Play.getCamera().position.y - Play.getCamera().viewportHeight/4);
        slots[0].draw(Play.getRenderer().getBatch());
        itemSlots[0].getSprite().setPosition(slots[0].getX(), slots[0].getY());
        itemSlots[0].getSprite().draw(Play.getRenderer().getBatch());
        for(int i = 1; i < slots.length; i++) {
            if (i % 2 == 1) {
                slots[i].setPosition(slots[i - 1].getX() + 51, slots[i - 1].getY());
                slots[i].draw(Play.getRenderer().getBatch());
                itemSlots[i].getSprite().setPosition(slots[i].getX(), slots[i].getY());
                itemSlots[i].getSprite().draw(Play.getRenderer().getBatch());
            } else {
                slots[i].setPosition(slots[i - 2].getX(), slots[i - 2].getY() + 51);
                slots[i].draw(Play.getRenderer().getBatch());
                itemSlots[i].getSprite().setPosition(slots[i].getX(), slots[i].getY());
                itemSlots[i].getSprite().draw(Play.getRenderer().getBatch());
            }
        }
    }

}
