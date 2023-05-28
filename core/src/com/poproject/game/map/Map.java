package com.poproject.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Map {
    private final TiledMap tiledMap;
    private final Array<CollisionArea> collisionAreas;

    public Map(TiledMap tiledMap){
        this.tiledMap = tiledMap;
        this.collisionAreas = new Array<>();

        parseCollisionLayer();
    }

    void parseCollisionLayer(){
        final MapLayer colliders = tiledMap.getLayers().get("colliders");
        if(colliders == null) return;

        int i = 0;
        for(MapObject mapObject : colliders.getObjects()){
            Gdx.app.log("Collision Parse: ", String.valueOf(i));
            if(mapObject instanceof RectangleMapObject){
                Gdx.app.log("Rectangle: ", String.valueOf(i));
                createRectangleCollisionArea((RectangleMapObject) mapObject);
            }else if(mapObject instanceof PolylineMapObject){
                createPolylineCollisionArea((PolylineMapObject) mapObject);
                Gdx.app.log("Polyline: ", String.valueOf(i));
            }
//            else if(mapObject instanceof PolygonMapObject){
//                createPolygonCollisionArea((PolygonMapObject) mapObject);
//                Gdx.app.log("Polygon: ", String.valueOf(i));
//            }
            i++;
        }
    }

//    void createPolygonCollisionArea(PolygonMapObject polygonMapObject){
//
//    }

    void createPolylineCollisionArea(PolylineMapObject polylineMapObject){
        final Polyline pl = polylineMapObject.getPolyline();
        collisionAreas.add(new CollisionArea(pl.getOriginX(), pl.getOriginY(), pl.getVertices()));
    }

    void createRectangleCollisionArea(RectangleMapObject rectangleMapObject){
        final Rectangle rectangle = rectangleMapObject.getRectangle();
        final float[] rectVertices = new float[10];

        //LB
        rectVertices[0] = 0;
        rectVertices[1] = 0;
        //LT
        rectVertices[2] = 0;
        rectVertices[3] = rectangle.height;
        //RT
        rectVertices[4] = rectangle.width;
        rectVertices[5] = rectangle.height;
        //RB
        rectVertices[6] = rectangle.width;
        rectVertices[7] = 0;
        //LB
        rectVertices[8] = 0;
        rectVertices[9] = 0;

        collisionAreas.add(new CollisionArea(rectangle.x, rectangle.y, rectVertices));
    }
    public Array<CollisionArea> getCollisionAreas() {
        return collisionAreas;
    }
}
