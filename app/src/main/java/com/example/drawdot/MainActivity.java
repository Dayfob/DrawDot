package com.example.drawdot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final int DOT_DIAMETER = 6;
    private final Random rand = new Random();

    final Dots dotModel = new Dots();

    DotView dotView;

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

        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEvent.ACTION_DOWN != motionEvent.getAction()) {
                    return false;
                }
                dotModel.addDots(motionEvent.getX(), motionEvent.getY(), Color.CYAN, DOT_DIAMETER);
                return true;
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

    private static final class TrackingTouchListener implements View.OnTouchListener {
        private final Dots mDots;

        private TrackingTouchListener(Dots mDots) {
            this.mDots = mDots;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    for (int i = 0, n = motionEvent.getHistorySize(); i < n; i++) {
                        addDot(
                                mDots,
                                motionEvent.getHistoricalX(i),
                                motionEvent.getHistoricalY(i),
                                motionEvent.getHistoricalPressure(i),
                                motionEvent.getHistoricalSize(i));
                    }
                    break;
                default:
                    return false;
            }
            addDot(
                    mDots,
                    motionEvent.getX(),
                    motionEvent.getY(),
                    motionEvent.getPressure(),
                    motionEvent.getSize());
            return true;
        }

        private void addDot(Dots dots, float x, float y, float p, float s) {
            mDots.addDots(x, y, Color.CYAN, (int) ((p * s * DOT_DIAMETER) + 1));
        }
    }
}


