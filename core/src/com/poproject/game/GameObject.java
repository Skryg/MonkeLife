package com.poproject.game;
public class GameObject {
    //children      ???
    //parent        ???
    //components:   ???
    //-Sprite renderer
    //-Position
    //-Collider
    //-Physics
    static GameObject instantiate(GameObject go){
        Game.instance.gameObjects.add(go);
        go.start();
        return go;
    }
    void start(){}
    void update(){}
    void lateUpdate(){}
    void destroy(){
        Game.instance.gameObjects.remove(this);
    }
}
