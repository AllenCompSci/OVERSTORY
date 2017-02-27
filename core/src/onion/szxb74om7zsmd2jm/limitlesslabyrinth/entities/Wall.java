package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
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
    int SETX, SETY;
    public Wall(float x, float y, Animation<TextureRegion> animation, int lifeTime) {
        // lifeTime enters as wait time
        super(x, y);
        this.animation = animation;
        removeState = false;
        stateTime = 0f;
        this.lifeTime = lifeTime + (int)Gdx.graphics.getDeltaTime();
        collisionLayer = (TiledMapTileLayer) Play.getMap().getLayers().get(1);
        this.sprite = new Sprite(spriteTextures.basic64);

        removeState = setCollisionLayer(x, y, "blocked");
        SETX = (int)(x/ collisionLayer.getTileWidth());
        SETY = (int) (y/collisionLayer.getTileHeight()) ;
        /// Needs to look better with cursor. This looks like a wall and acts correctly. 
        x = SETX * collisionLayer.getTileWidth()-32;
        y = SETY * collisionLayer.getTileHeight()-32;
        sprite.setPosition(x, y);

    }


    public boolean getCollision(int x1, int y1, int x2, int y2, int x3, int y3){


        return COLIDE(x1,y1) && COLIDE(x2,y2) && COLIDE(x3,y3);
    }
    private boolean COLIDE(int x1, int y1){

        return (SETX+1 == x1 || x1 == (SETX -1) || SETX == x1)&& (y1 == (SETY -1) || SETY == y1);
    }
    public int getSETX(){
        return SETX;
    }
    public int getSETY(){
        return SETY;
    }
    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) animation.getKeyFrame(stateTime, true), sprite.getX(), sprite.getY());
        if(stateTime > lifeTime){
            wallDespawn();
        }
    }
    public boolean getRemoveState(){return removeState;}
    public boolean setCollisionLayer(float x, float y, String set){
        boolean hasBLOCKED = collisionLayer.getCell((int)((x)/ collisionLayer.getTileWidth()) ,(int)((y)/collisionLayer.getTileHeight())).getTile().getProperties().containsKey("blocked");
        if(hasBLOCKED)
            return true;
        for(Wall e : Play.getWalls()){
            hasBLOCKED = hasBLOCKED ||e.COLIDE(SETX,SETY);
            if(hasBLOCKED)
                return true;
        }
        //System.out.println("has : " + hasBLOCKED + " @ (" + x + ", " + y +")");
        //if(!hasBLOCKED)
            //collisionLayer.getCell((int)(x/ collisionLayer.getTileWidth()) ,(int)(y/collisionLayer.getTileHeight())).getTile().getProperties().put("blocked","blocked");
        return hasBLOCKED;
    }
    public void removeBlocked(float x, float y){
        collisionLayer.getCell((int)(x/ collisionLayer.getTileWidth()) ,(int)(y/collisionLayer.getTileHeight())).getTile().getProperties().remove("blocked");
    }
    public void wallDespawn(){

        Play.getWalls().set(Play.getWalls().indexOf(this, true), null);
        Play.getWalls().removeIndex(Play.getWalls().indexOf(null, true));
    }
}
