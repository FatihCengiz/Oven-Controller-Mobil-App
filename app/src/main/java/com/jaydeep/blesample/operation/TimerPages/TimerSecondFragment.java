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

import com.jaydeep.blesample.R;

public class TimerSecondFragment extends Fragment {
    ListView timerList;
    String[] buttons={"H.A.Cooking","A.Cooking","Alarm"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_timer_second,container,false);

        timerList=(ListView) viewGroup.findViewById(R.id.timerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.my_custom_timer_layout, buttons);
        timerList.setAdapter(adapter);

        timerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position=i;
                String button_name=(String) timerList.getItemAtPosition(i);
                switch(position) {
                    case 0:
                       // goToTimerSettings();
                        break;
                    case 2:
                      //  goToTimerSettings();
                        break;
                    case 3:
                       // goToTimerSettings();
                        break;

                }
            }
        });

        return viewGroup;
    }
}
