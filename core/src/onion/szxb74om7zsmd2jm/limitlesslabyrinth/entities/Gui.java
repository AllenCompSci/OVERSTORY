package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private static float healthBarX = 0;
    private static Play pl = new Play();

    public Gui(){
        playerHealthBar = new Sprite(new Texture("playerhealthbar.png"));
        playerLostHealthBar = new Sprite(new Texture("playerredbar.png"));
        itemBox1 = new Sprite(new Texture("nullItem.png"));
        itemBox2 = new Sprite(new Texture("nullItem.png"));
        itemBox3 = new Sprite(new Texture("nullItem.png"));
        itemBox4 = new Sprite(new Texture("nullItem.png"));
    }

    public void update(){

        /** Health Bar Update */
        playerHealthBar.setPosition(pl.getCamera().position.x - pl.getCamera().viewportWidth/2 + 10 - healthBarX, pl.getCamera().position.y + pl.getCamera().viewportHeight/2 - 28);
        playerLostHealthBar.setPosition(pl.getCamera().position.x - pl.getCamera().viewportWidth/2 + 10 - healthBarX, pl.getCamera().position.y + pl.getCamera().viewportHeight/2 - 28);
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
    }
}
