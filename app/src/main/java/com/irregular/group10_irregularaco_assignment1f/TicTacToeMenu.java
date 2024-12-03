package com.irregular.group10_irregularaco_assignment1f;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeMenu extends AppCompatActivity implements View.OnClickListener {
    Button btnPlay, btnInfo, btnScore, btnAbout;
    LinearLayout linearLayout1, linearLayout2;

    Intent winPlay,winInfo, winScore, winAbout;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_menu);
        LinearLayout picLL = new LinearLayout(this);
        picLL.layout(0, 0, 500, 500);
        picLL.setLayoutParams(new LinearLayout.LayoutParams(725, 500));
        picLL.setOrientation(LinearLayout.HORIZONTAL);

        setTitle("group10_irregularaco__FINALassignment3F");
        linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);

        linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);

        logo = new ImageView(this);
        logo.setImageResource(R.drawable.group_logo1);
        picLL.addView(logo);

        winPlay = new Intent(this, com.irregular.group10_irregularaco_assignment1f.TicTacToeplayers.class);
        winAbout = new Intent(this, TicTacToeAbout.class);

        btnPlay = new Button(this);
        btnPlay.setText("Play");
        btnPlay.setOnClickListener(this);
        linearLayout2.addView(btnPlay);

        btnAbout = new Button(this);
        btnAbout.setText("About");
        btnAbout.setOnClickListener(this);
        linearLayout2.addView(btnAbout);

        linearLayout1.addView(picLL);
        linearLayout1.addView(linearLayout2);

        setContentView(linearLayout1);
    }

    @Override
    public void onClick(View view) {
        if (view==btnPlay) {
            startActivity(winPlay);
            Log.d("test", "pumasok");
        }
        else if (view==btnAbout) {
            startActivity(winAbout);
            Log.d("test", "pumasok");
        }


    }
}