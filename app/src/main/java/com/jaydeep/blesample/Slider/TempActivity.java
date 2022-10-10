package com.jaydeep.blesample.Slider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaydeep.blesample.R;

public class TempActivity extends AppCompatActivity {
    private CircularSeekBar mSeekArc;
    private TextView mSeekArcProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        mSeekArc = (CircularSeekBar) findViewById(R.id.seekBar);
        mSeekArcProgress = (TextView) findViewById(R.id.txtProgress);
        mSeekArcProgress.setText("0°C");

        mSeekArc.setOnProgressChangeListener(new CircularSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, int progress, boolean isUser) {
                mSeekArcProgress.setText(String.valueOf(progress)+"°C");
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }
        });



    }


}
