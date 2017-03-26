package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import calculotprototype.g14.cmpt276.calculot_prototype.R;


/**
 * Created by Jean Pierre on 2017-03-25.
 */

public class calcGameGraphics extends View {

    Bitmap background;
    Bitmap castle;

    public calcGameGraphics(Context context) {
        super(context);
        setAssets();
    }

    @Override
    public void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        drawStatics(canvas);
        invalidate();
    }

    // This method encapsulates all of the static graphics in the calcGame
    private void drawStatics(Canvas canvas){
        // Position all of the items ( floor, castle, etc. )
        int floor_right = canvas.getWidth();
        int floor_bottom = canvas.getHeight();
        int floor_start = (int) Math.round(0.9*floor_bottom);

        int castle_top = floor_bottom - castle.getHeight();

        // Set the color for the floor
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        // Draw the items
        canvas.drawBitmap(background,0,0,null);
        canvas.drawRect(0,floor_start,floor_right,floor_bottom,paint);
        canvas.drawBitmap(castle,0,castle_top,null);
    }


    // This method converts all jpeg and png images into bitmap objects, and prepares them for use.
    private void setAssets() {
        background =  BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bg);
        background = Bitmap.createScaledBitmap(background,1000,1000,true);

        castle = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.castle);
        castle = Bitmap.createScaledBitmap(castle,200,400,true);
        makeTransparent(castle);
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
