package com.jaydeep.blesample.operation.TimerPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jaydeep.blesample.R;

public class TimerFirstFragment extends Fragment {

    ListView pressList;
    String[] buttons={"Press","Forward Button","to Enter"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_timer_first,container,false);


        pressList=(ListView) viewGroup.findViewById(R.id.pressList);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.my_custom_timer_layout, buttons);

        pressList.setAdapter(adapter);

        pressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position=i;
                String button_name=(String) pressList.getItemAtPosition(i);
                switch(position) {
                    case 0:
                        goToTimerSettings();
                        break;
                    case 2:
                        goToTimerSettings();
                        break;
                    case 3:
                        goToTimerSettings();
                        break;

                }
            }
        });

        return viewGroup;
    }
    public void goToTimerSettings() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TimerSecondFragment firstFragment = new TimerSecondFragment();
        fragmentTransaction.replace(R.id.frame_layoutTimer,firstFragment).commit();



    }
}

// android:layout_height="?android:attr/listPreferredItemHeight"
