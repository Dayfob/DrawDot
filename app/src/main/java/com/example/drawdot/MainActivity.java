package com.example.drawdot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.drawdot.Dots;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final int DOT_DIAMETER = 6;
    private final Random rand = new Random();

    final Dots dotModel = new Dots();

    DotView dotView;

    private LinearLayoutCompat root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = new DotView(this, dotModel);

        ((LinearLayoutCompat) findViewById(R.id.root)).addView(dotView, 2);

        ((Button) findViewById(R.id.button1)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDot(dotModel, dotView, Color.RED);
            }
        });
        ((Button) findViewById(R.id.button2)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDot(dotModel, dotView, Color.GREEN);
            }
        });

        final EditText tb1 = findViewById(R.id.text1);
        final EditText tb2 = findViewById(R.id.text2);

        dotModel.setDotsChangeListener(new Dots.DotsChangeListener() {
            @Override
            public void onDotsChange(Dots dots) {
                Dot d = dots.getLastDot();
                tb1.setText((null == d) ? "" : String.valueOf(d.getX()));
                tb2.setText((null == d) ? "" : String.valueOf(d.getY()));
                dotView.invalidate();
            }
        });
    }

    void makeDot(Dots dots, DotView view, int color) {
        int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDots(
                DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
                DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
                color,
                DOT_DIAMETER
        );
    }

//    private class DotsChangeListener{
//        @Override
//        void onDotsChange(){
//            EditText editText = findViewById(R.id.text1);
//            editText.setText(getLast);
//        }
//    }

//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
//        int measuredHeight = measureHeigt(heightMeasureSpec);
//        int measuredWidth = measureWidth(widthMeasureSpec);
//        setMeasuredDimension(measuredHeight, measuredWidth);
//    }


//    private static final class TrackingTouchListener implements View.OnTouchListener{
//        private final Dots mDots;
//
//        TrackingTouchListener(Dots dots){
//            mDots = dots;
//        }
//        @Override
//        public boolean onTouch(View viewm MotionEvent evt){
//            switch (evt.getAction()){
//                case MotionEvent.ACTION_DOWN: break;
//                case MotionEvent.ACTION_MOVE:
//                    for (int i = 0, n = evt.getHistorySize(); i < n; i++){
//                        addDot(
//                                mDots,
//                                evt.
//                        )
//                    }
//            }
//        }
//    }
}

