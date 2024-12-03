package com.irregular.group10_irregularaco_assignment1f;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BreakoutScore extends AppCompatActivity {

    ImageView hsImage;
    TextView text;
    SavedPrefs savedPrefs;
    int highscore;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedPrefs = new SavedPrefs();
        highscore = savedPrefs.loadTotalFromPref(this);

        LinearLayout picLL = new LinearLayout(this);
        picLL.setGravity(Gravity.CENTER);
        picLL.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        hsImage = new ImageView(this);
        hsImage.setImageResource(R.drawable.game2_hs);
        picLL.addView(hsImage);


        text = new TextView(this);
        text.setTextSize(25);
        text.setTextAlignment(text.TEXT_ALIGNMENT_CENTER);
        text.setText("The current high score is:\n" + highscore);

        linearLayout.addView(picLL);
        linearLayout.addView(text);

        setContentView(linearLayout);
    }
}
