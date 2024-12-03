package com.irregular.group10_irregularaco_assignment1f;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeMain extends AppCompatActivity {

    private final List<int[]> combinationsList = new ArrayList<>();

    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};

    private int PlayerTurn = 1;

    private int TotalSelectedBoxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_main);
        setTitle("group10_irregularaco__FINALassignment3F");

        playerOneName = findViewById(R.id.PlayerOneName);
        playerTwoName = findViewById(R.id.PlayerTwoName);

        playerOneLayout = findViewById(R.id.PlayerOneLayout);
        playerTwoLayout = findViewById(R.id.PlayerTwoLayout);

        image1 = findViewById(R.id.box1);
        image2 = findViewById(R.id.box2);
        image3 = findViewById(R.id.box3);
        image4 = findViewById(R.id.box4);
        image5 = findViewById(R.id.box5);
        image6 = findViewById(R.id.box6);
        image7 = findViewById(R.id.box7);
        image8 = findViewById(R.id.box8);
        image9 = findViewById(R.id.box9);

        // HORIZONTAL
        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        // VERTICAL
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        // DIAGONAL
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});
        // L-SHAPE CORNERS
        combinationsList.add(new int[]{0, 1, 3});
        combinationsList.add(new int[]{1, 2, 5});
        combinationsList.add(new int[]{3, 6, 7});
        combinationsList.add(new int[]{5, 7, 8});
        // L-SHAPE INSIDES
        combinationsList.add(new int[]{1, 3, 4});
        combinationsList.add(new int[]{1, 4, 5});
        combinationsList.add(new int[]{3, 4, 7});
        combinationsList.add(new int[]{4, 5, 7});
        // OTHER L'S
        combinationsList.add(new int[]{0, 3, 4});
        combinationsList.add(new int[]{2, 4, 5});
        combinationsList.add(new int[]{3, 4, 6});
        combinationsList.add(new int[]{4, 5, 8});

        final String getPlayerOneName = getIntent().getStringExtra("PlayerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("PlayerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(0)) {
                    performAction((ImageView) view, 0);
                }

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(1)) {
                    performAction((ImageView) view, 1);
                }

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(2)) {
                    performAction((ImageView) view, 2);
                }

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(3)) {
                    performAction((ImageView) view, 3);
                }

            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(4)) {
                    performAction((ImageView) view, 4);
                }

            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(5)) {
                    performAction((ImageView) view, 5);
                }

            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(6)) {
                    performAction((ImageView) view, 6);
                }

            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(7)) {
                    performAction((ImageView) view, 7);
                }

            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(8)) {
                    performAction((ImageView) view, 8);
                }
            }
        });

        }

    private void performAction(ImageView imageView, int selectedBoxPosition){
        boxPositions[selectedBoxPosition] = PlayerTurn;
        if (PlayerTurn == 1) {
            imageView.setImageResource(R.drawable.ace_attorney);
            if (checkPlayerWin()) {
                TicTacToeWinDialog winDialog = new TicTacToeWinDialog(TicTacToeMain.this, playerOneName.getText().toString() + " has won the match!", TicTacToeMain.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if (TotalSelectedBoxes == 9) {
                TicTacToeWinDialog winDialog = new TicTacToeWinDialog(TicTacToeMain.this, "It's a draw!", TicTacToeMain.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(2);
                TotalSelectedBoxes++;
            }
        }
        else {
            imageView.setImageResource(R.drawable.undertale);
            if (checkPlayerWin()) {
                TicTacToeWinDialog winDialog = new TicTacToeWinDialog(TicTacToeMain.this, playerTwoName.getText().toString() + " has won the match!", TicTacToeMain.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if (selectedBoxPosition == 9){
                TicTacToeWinDialog winDialog = new TicTacToeWinDialog(TicTacToeMain.this, "It's a draw!", TicTacToeMain.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(1);

                TotalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn){
        PlayerTurn = currentPlayerTurn;
        if (PlayerTurn == 1){
            // magkaiba dapat layouts
            playerOneLayout.setBackgroundResource(R.drawable.boarder);
            playerTwoLayout.setBackgroundResource(R.drawable.boarder);
        }
        else {
            // swap layouts from above
            playerOneLayout.setBackgroundResource(R.drawable.boarder);;
            playerTwoLayout.setBackgroundResource(R.drawable.boarder);
        }
    }

    private boolean checkPlayerWin(){
        boolean response = false;

        for(int i=0;i<combinationsList.size();i++) {
            final int [] combination = combinationsList.get(i);
            if (boxPositions[combination[0]] == PlayerTurn && boxPositions[combination[1]] == PlayerTurn && boxPositions[combination[2]] == PlayerTurn) {
                response = true;
            }
        }
        return response;
    }

    private Boolean isBoxSelectable(int BoxPosition) {
        boolean response = false;

        if(boxPositions[BoxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch(){
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        PlayerTurn = 1;
        TotalSelectedBoxes = 1;
        image1.setImageResource(R.drawable.empty);
        image2.setImageResource(R.drawable.empty);
        image3.setImageResource(R.drawable.empty);
        image4.setImageResource(R.drawable.empty);
        image5.setImageResource(R.drawable.empty);
        image6.setImageResource(R.drawable.empty);
        image7.setImageResource(R.drawable.empty);
        image8.setImageResource(R.drawable.empty);
        image9.setImageResource(R.drawable.empty);
    }
    }