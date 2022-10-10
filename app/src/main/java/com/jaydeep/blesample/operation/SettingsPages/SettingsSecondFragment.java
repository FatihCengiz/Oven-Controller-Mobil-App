package com.jaydeep.blesample.operation.SettingsPages;



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
import com.jaydeep.blesample.adapter.SlidePageAdapter;
import com.jaydeep.blesample.operation.MainFavPage;
import com.jaydeep.blesample.operation.MainSettingsPage;
import com.jaydeep.blesample.operation.MainTempPage;
import com.jaydeep.blesample.operation.MainTimerPage;

import java.util.ArrayList;
import java.util.List;

public class SettingsSecondFragment extends Fragment {
    ListView settingsList;
    String[] buttons={"Economic","Clock","Sound","Brightness","Bluetooth","Language","NightMode","Sleep Time","Unit Temp"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_settings_second,container,false);

        settingsList=(ListView) viewGroup.findViewById(R.id.settingsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.my_custom_timer_layout, buttons);
        settingsList.setAdapter(adapter);







        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position=i;
                String button_name=(String) settingsList.getItemAtPosition(i);
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
