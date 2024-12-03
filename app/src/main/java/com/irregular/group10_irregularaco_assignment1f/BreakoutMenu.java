package com.irregular.group10_irregularaco_assignment1f;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class BreakoutMenu extends AppCompatActivity implements View.OnClickListener{

    Button btnPlay, btnInfo, btnScore, btnAbout;
    LinearLayout linearLayout1, linearLayout2;

    Intent winPlay,winInfo, winScore, winAbout;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout picLL = new LinearLayout(this);
        picLL.layout(0, 0, 500, 500);
        picLL.setLayoutParams(new LinearLayout.LayoutParams(725, 500));
        picLL.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);

        linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);


        logo = new ImageView(this);
        logo.setImageResource(R.drawable.game2_logo);
        picLL.addView(logo);



        winPlay = new Intent(this, BreakoutActivity.class);
        winInfo = new Intent(this, BreakoutInfo.class);
        winScore = new Intent(this, BreakoutScore.class);
        winAbout = new Intent(this, BreakoutAbout.class);


        btnPlay = new Button(this);
        btnPlay.setText("Play");
        btnPlay.setOnClickListener(this);
        linearLayout2.addView(btnPlay);

        btnInfo = new Button(this);
        btnInfo.setText("Info");
        btnInfo.setOnClickListener(this);
        linearLayout2.addView(btnInfo);

        btnScore = new Button(this);
        btnScore.setText("High Score");
        btnScore.setOnClickListener(this);
        linearLayout2.addView(btnScore);

        btnAbout = new Button(this);
        btnAbout.setText("About");
        btnAbout.setOnClickListener(this);
        linearLayout2.addView(btnAbout);



        linearLayout1.addView(picLL);
        linearLayout1.addView(linearLayout2);

        setContentView(linearLayout1);


    }

    @Override
    public void onClick(View v) {
        if (v==btnPlay) {
            startActivity(winPlay);
            Log.d("test", "pumasok");
        }
        else if (v==btnInfo) {
            startActivity(winInfo);
            Log.d("test", "pumasok");
        }
        else if (v==btnScore) {
            startActivity(winScore);
            Log.d("test", "pumasok");
        }
        else if (v==btnAbout) {
            startActivity(winAbout);
            Log.d("test", "pumasok");
        }


    }


    //My_application yung kukunan ng code
// https://stackoverflow.com/questions/5981098/set-the-background-image-of-a-surfaceview
}
