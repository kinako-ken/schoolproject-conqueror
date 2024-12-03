package com.irregular.group10_irregularaco_assignment1f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainHome extends AppCompatActivity implements View.OnClickListener{
    Button button1,button2;
    Intent winConqueror, winLightners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);


        winConqueror = new Intent(this, BreakoutMenu.class);
        winLightners = new Intent(this, TicTacToeMenu.class);


    }

    @Override
    public void onClick(View v) {
        if (v == button1) {
            startActivity(winConqueror);
        }
        else if(v == button2) {
            startActivity(winLightners);
        }
    }
}