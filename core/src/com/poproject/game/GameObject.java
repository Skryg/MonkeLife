package com.poproject.game;

public class GameObject {
    static GameObject Instantiate(){
        GameObject go = new GameObject();
        Game.instance.gameObjects.add(go);
        return go;
    }
    void Start(){

    }
    void Update(){

    }


}
