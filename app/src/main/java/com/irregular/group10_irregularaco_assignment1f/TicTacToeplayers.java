package com.irregular.group10_irregularaco_assignment1f;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeplayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_players);
        setTitle("group10_irregularaco__FINALassignment3F");

        final EditText uno = findViewById(R.id.player1);
        final EditText dos = findViewById(R.id.player2);
        final Button play = findViewById(R.id.start);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String playerOne = uno.getText().toString();
                final String playerTwo = dos.getText().toString();

                if(playerOne.isEmpty() || playerTwo.isEmpty()){
                    Toast.makeText(TicTacToeplayers.this,"Please enter a name", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(TicTacToeplayers.this, com.irregular.group10_irregularaco_assignment1f.TicTacToeMain.class);
                    intent.putExtra("PlayerOne", playerOne);
                    intent.putExtra("PlayerTwo", playerTwo);
                    startActivity(intent);
                }
            }
        });


    }
}