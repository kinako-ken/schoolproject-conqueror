package com.irregular.group10_irregularaco_assignment1f;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeAbout extends AppCompatActivity {
    ImageView groupImage;
    LinearLayout linearLayout;
    TextView text, text2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout picLL = new LinearLayout(this);
        picLL.layout(0, 0, 500, 500);
        picLL.setLayoutParams(new LinearLayout.LayoutParams(825, 500));
        picLL.setOrientation(LinearLayout.HORIZONTAL);


        groupImage = new ImageView(this);
        groupImage.setImageResource(R.drawable.group_logo);
        picLL.addView(groupImage);

        text = new TextView(this);
        text.setTextSize(30);
        text.setTextAlignment(text.TEXT_ALIGNMENT_CENTER);
        text.setTextColor(Color.BLACK);
        text.setText("Irregular A Co.");


        text2 = new TextView(this);
        text2.setTextSize(20);
        text2.setTextAlignment(text2.TEXT_ALIGNMENT_CENTER);
        text2.setText("Group Members:\n" +
                "Noquiao, Kenneth Ryan\n" +
                "Rebenque, Maria Gabriella\n"+
                "De Jesus, Christian Angelo\n"+
                "Paronda, Gabriel");

        linearLayout.addView(picLL);
        linearLayout.addView(text);
        linearLayout.addView(text2);

        setContentView(linearLayout);



    }
}
