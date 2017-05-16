package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.*;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.Mine;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.TurretItem;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by chris on 2/1/2017.
 */
public class Backpack {
    private int pages = 1;
    private Sprite[] slots = new Sprite[16 * pages];
    private static int pageOn = 1;
    private Item[] itemSlots = new Item[16 * pages];
    private Item tempItem;
    private Texture ItemBox = new Texture("itemBox.png");
    private Texture SelectedBox = new Texture("selectedBox.png");
    private static int selectedSlot = 0;
    private boolean worked;
    private static BitmapFont font = new BitmapFont();

    public void reset(){
        selectedSlot = 0;
        for(int i = 0; i < slots.length; i++){
            slots[i] = new Sprite(ItemBox);
            itemSlots[i] = new NullWeapon();
        }
        slots[0] = new Sprite(SelectedBox);
    }

    public Backpack(){
        slots[0] = new Sprite(SelectedBox);
        for(int i = 0; i < slots.length; i++){
            slots[i] = new Sprite(ItemBox);
            itemSlots[i] = new NullWeapon();
        }

    }

    public void input(){
        /** Changes which slot is selected */
        if(Play.backpackdn){
            selectedSlot += 2;
            if(selectedSlot > slots.length - 1) selectedSlot -= slots.length;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
        }
        if(Play.backpackup){
            selectedSlot -= 2;
            if(selectedSlot < 0) selectedSlot += slots.length;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
        }
        if(Play.switchslot && selectedSlot % 2 == 1){
            selectedSlot -= 1;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
            Play.switchslot = false;
        }
        if(Play.switchslot && selectedSlot % 2 == 0){
            selectedSlot += 1;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
            Play.switchslot = false;
        }

        if(Play.backpackup) Play.backpackup = false;
        if(Play.backpackdn) Play.backpackdn = false;

        /** Switches backpack selected slot with hot bar item */
        if(Play.switchitem && Play.getGui().getSelected() == 0){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem1(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem1());
            Play.getPlayer().setDmg(Play.getGui().getItem1().getDmg());

        }
        if(Play.switchitem && Play.getGui().getSelected() == 1){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem2(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem2());
            Play.getPlayer().setDmg(Play.getGui().getItem2().getDmg());

        }
        if(Play.switchitem && Play.getGui().getSelected() == 2){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem3(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem3());
            Play.getPlayer().setDmg(Play.getGui().getItem3().getDmg());

        }
        if(Play.switchitem && Play.getGui().getSelected() == 3){
            tempItem = itemSlots[selectedSlot];
            itemSlots[selectedSlot] = Play.getGui().getEquipped();
            Play.getGui().setItem4(tempItem);
            Play.getGui().setEquipped(Play.getGui().getItem4());
            Play.getPlayer().setDmg(Play.getGui().getItem4().getDmg());

        }
        if (Play.switchitem) Play.switchitem = false;

        /** Delete unwanted items */

        if(Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)){
            itemSlots[selectedSlot] = new NullWeapon();
        }
    }

    public void draw(){

        if(selectedSlot >= slots.length / pages * pageOn) pageOn++;
        if(selectedSlot < 0 + slots.length / pages * pageOn - slots.length / pages) pageOn--;

        /** Display the page number */
        font.setColor(Color.WHITE);
        font.draw(Play.getRenderer().getBatch(), "Page " + pageOn + " / " + pages, Play.getCamera().position.x + Play.getCamera().viewportWidth/2 - 140, Play.getCamera().position.y + Play.getCamera().viewportHeight/2 - 385);


        /** Weapon Stats */
        font.setColor(Color.WHITE);
        font.draw(Play.getRenderer().getBatch(), "Weapon Level : " + itemSlots[selectedSlot].getLvl(), Play.getCamera().position.x - Play.getCamera().viewportWidth/2 + 40, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 140);
        font.draw(Play.getRenderer().getBatch(), "Weapon Damage : " + itemSlots[selectedSlot].getDmg(), Play.getCamera().position.x - Play.getCamera().viewportWidth/2 + 40, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 160);
        font.draw(Play.getRenderer().getBatch(), "Weapon XP : " + itemSlots[selectedSlot].getItemXP() + " / " + itemSlots[selectedSlot].getXPtoLVL(), Play.getCamera().position.x - Play.getCamera().viewportWidth/2 + 40, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 180);
        font.draw(Play.getRenderer().getBatch(), "BACKPACK SELECTED", Play.getCamera().position.x - Play.getCamera().viewportWidth/2 + 40, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 200);

        /** Draws the backpack item slots */
        slots[0 + (pageOn * slots.length / pages - slots.length/pages)].setPosition(Play.getCamera().position.x + Play.getCamera().viewportWidth/2 - 150, Play.getCamera().position.y - Play.getCamera().viewportHeight/4);
        slots[0 + (pageOn * slots.length / pages - slots.length/pages)].draw(Play.getRenderer().getBatch());
        itemSlots[0 + (pageOn * slots.length / pages - slots.length/pages)].getSprite().setPosition(slots[0 + (pageOn * slots.length / pages - slots.length/pages)].getX(), slots[0 + (pageOn * slots.length / pages - slots.length/pages)].getY());
        itemSlots[0 + (pageOn * slots.length / pages - slots.length/pages)].getSprite().draw(Play.getRenderer().getBatch());


        for(int i = 1 + (1 * slots.length / pages * pageOn - slots.length / pages); i < slots.length / pages * pageOn; i++) {
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

    public void addToBackpack(Item weapon){
        worked = false;
        for(int i = 0; i < itemSlots.length; i++){
            if(itemSlots[i].getType().equals("melee")){
                itemSlots[i] = weapon;
                worked = true;
                break;
            }
        }
        if(!worked){
            expand();
            itemSlots[itemSlots.length - 16] = weapon;
        }
    }

    public void expand(){
        pages++;
        Sprite[] tempSprite = new Sprite[slots.length + 16];
        Item[] tempItems = new Item[itemSlots.length + 16];
        System.arraycopy(slots,0,tempSprite,0,slots.length);
        System.arraycopy(itemSlots,0,tempItems,0,itemSlots.length);
        for(int i = slots.length; i < tempSprite.length; i++){
            tempSprite[i] = new Sprite(ItemBox);
            tempItems[i] = new NullWeapon();
        }
        slots = tempSprite;
        itemSlots = tempItems;
    }

}
