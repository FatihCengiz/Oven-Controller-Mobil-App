package com.jaydeep.blesample.operation.TimerPages;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydeep.blesample.R;

public class TimerMenuActivity extends AppCompatActivity {


    ListView listemiz;
    String[] ulkeler={"Almanya","Turkiye","Rusya"};
    TextView yazi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_item_list);
        yazi=(TextView) findViewById(R.id.yazi);

      /*  listemiz=(ListView) findViewById(R.id.listemiz);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.my_custom_layout,ulkeler);
        listemiz.setAdapter(adapter);

        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int sira=i;
                String ulke_ismi=(String) listemiz.getItemAtPosition(i);
            }
        });*/

    }
}