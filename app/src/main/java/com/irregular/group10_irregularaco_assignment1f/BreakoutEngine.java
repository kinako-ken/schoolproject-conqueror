package com.irregular.group10_irregularaco_assignment1f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

class BreakoutEngine extends SurfaceView implements Runnable {

    private Thread gameThread = null;

    private SurfaceHolder ourHolder;

    private volatile boolean playing;

    private boolean paused = true;

    private Canvas canvas;
    private Paint paint;

    private int screenX;
    private int screenY;


    private int borderX1, borderX2, borderX3;

    private Bitmap protag2, orb, wall, over,tryAgainBit;

    private boolean isGameOver;
    private boolean onClickGameOver;

    // For sound FX
    SoundPool soundPool;
    int explodeID, boundaryID, paddleID, beep1ID,beep3ID,gameoverID,speedupID;


    Bat bat;
    Ball ball;
    SavedPrefs savedPrefs;

    Brick[] bricks = new Brick[100];
    int numBricks;

    Rect tryAgainDst,tryAgainSrc;

    MediaPlayer bgm;

    private int score, lives, highScore;

    public BreakoutEngine(Context context, int x, int y) {
        super(context);


        ourHolder = getHolder();
        paint = new Paint();

        screenX = x;
        screenY = y;


        Log.d("test", "resolution " + x + " " + y);
        borderX1 = (int) (screenX * .1914);
        borderX2 = (int) (screenX * .8048);
        //Typed something and it worked
        borderX3 = (int) (screenX * .6171);
        Log.d("test", "borderx2 " + borderX2 + " borderx3 " + borderX3);


        //Performance issue fixed, used this instead of drawing background over .drawBitmap which causes lag
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        setBackgroundResource(R.drawable.gametest1);


        //Bitmap
        InputStream isprotag = getResources().openRawResource(R.raw.protag2_walk);
        protag2 = BitmapFactory.decodeStream(isprotag);

        InputStream isorb = getResources().openRawResource(R.raw.protag2_orb);
        orb = BitmapFactory.decodeStream(isorb);

        InputStream iswall = getResources().openRawResource(R.raw.game2_wall);
        wall = BitmapFactory.decodeStream(iswall);

        InputStream isover = getResources().openRawResource(R.raw.protag2_over);
        over = BitmapFactory.decodeStream(isover);

        InputStream isagain = getResources().openRawResource(R.raw.try_again);
        tryAgainBit = BitmapFactory.decodeStream(isagain);

        bat = new Bat(screenX, screenY);
        ball = new Ball(screenX, screenY);

        savedPrefs = new SavedPrefs();
        highScore = SavedPrefs.loadTotalFromPref(context);

        int testx = screenX / 2;
        int testy = screenY / 2;
        tryAgainDst = new Rect(testx - 100,testy + 10,testx+100,testy + 60);
        tryAgainSrc = new Rect(0,0,400,100);

        loadSounds(context);

        reset();



    }

    public void pause() {
        playing = false;
        stopBGM();
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "Error");
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();

    }


    @Override
    public void run() {

        while (playing) {

            //If there is no if condition, the game will continue updating unnecessarily at the start of the game.
            //The ball will keep on moving too since it moves when update() is called.
            if (!paused) {
                update();
            }
            draw();

            if(isGameOver) {
                Log.d("haha", "enter");
                soundPool.play(gameoverID, 1,1,1,0,1);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                paused = true;
                playing = false;
                onClickGameOver = true;
            }
        }
        while (isGameOver) {
            gameOver(getContext().getApplicationContext());
        }

    }

    private void update() {

        //Update sprite position depending on movement state
        bat.update();

        ball.update();

        //Bat to boundary collision
        if (bat.getRectDst().left < borderX1) {
            Log.d("bound", "borderx1");
            bat.setMovementState(bat.BLEFT);
            soundPool.play(boundaryID, 0.5F,0.5F,1,0,1);
        }
        if (bat.getRectDst().right > borderX2) {
            Log.d("bound", "borderx1");
            bat.setMovementState(bat.BRIGHT);
            soundPool.play(boundaryID, 0.5F,0.5F,1,0,1);
        }

        //Brick and ball collision
        for (int i = 0; i < numBricks; i++) {
            if (bricks[i].getVisibility()) {
                // Changed to remove FPS Function float to int
                if (Rect.intersects(bricks[i].getRectDst(), ball.getRectDst())) {
                    bricks[i].setInvisible();
                    ball.reverseYVelocity();
                    score = score + 10;
                    if(score % 20 == 0) {
                        Log.d("score","true");
                        ball.increaseSpeed();
                        soundPool.play(speedupID, 1,1,1,0,1);
                    }
//                    ball.increaseSpeed();
                    soundPool.play(explodeID, 0.5F,0.5F,1,0,1);
                }
            }
        }

        //Bat and ball collision
        if (Rect.intersects(bat.getRectDst(), ball.getRectDst())) {
            ball.setRandomXVelocity();
            ball.reverseYVelocity();
            ball.clearObstacleY(bat.getRectDst().top - 2);
            soundPool.play(paddleID, 0.5F,0.5F,1,0,1);
        }

        //Ball and bottom screen collision
        if (ball.getRectDst().bottom > screenY) {
            ball.reverseYVelocity();
            ball.clearObstacleY(screenY - 2); //Small bump, to avoid glitches
            soundPool.play(beep3ID, 0.5F,0.5F,1,0,1);

            // Lose a life
            lives--;

            if (lives == 0) {
//                paused = true;
//                reset();
                stopBGM();
                isGameOver = true;
            }
        }

        //Ball and top screen collision
        if (ball.getRectDst().top < 0) {
            ball.reverseYVelocity();
            ball.clearObstacleY(12); //Small bump, to avoid glitches
            soundPool.play(beep3ID, 0.5F,0.5F,1,0,1);
        }

        //Ball and left screen collision
        if (ball.getRectDst().left < borderX1) {
            ball.reverseXVelocity();
            ball.clearObstacleX(borderX1 + 2); //Small bump, to avoid glitches
            soundPool.play(beep1ID, 0.5F,0.5F,1,0,1);
        }

        //Ball and right screen collision
        if (ball.getRectDst().right > borderX2) {
            ball.reverseXVelocity();
            ball.clearObstacleX(borderX2 - 22); //Small bump, to avoid glitches
            soundPool.play(beep1ID, 0.5F,0.5F,1,0,1);
        }

        //If score is equals to total number of bricks in game(10 is the score for each brick hit.)
        //Then pause the game so it stops updating, then reset it after.
        if (score == numBricks * 10) {
            stopBGM();
            paused = true;
            reset();
        }

    }

    private void reset() {
        paused = true;
        isGameOver = false;
        onClickGameOver = false;
        bat.reset();
        ball.reset();


        startBGM();

        lives = 3;
        score = 0;

        int brickWidth = borderX3 / 8;
        int brickHeight = screenY / 10;
        Log.d("test", "width " + brickWidth);

        numBricks = 0;

        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 3; row++) {
                bricks[numBricks] = new Brick(borderX1, row, column, brickWidth, brickHeight);
                numBricks++;
            }
        }


    }

    private void draw() {

        if (ourHolder.getSurface().isValid()) {

            canvas = ourHolder.lockCanvas();

            //Performance issue fixed
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);

            //Draws the sprite
            canvas.drawBitmap(protag2, bat.getRectSrc(), bat.getRectDst(), paint);
            canvas.drawBitmap(orb, ball.getRectSrc(), ball.getRectDst(), paint);

            for (int i = 0; i < numBricks; i++) {
                if (bricks[i].getVisibility()) {
                    canvas.drawBitmap(wall, bricks[i].getRectSrc(), bricks[i].getRectDst(), paint);
                }
            }

            paint.setColor(Color.argb(255, 255, 255, 255));

            paint.setTextSize(70);
            canvas.drawText("Score: " + score + " Lives: " + lives, 10, 80, paint);
            canvas.drawText("Highscore: " + highScore,  640, 80, paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int tx = (int) motionEvent.getX();
        int ty = (int) motionEvent.getY();
        boolean touched;
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                paused = false;

                if (motionEvent.getX() > screenX / 2) {
                    bat.setMovementState(bat.RIGHT);
                } else {
                    bat.setMovementState(bat.LEFT);
                }

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                bat.setMovementState(bat.STOPPED);
                break;
        }

        if (onClickGameOver) {
            touched = tryAgainDst.contains(tx,ty);
            if (touched) {
                Log.d("touch", "touched");
                reset();
                resume();
            }
        }

        return true;
    }

    private void loadSounds(Context context) {

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        boundaryID = soundPool.load(context, R.raw.border, 1);
        explodeID = soundPool.load(context, R.raw.hit, 1);
        paddleID = soundPool.load(context, R.raw.paddle, 1);
        beep1ID = soundPool.load(context, R.raw.beep1,1);
        beep3ID = soundPool.load(context, R.raw.beep3,1);
        gameoverID = soundPool.load(context, R.raw.gameover, 1);
        speedupID = soundPool.load(context, R.raw.speedup, 1);


    }

    private void gameOver(Context context) {
        int x = screenX / 2;
        int y = screenY / 2;
        Rect overSrc = new Rect(64*4,0,64*5, 64);
        Rect overDst = new Rect(x - 60, y - 210, x + 60,y - 105);


        canvas = ourHolder.lockCanvas();
        paint.setColor(Color.BLACK);
        canvas.drawRect(x - 325, y - 210, x + 325, y + 210,paint);
        canvas.drawBitmap(over, overSrc,overDst,paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Game Over", x - 125,y - 60,paint );
        paint.setTextSize(25);
        canvas.drawText("Score: " + score, x - 60, y - 30, paint);

        if(score > highScore) {
            Log.d("gameover", "nice");
            highScore = score;
            savedPrefs.saveTotalInPref(context, highScore);
            canvas.drawText("You beat the high score!", x - 125, y, paint);
        }
        paint.setColor(Color.WHITE);
        canvas.drawBitmap(tryAgainBit,tryAgainSrc,tryAgainDst,paint);


        ourHolder.unlockCanvasAndPost(canvas);
        pause();

    }
    private void startBGM() {
        if (bgm == null) {
            bgm = MediaPlayer.create(getContext().getApplicationContext(), R.raw.bg_loop);
        }
        bgm.start();
        bgm.setLooping(true);
    }

    private void stopBGM() {
        if(bgm!=null) {
            bgm.release();
            bgm = null;
        }

    }


}




