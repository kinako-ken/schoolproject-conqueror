package com.irregular.group10_irregularaco_assignment1f;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BreakoutInfo extends AppCompatActivity {

    LinearLayout linearLayout;
    ImageView infoImage;
    TextView text1, text2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout picLL = new LinearLayout(this);
//        picLL.layout(0, 0, 500, 500);
//        picLL.setLayoutParams(new LinearLayout.LayoutParams(825, 500));
        picLL.setGravity(Gravity.CENTER);
        picLL.setOrientation(LinearLayout.HORIZONTAL);


        infoImage = new ImageView(this);
        infoImage.setImageResource(R.drawable.game2_info);
        picLL.addView(infoImage);


        text1 = new TextView(this);
        text1.setTextSize(25);
        text1.setTextColor(Color.BLACK);
        text1.setTextAlignment(text1.TEXT_ALIGNMENT_CENTER);
        text1.setText("INSTRUCTIONS");

        text2 = new TextView(this);
        text2.setTextSize(15);
        text2.setTextAlignment(text2.TEXT_ALIGNMENT_CENTER);
        text2.setText("1.Control your character by holding left or right\n" +
                "2.Hit the wall using the purple orb\n" +
                "3.The orb gets faster the higher the score\n"+
                "4.Get the highest score possible\n" +
                "5.Miss hitting the ball and you lose 1 life");


        linearLayout.addView(picLL);
        linearLayout.addView(text1);
        linearLayout.addView(text2);

        setContentView(linearLayout);
    }
}
