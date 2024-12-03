package com.irregular.group10_irregularaco_assignment1f;


import android.graphics.Rect;
import android.util.Log;

public class Brick {

    private Rect rectDst,rectSrc;

    private boolean isVisible;

    Brick(int borderX1, int row, int column, int width, int height){

        isVisible = true;

        int padding = 1;

        rectSrc = new Rect(0,0,96,96);

        rectDst = new Rect(column * width + borderX1 + padding,
                row * height + padding,
                column * width +  borderX1 + width - padding,
                row * height  + height - padding);
        Log.d("test" , "brick properties " + rectDst.left + " " + rectDst.right);

    }

    Rect getRectDst(){
        return rectDst;
    }

    Rect getRectSrc() {
        return rectSrc;
    }

    void setInvisible(){
        isVisible = false;
    }

    boolean getVisibility(){
        return isVisible;
    }
}
