package com.jaydeep.blesample.operation;

import static com.jaydeep.blesample.operation.TransationPage.dataReadWrite;
import static com.jaydeep.blesample.operation.TransationPage.flag_page_change;
import static com.jaydeep.blesample.operation.TransationPage.positionPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jaydeep.blesample.MainActivity;
import com.jaydeep.blesample.R;

public class MainFavPage extends Fragment {

    Button btn_press;
    int position;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootWiew=(ViewGroup) inflater.
                inflate(R.layout.main_fav_page,container
                        ,false);
        final TransationPage transationPage=new TransationPage();
        System.out.println("BaşlangıçFav : "+transationPage.pageData);

        LinearLayout layout=(LinearLayout)rootWiew.findViewById(R.id.favLayout);
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


        btn_press=(Button) rootWiew.findViewById(R.id.btnPress);
        btn_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataReadWrite = "M17?";
                transationPage.writeData();
                positionPage=0;
                Intent intent=new Intent(getActivity(), TransitionPageFav.class);
                startActivity(intent);

            }
        });

        return rootWiew;
    }

    public void hello(){
        System.out.println("Hello");

    }



}
