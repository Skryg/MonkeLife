package com.poproject.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class RandomVectorGenerator {
    private static Vector2 leftDown = new Vector2();
    private static Vector2 rightUp = new Vector2();

    public static void setInterval(Vector2 leftDown, Vector2 rightUp){
        RandomVectorGenerator.leftDown = leftDown;
        RandomVectorGenerator.rightUp = rightUp;
    }

    public static Vector2 getNotClose(Vector2 position, double distance){
        float x=position.x,y=position.y;
        while(distance > Vector2.dst(x,y, position.x, position.y)){
            x = random(leftDown.x, rightUp.x);
            y = random(leftDown.y, rightUp.y);
        }
        return new Vector2(x,y);
    }
}
