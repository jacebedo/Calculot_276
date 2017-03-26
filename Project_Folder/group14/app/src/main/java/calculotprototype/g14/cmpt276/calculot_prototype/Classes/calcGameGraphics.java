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

    // Asset Members
    Bitmap background;
    Bitmap castle;
    Bitmap character;
    Bitmap monster;

    // Static Color Members
    Paint paintFloor = new Paint();
    Paint paintSky = new Paint();
    int skyblue = getContext().getResources().getColor(R.color.skyblue);
    int grassgreen = getContext().getResources().getColor(R.color.grassgreen);

    // Positioning Members
    int floor_right;
    int floor_bottom;
    int floor_start;
    int castle_top;
    int char_left;
    int char_top;

    // Scaling Members
    int width;
    int height;
    int castle_width;
    int castle_height;
    int char_width;
    int char_height;
    int monster_width;
    int monster_height;


    // Initialization
    public calcGameGraphics(Context context) {
        super(context);
        setAssets();
    }

    // Drawing the game
    @Override
    public void onDraw(Canvas canvas) {
        drawStatics(canvas);
        invalidate();
    }

    // This method encapsulates all of the static graphics in the calcGame
    private void drawStatics(Canvas canvas){
        // Position all of the items ( floor, castle, etc. )
        floor_right = canvas.getWidth();
        floor_bottom = canvas.getHeight();
        floor_start = (int) Math.round(0.9*floor_bottom);
        castle_top = floor_bottom - castle.getHeight();
        char_left = (int)Math.round(castle_width / 2);
        char_top = castle_top - character.getHeight();

        // Draw the items
        //canvas.drawBitmap(background,0,0,null);

        // Temporary
        paintSky.setColor(skyblue);
        canvas.drawRect(0,0,floor_right,floor_bottom,paintSky);

        paintFloor.setColor(grassgreen);
        canvas.drawRect(0,floor_start,floor_right,floor_bottom,paintFloor);

        canvas.drawBitmap(castle,0,castle_top,null);
        canvas.drawBitmap(character,char_left,char_top,null);
        canvas.drawBitmap(monster,0,0,null);
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
        castle = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.castle);
        castle_width = (int)Math.round(0.35*width);
        int castle_height = (int)Math.round(0.35*height);
        castle = Bitmap.createScaledBitmap(castle,castle_width,castle_height,true);
        makeTransparent(castle);

        // GENERATE CHARACTER
        character = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.malewizard3);
        char_width = (int)Math.round(0.075*width);
        char_height= (int)Math.round(0.075*height);
        character = Bitmap.createScaledBitmap(character,char_width,char_height,true);

        // GENERATE MONSTER
        monster = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.demon);
        monster_width = (int)Math.round(0.175*width);
        monster_height = (int)Math.round(0.125*height);
        monster = Bitmap.createScaledBitmap(monster,monster_width,monster_height,true);
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
