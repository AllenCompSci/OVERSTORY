package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
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
    private int pages = 10;
    private Sprite[] slots = new Sprite[16 * pages];
    private static int pageOn = 1;
    private Item[] itemSlots = new Item[16 * pages];
    private Item tempItem;
    private Texture ItemBox = new Texture("itemBox.png");
    private Texture SelectedBox = new Texture("selectedBox.png");
    private static int selectedSlot = 0;

    public void reset(){
        selectedSlot = 0;
        slots[0] = new Sprite(SelectedBox);
        itemSlots[0] = new Sword(1);
        for(int i = 1; i < slots.length; i++){
            slots[i] = new Sprite(ItemBox);
            itemSlots[i] = new NullWeapon();
        }
        itemSlots[1] = new Mine(1);
        itemSlots[2] = new TurretItem();
        itemSlots[3] = new LightningStaff(1);
        itemSlots[4] = new LightningStaff(1);
        itemSlots[5] = new LightningStaff(1);
        itemSlots[6] = new LightningStaff(1);
        itemSlots[7] = new LightningStaff(1);
        itemSlots[8] = new Rune(1);
        itemSlots[9] = new Rune(1);
        itemSlots[10] = new AOE(1);
        itemSlots[11] = new LaserGun(1);
        itemSlots[12] = new LaserGun(1);
        itemSlots[13] = new LaserGun(1);
        itemSlots[14] = new LaserGun(1);
        itemSlots[15] = new LaserGun(1);
        itemSlots[16] = new LaserGun(1);
        itemSlots[17] = new LaserGun(1);
        itemSlots[18] = new LaserGun(1);
        itemSlots[19] = new LaserGun(1);
        itemSlots[20] = new LaserGun(1);
        itemSlots[21] = new LaserGun(1);
        itemSlots[22] = new Magic(1);
        itemSlots[23] = new Rune(1);
        itemSlots[24] = new Rune(1);
        itemSlots[25] = new Rune(1);
        itemSlots[26] = new Rune(1);
        itemSlots[27] = new Rune(1);
        itemSlots[28] = new LightningStaff(1);
        itemSlots[29] = new LightningStaff(1);
        itemSlots[30] = new LightningStaff(1);
        itemSlots[31] = new LightningStaff(1);
        itemSlots[32] = new LightningStaff(1);
        itemSlots[33] = new LightningStaff(1);
        itemSlots[34] = new LightningStaff(1);
        itemSlots[35] = new LightningStaff(1);
        itemSlots[36] = new LightningStaff(1);
        itemSlots[37] = new WizardStaff(1);
        itemSlots[38] = new WizardStaff(1);
        itemSlots[39] = new WizardStaff(1);
        itemSlots[40] = new WizardStaff(1);
        itemSlots[41] = new WizardStaff(1);
        itemSlots[42] = new WizardStaff(1);
        itemSlots[43] = new WizardStaff(1);
        itemSlots[44] = new WizardStaff(1);
        itemSlots[45] = new WizardStaff(1);
        itemSlots[46] = new WizardStaff(1);
        itemSlots[47] = new WizardStaff(1);
    }

    public Backpack(){
        slots[0] = new Sprite(SelectedBox);
        itemSlots[0] = new Sword(1);
        for(int i = 1; i < slots.length; i++){
            slots[i] = new Sprite(ItemBox);
            itemSlots[i] = new NullWeapon();
        }
        itemSlots[1] = new Mine(1);
        itemSlots[2] = new TurretItem();
        itemSlots[3] = new LightningStaff(1);
        itemSlots[4] = new LightningStaff(1);
        itemSlots[5] = new LightningStaff(1);
        itemSlots[6] = new LightningStaff(1);
        itemSlots[7] = new LightningStaff(1);
        itemSlots[8] = new Rune(1);
        itemSlots[9] = new Rune(1);
        itemSlots[10] = new AOE(1);
        itemSlots[11] = new LaserGun(1);
        itemSlots[12] = new Magic(1);
        itemSlots[13] = new Magic(1);
        itemSlots[14] = new Magic(1);
        itemSlots[15] = new Magic(1);
        itemSlots[16] = new Magic(1);
        itemSlots[17] = new Magic(1);
        itemSlots[18] = new Magic(1);
        itemSlots[19] = new Magic(1);
        itemSlots[20] = new Magic(1);
        itemSlots[21] = new Magic(1);
        itemSlots[22] = new Magic(1);
        itemSlots[23] = new Rune(1);
        itemSlots[24] = new Rune(1);
        itemSlots[25] = new Rune(1);
        itemSlots[26] = new Rune(1);
        itemSlots[27] = new Rune(1);
        itemSlots[28] = new LightningStaff(1);
        itemSlots[29] = new LightningStaff(1);
        itemSlots[30] = new LightningStaff(1);
        itemSlots[31] = new LightningStaff(1);
        itemSlots[32] = new LightningStaff(1);
        itemSlots[33] = new LightningStaff(1);
        itemSlots[34] = new LightningStaff(1);
        itemSlots[35] = new LightningStaff(1);
        itemSlots[36] = new LightningStaff(1);
        itemSlots[37] = new WizardStaff(1);
        itemSlots[38] = new WizardStaff(1);
        itemSlots[39] = new WizardStaff(1);
        itemSlots[40] = new WizardStaff(1);
        itemSlots[41] = new WizardStaff(1);
        itemSlots[42] = new WizardStaff(1);
        itemSlots[43] = new WizardStaff(1);
        itemSlots[44] = new WizardStaff(1);
        itemSlots[45] = new WizardStaff(1);
        itemSlots[46] = new WizardStaff(1);
        itemSlots[47] = new WizardStaff(1);
    }

    public void input(){
        /** Changes which slot is selected */
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            selectedSlot += 2;
            if(selectedSlot > slots.length - 1) selectedSlot -= slots.length;
            for(int i = 0; i < slots.length; i++){
                slots[i] = new Sprite(ItemBox);
            }
            slots[selectedSlot] = new Sprite(SelectedBox);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            selectedSlot -= 2;
            if(selectedSlot < 0) selectedSlot += slots.length;
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

        if(selectedSlot >= slots.length / pages * pageOn) pageOn++;
        if(selectedSlot < 0 + slots.length / pages * pageOn - slots.length / pages) pageOn--;

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

}
