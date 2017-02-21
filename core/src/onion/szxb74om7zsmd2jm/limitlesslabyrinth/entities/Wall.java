package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by taylor hudson on 2/20/2017.
 */
public class Wall {
    pos CURRENT;
    int lifeTime;
    Animation<TextureRegion> animation;
    float stateTime;
    public Wall(float x, float y, Animation<TextureRegion> animation, int lifeTime){
        // lifeTime enters as wait time
        CURRENT = new pos(x,y);
        this.animation = animation;
        stateTime = 0f;
        this.lifeTime = lifeTime;
    }
}
