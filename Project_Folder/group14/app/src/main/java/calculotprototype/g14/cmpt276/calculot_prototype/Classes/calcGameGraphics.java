package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;

import calculotprototype.g14.cmpt276.calculot_prototype.R;


/**
 * Created by Jean Pierre on 2017-03-25.
 */

public class calcGameGraphics extends View {

    // ENVIROMENT MEMBERS
    Bitmap background;
    Bitmap castle_img;
    Paint paintFloor = new Paint();
    Paint paintSky = new Paint();
    int skyblue = getContext().getResources().getColor(R.color.skyblue);
    int grassgreen = getContext().getResources().getColor(R.color.grassgreen);
    int floor_right;
    int floor_bottom;
    int floor_top;
    int castle_top;
    int castle_width;
    int castle_height;

    // SCREEN MEMBERS
    int width;
    int height;

    // WIZARD MEMBERS
    int char_left;
    int char_top;
    int char_width;
    int char_height;
    Bitmap character_img;

    // MONSTER MEMBERS
    Monster monster;
    int monster_left;
    int monster_top;
    int spawn_left;
    int monster_width;
    int monster_height;
    Bitmap monster_img;

    // Initialization
    public calcGameGraphics(Context context, Monster _monster) {
        super(context);
        monster = _monster;
        setAssets();

        spawn_left = width;
        monster.spawnMonster(spawn_left);

    }

    // Drawing the game
    @Override
    public void onDraw(Canvas canvas) {
        drawStatics(canvas);
        drawMonster(canvas);
        invalidate();
    }

    // This method encapsulates all of the static graphics in the calcGame
    private void drawStatics(Canvas canvas){
        // Position all of the items ( floor, castle, etc. )
        floor_right = canvas.getWidth();
        floor_bottom = canvas.getHeight();
        floor_top = (int) Math.round(0.9*floor_bottom);
        castle_top = floor_bottom - castle_img.getHeight();
        char_left = (int)Math.round(castle_width / 2);
        char_top = castle_top - character_img.getHeight();
        monster_top = (int) ((floor_bottom - floor_top) * 0.75 + floor_top - monster_height);
        // Draw the items
        //canvas.drawBitmap(background,0,0,null);

        // Temporary
        paintSky.setColor(skyblue);
        canvas.drawRect(0,0,floor_right,floor_bottom,paintSky);

        paintFloor.setColor(grassgreen);
        canvas.drawRect(0,floor_top,floor_right,floor_bottom,paintFloor);

        canvas.drawBitmap(castle_img,0,castle_top,null);
        canvas.drawBitmap(character_img,char_left,char_top,null);
    }

    private void drawMonster(Canvas canvas){
        if (monster.getXCoord() <= (castle_width)) {
            monster.respawnMonster(spawn_left);
        }else {
            canvas.drawBitmap(monster_img,monster.getXCoord(),monster_top,null);
            monster.moveMonster(width);
        }

    }

    // This method converts all jpeg and png images into bitmap objects, and prepares them for use. (BROKEN BACKGROUND)
    private void setAssets() {

        // get phone size
        DisplayMetrics phoneSize = getContext().getResources().getDisplayMetrics();
        width =  phoneSize.widthPixels;
        height= phoneSize.heightPixels;

        // GENERATE BACKGROUND
        //background =  BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bg);
        //background = Bitmap.createScaledBitmap(background,1000,1000,true);

        // GENERATE CASTLE
        castle_img = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.castle);
        castle_width = (int)Math.round(0.35*width);
        int castle_height = (int)Math.round(0.35*height);
        castle_img = Bitmap.createScaledBitmap(castle_img,castle_width,castle_height,true);
        makeTransparent(castle_img);

        // GENERATE CHARACTER
        character_img = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.malewizard3);
        char_width = (int)Math.round(0.075*width);
        char_height= (int)Math.round(0.075*height);
        character_img = Bitmap.createScaledBitmap(character_img,char_width,char_height,true);

        // GENERATE MONSTER
        monster_img = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.demon);
        monster_width = (int)Math.round(0.175*width);
        monster_height = (int)Math.round(0.125*height);
        monster_img = Bitmap.createScaledBitmap(monster_img,monster_width,monster_height,true);
    }

    // This method turns all black pixels (0xffffff) into TRANSPARENT
    private void makeTransparent(Bitmap bmp) {

        int height = bmp.getHeight();
        int width = bmp.getWidth();

        for (int y = 0; y < height;y++) {
            for (int x = 0; x < width; x++) {
                if (bmp.getPixel(x,y) == Color.BLACK ) {
                    bmp.setPixel(x,y,Color.TRANSPARENT);
                } else {
                    bmp.setPixel(x,y,bmp.getPixel(x,y));
                }
            }
        }
        bmp.setHasAlpha(true);
    }

}
