package com.irregular.group10_irregularaco_assignment1f;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class BreakoutActivity extends Activity {
    BreakoutEngine breakoutEngine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Getting phone resolution
        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        breakoutEngine = new BreakoutEngine(this,size.x,size.y);

        setContentView(breakoutEngine);
    }

    @Override
    protected void onResume() {
        super.onResume();

        breakoutEngine.resume();

    }
    @Override
    protected void onPause() {
        super.onPause();

        breakoutEngine.pause();

    }
}