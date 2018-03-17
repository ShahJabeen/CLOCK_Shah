package com.example.shahjabeensajid.clock_shah;

import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.util.DisplayMetrics;
import org.w3c.dom.Text;
import static android.graphics.Color.rgb;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CanvasView cv = new CanvasView(getApplicationContext());
        setContentView(cv);
    }
    public class CanvasView extends View {

        boolean init=false;
        Paint pt;

        //all three constructors are needed here
        public CanvasView(Context context) {
            super(context);
        }

        public CanvasView(Context context, AttributeSet attrs)
        {
            super(context,attrs);
        }

        public CanvasView(Context context, AttributeSet attrs, int defStyleAttr)
        {
            super(context, attrs, defStyleAttr);
        }

        void init()
        {
            pt = new Paint();
            pt.setColor(Color.BLACK);
            pt.setStyle(Paint.Style.FILL);
            pt.setTextSize(400);
            pt.setFakeBoldText(true);

        }


        @Override
        protected void onDraw(Canvas canvas){

            if(!init){
                init();
                init=true;
            }

            canvas.drawColor(Color.WHITE);
            canvas.drawPaint(pt);
            //get the time
            Calendar ctime = Calendar.getInstance();

            String hour = Integer.toString(ctime.get(Calendar.HOUR));
            String min = Integer.toString(ctime.get(Calendar.MINUTE));
            String sec = Integer.toString(ctime.get(Calendar.SECOND));


            int left_hr,right_hr, bottom_hr,top_hr;
            int left_min,right_min, bottom_min,top_min;
            int left_sec,right_sec, bottom_sec,top_sec;

            super.onDraw(canvas);
            Paint pt= new Paint();
            pt.setStyle(Paint.Style.FILL);
            pt.setColor(Color.LTGRAY);


            //paint canvas
            canvas.drawPaint(pt);

            //get the canvas dimensions
            DisplayMetrics dm = this.getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            int height = dm.heightPixels-200;

            left_hr = width/2-400;
            top_hr = height/2+200 - (ctime.get(Calendar.HOUR) *10);
            right_hr = width/2-200;
            bottom_hr = height/2+200;

            left_min = width/2-100;
            top_min = height/2+200 - (ctime.get(Calendar.MINUTE) *10);
            right_min = width/2+100;
            bottom_min = height/2+200;

            left_sec = width/2+200;
            top_sec = height/2+200 - (ctime.get(Calendar.SECOND) *10);
            right_sec = width/2+400;
            bottom_sec = height/2+200;

            pt.setColor(rgb(255,0,0));
            canvas.drawRect(left_hr, top_hr, right_hr,bottom_hr, pt);
            canvas.drawRect(left_min, top_min, right_min,bottom_min, pt);
            canvas.drawRect(left_sec, top_sec, right_sec,bottom_sec, pt);


            if(ctime.get(Calendar.MINUTE)>=10&&ctime.get(Calendar.SECOND)>=10) {
                canvas.drawText(hour + ":" + min + ":" + sec, 300, 200, pt);
            } else if(ctime.get(Calendar.MINUTE)>=10){
                canvas.drawText(hour + ":" + min + ":0" + sec, 300, 200, pt);
            } else {
                canvas.drawText(hour + ":0" + min + ":" + sec, 300, 200, pt);
            }

            pt.setTextSize(150);
            canvas.drawText(hour+" :  " +min + " :  " + sec , 200, 200, pt);
            int AM_PM = ctime.get(Calendar.AM_PM);
            String time;
            if (AM_PM ==1)
            {
                time = "PM";
            }
            else
                time = "AM";
        canvas.drawText(time ,width /2-150 , height/2+500, pt);
           invalidate();
        }
    }
}


