package com.jaydeep.blesample.operation.FavPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jaydeep.blesample.R;


public class FavPageBread extends Fragment {
    Button buttonSet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootWiew=(ViewGroup) inflater.
                inflate(R.layout.fav_page_bread,container
                        ,false);




        return rootWiew;
    }


}
