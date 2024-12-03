package com.irregular.group10_irregularaco_assignment1f;

import android.graphics.Rect;

import java.util.Random;

public class Ball {

    private Rect rectSrc, rectDst;
    private int xVelocity, yVelocity;
    private int ballWidth, ballHeight;
    private int screenX, screenY;

    //Ball issue: Since the velocity of the ball doesn't reset
    //When the player finishes the game the ball will go downwards(since it hit a brick before it everything resets)
    Ball(int x, int y) {
        screenX = x;
        screenY = y;
        ballWidth = 20;
        ballHeight = 20;

        xVelocity = 5;
        yVelocity = -10;

        rectSrc = new Rect();
        rectDst = new Rect();


    }

    Rect getRectSrc() {
        return rectSrc;
    }
    Rect getRectDst(){
        return rectDst;
    }

    void reset(){

        yVelocity = -10;

        rectSrc.left = 0;
        rectSrc.top = 0;
        rectSrc.right = 947;
        rectSrc.bottom = 843;

        rectDst.left = screenX / 2;
        rectDst.top = screenY - 100;
        rectDst.right = rectDst.left + ballWidth;
        rectDst.bottom = rectDst.top + ballHeight;
    }

    void update() {
        rectDst.left = rectDst.left + xVelocity;
        rectDst.top = rectDst.top + yVelocity;
        rectDst.right = rectDst.left + ballWidth;
        rectDst.bottom = rectDst.top + ballHeight;
    }

    // Changed to remove FPS Function float to int
    void clearObstacleY(int y){
        rectDst.bottom = y;
        rectDst.top = y - ballHeight;
    }
    // Changed to remove FPS Function float to int
    void clearObstacleX(int x){
        rectDst.left = x;
        rectDst.right = x + ballWidth;
    }

    void reverseYVelocity(){
        yVelocity = -yVelocity;
    }

    void reverseXVelocity(){
        xVelocity = - xVelocity;
    }

    void setRandomXVelocity(){
        Random generator = new Random();
        int answer = generator.nextInt(2);

        if(answer == 0){
            reverseXVelocity();
        }
    }

    void increaseSpeed() {
        if(yVelocity > 0) {
            yVelocity++;
        } else {
            yVelocity--;
        }
    }


}
