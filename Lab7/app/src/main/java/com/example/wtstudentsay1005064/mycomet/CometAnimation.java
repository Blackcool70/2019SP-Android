package com.example.wtstudentsay1005064.mycomet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class CometAnimation  extends View {
    private float r,fWidth,fHeight,Angle,X,Y,radians,alpha,beta;
    private Context c;
    RGB ballrgb = new RGB(128, 128, 128, 1, 1, 1);
    RGB bgrgb   = new RGB(128, 128, 128, -1, -1, -1);

    public CometAnimation(Context context, float w,float h){
        super(context);
        c       = context;
        fWidth  = w;
        fHeight = h - 200;
        r       = 100;
        Angle   = 0;
        radians = (float) Math.toRadians(Angle);
        alpha   = (fWidth / 2 - 120);
        beta    = (fHeight / 2 - 120);
        updateCordinates(radians,alpha,beta);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        paint.setStyle(Paint.Style.FILL);

        double frameRate = 120;
        double period = 1.0 / frameRate;
        int speed = 1;

        ballrgb = updateColors(ballrgb, 8, 30, 230, 12);
        bgrgb   = updateColors(bgrgb, 2, 70, 200, 50);

        paint.setColor(Color.rgb(ballrgb.R, ballrgb.G, ballrgb.B));
        paint2.setColor(Color.rgb(0,0,0));
        setBackgroundColor(Color.rgb(bgrgb.G, bgrgb.B, bgrgb.R));
        canvas.drawCircle(X,Y,r+10,paint2);
        canvas.drawCircle(X,Y,r,paint);


        Angle = (( Angle + speed ) % 360 );
        float radians = (float) Math.toRadians(Angle);
        updateCordinates(radians,alpha,beta);

        try{
            Thread.sleep((long)(period * 1000));
        }
        catch(InterruptedException ie){
            Toast.makeText(c, "Fatal Error", Toast.LENGTH_SHORT).show();
        }
        // invalidate the whole view and invoke the callback onDraw
        invalidate();

    }
    private RGB updateColors(RGB rgb, int mutation, int min, int max, int flip_percent) {
        Random rand =  new Random();
        int mutationR = mutation * (rand.nextInt(3)+1);
        int mutationG = mutation * (rand.nextInt(3)+1);
        int mutationB = mutation * (rand.nextInt(3)+1);

        rgb.R += rand.nextInt(mutationR) * rgb.flipR;
        rgb.G += rand.nextInt(mutationG) * rgb.flipG;
        rgb.B += rand.nextInt(mutationB) * rgb.flipB;

        if (rand.nextInt(100) < flip_percent) { rgb.flipR *= 1; }
        if (rand.nextInt(100) < flip_percent) { rgb.flipG *= 1; }
        if (rand.nextInt(100) < flip_percent) { rgb.flipB *= 1; }

        if (rgb.R > max) { rgb.flipR *= -1; rgb.R = max; }
        if (rgb.R < min) { rgb.flipR *= -1; rgb.R = min; }
        if (rgb.G > max) { rgb.flipG *= -1; rgb.G = max; }
        if (rgb.G < min) { rgb.flipG *= -1; rgb.G = min; }
        if (rgb.B > max) { rgb.flipB *= -1; rgb.B = max; }
        if (rgb.B < min) { rgb.flipB *= -1; rgb.B = min; }

        return rgb;
    }

    private static class RGB {
        int R, G, B;
        int flipR, flipG, flipB;

        public RGB(int R, int G, int B, int flipR, int flipG, int flipB) {
            this.R = R;
            this.G = G;
            this.B = B;
            this.flipR = flipR;
            this.flipG = flipG;
            this.flipB = flipB;
        }
    }

    private void updateCordinates(float radians, float alpha, float beta){
        float shiftX = fWidth  / 2;
        float shiftY = fHeight / 2;
        X = alpha * (float)Math.cos(radians);
        Y = beta  * (float)Math.sin(radians);
        X = shiftX + X;
        Y = shiftY + Y;
    }
}
