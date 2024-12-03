package com.irregular.group10_irregularaco_assignment1f;

import android.graphics.Rect;

public class Bat {

    private Rect rectDst, rectSrc;
    private int frame = 0;

    private int x,y;
    private int length,height;


    private int paddleSpeed;

    //Used for determining movement
    final int STOPPED = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    final int BLEFT = 3;
    final int BRIGHT = 4;
    private int paddleMoving = STOPPED;

    Bat(int screenX, int screenY) {
        x = screenX;
        y = screenY;

        length = 64;
        height = 64;

        rectSrc = new Rect();
        rectDst = new Rect();

        //Speed of sprite
        paddleSpeed = 20;

    }

    void reset() {

        //Bitmap Source - Default Position - Looking right - Start Pos: 0,192,64,255 - Checked from Paint app
        rectSrc.left = 0;
        rectSrc.top = 64 * 3;
        rectSrc.right = 64;
        rectSrc.bottom = 64 * 4;

        //Bitmap Distance - Default Position - At the middle and very bottom of the screen
        rectDst.left = x /2; //left
        rectDst.top = y - height; //top
        rectDst.right = rectDst.left + length; //right
        rectDst.bottom = y; //bottom

    }

    Rect getRectSrc() {
        return rectSrc;
    }
    Rect getRectDst() {
        return rectDst;
    }



    //Resets to left sprite only, does not mean it will return to 1st left sprite
    void resetsrcLeft() {
//        aSrc[0] = 0;
        rectSrc.top = 64;
//        aSrc[2] = 64;
        rectSrc.bottom = 64 * 2;
    }
    //Resets to right sprite only, does not mean it will return to 1st right sprite
    void resetsrcRight() {
//        aSrc[0] = 0;
        rectSrc.top = 64 * 3;
//        aSrc[2] = 64;
        rectSrc.bottom = 64 * 4;
    }


    void setMovementState(int state) {
        paddleMoving = state;
    }
    void update() {
        if (paddleMoving == BLEFT) {
            rectDst.left = rectDst.left + paddleSpeed ;
            rectDst.right = rectDst.right + paddleSpeed ;
            paddleMoving = STOPPED;
        } else {
            if (paddleMoving == LEFT) {
//            frame = 0;

                //Animation - Left
                //If reached the last sprite then reset sprite
                //Else continue changing sprite
                resetsrcLeft();
                if (frame == 8) {
                    frame = 0;
                    resetsrcLeft();
                    rectSrc.left = 0;
                    rectSrc.right = 64;
                } else {
                    rectSrc.left += 64;
                    rectSrc.right += 64;
                }
                //Moves the sprite to the left
                rectDst.left = rectDst.left - paddleSpeed;
                rectDst.right = rectDst.right - paddleSpeed;
                frame++;
            }

            if (paddleMoving == BRIGHT) {
                rectDst.left = rectDst.left - paddleSpeed ;
                rectDst.right = rectDst.right - paddleSpeed ;
                paddleMoving = STOPPED;
            } else {
                if (paddleMoving == RIGHT) {
//            frame = 0;

                    //Animation - Right
                    //If reached the last sprite then reset sprite
                    //Else continue changing sprite
                    resetsrcRight();
                    if (frame == 8) {
                        frame = 0;
                        resetsrcRight();
                        rectSrc.left = 0;
                        rectSrc.right = 64;
                    } else {
                        rectSrc.left += 64;
                        rectSrc.right += 64;
                    }
                    //Moves the sprite to the right
                    rectDst.left = rectDst.left + paddleSpeed;
                    rectDst.right = rectDst.right + paddleSpeed;
                    frame++;

                }
            }
        }
    }
}
