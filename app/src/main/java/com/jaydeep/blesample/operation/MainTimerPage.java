package com.jaydeep.blesample.operation;

import static com.jaydeep.blesample.operation.TransationPage.flag_page_change;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jaydeep.blesample.R;
import com.jaydeep.blesample.operation.TimerPages.TimerFirstFragment;

public class MainTimerPage extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                    ViewGroup rootWiew = (ViewGroup) inflater.
                    inflate(R.layout.main_timer_page, container
                            , false);


        final TransationPage transationPage=new TransationPage();
        LinearLayout layout=(LinearLayout)rootWiew.findViewById(R.id.timerLayout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        //pointer down.
                        flag_page_change=true;
                        transationPage.Dur();
                        transationPage.Devam();
                        System.out.println("Touch is end Action");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        //pointer down.
                        flag_page_change=false;
                        break;
                }
                // transationPage.Dur();
                return true;
            }
        });

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TimerFirstFragment timerFirstFragment = new TimerFirstFragment();
        fragmentTransaction.replace(R.id.frame_layoutTimer, timerFirstFragment).commit();
            return rootWiew;

    }

}
