package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by taylor hudson on 2/20/2017.
 */
public class Wall extends Entity{

    int lifeTime;
    Animation<TextureRegion> animation;
    float stateTime;
    boolean needsDeset;
    boolean removeState;
    public Wall(float x, float y, Animation<TextureRegion> animation, int lifeTime) {
        // lifeTime enters as wait time
        super(x, y);
        this.animation = animation;
        removeState = false;
        stateTime = 0f;
        this.lifeTime = lifeTime + (int)Gdx.graphics.getDeltaTime();
        collisionLayer = (TiledMapTileLayer) Play.getMap().getLayers().get(1);
        this.sprite = new Sprite(spriteTextures.basic32);
        sprite.setPosition(x, y);
        needsDeset = setCollisionLayer(x, y, "blocked");

    }
    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        batch.draw((TextureRegion) animation.getKeyFrame(stateTime, true), sprite.getX(), sprite.getY());
        if(stateTime > lifeTime){
            removeState = true;
        }
    }
    public boolean getRemoveState(){return removeState;}
    public boolean setCollisionLayer(float x, float y, String set){
        boolean hasBLOCKED = collisionLayer.getCell((int)((x)/ collisionLayer.getTileWidth()) ,(int)((y)/collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked");
        System.out.println("has : " + hasBLOCKED + " @ (" + x + ", " + y +")");
        if(!hasBLOCKED)
            collisionLayer.getCell((int)(x/ collisionLayer.getTileWidth()) ,(int)(y/collisionLayer.getTileHeight())).getTile().getProperties().put("blocked","blocked");
        return hasBLOCKED;
    }
    public void removeBlocked(float x, float y){
        collisionLayer.getCell((int)(x/ collisionLayer.getTileWidth()) ,(int)(y/collisionLayer.getTileHeight())).getTile().getProperties().remove("blocked");
    }
    public void remove(){
        if(!needsDeset){
            removeBlocked(sprite.getX(),sprite.getY());
        }
    }
}
