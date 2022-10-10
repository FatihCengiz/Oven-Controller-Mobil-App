package com.jaydeep.blesample.operation;

import static com.jaydeep.blesample.operation.CharacteristicOperationFragment.HexToString;
import static com.jaydeep.blesample.operation.CharacteristicOperationFragment.globalBleDevice;
import static com.jaydeep.blesample.operation.CharacteristicOperationFragment.globalCharacteristic;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;
import com.jaydeep.blesample.R;
import com.jaydeep.blesample.adapter.SlidePageAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransationPage extends AppCompatActivity {
    Button button;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    public static String dataReadWrite;
    public static int tempData,positionPage=0;
    String[] dataStringArray,separated;
    public static Handler handler;
    public static Runnable runnable,runnableWrite;
    static int currentPage,mcuDataPage,pageState,headerData,nextData;
    int sayacWrite=0,sayac,pageData,pageDataChange;
    static int mainPage1,mainPage2,mainPage3,mainPage4;
    public static Boolean flag_thread_ble=true,flag_page_change=true;
    Boolean is_menu_state=true,flag_data_control=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transation_page);
        //Notification bar close
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final List<Fragment> list=new ArrayList<>();
        list.add(new MainTempPage());
        list.add(new MainTimerPage());
        list.add(new MainSettingsPage());
        list.add(new MainFavPage());


        pager=findViewById(R.id.pager);
        pagerAdapter=new SlidePageAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);
        pageData= pager.getCurrentItem();
        button=findViewById(R.id.page3);
        readDataMainThread();

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            public void onPageSelected(int position) {
               //for (int i=0;i<list.size();i++){
                if(position==0){
                   if(is_menu_state==true){

                         dataReadWrite = "M1?";
                         positionPage = 0;
                         System.out.println("position : " + position);
                         showData();
                         flag_page_change=true;

                    }

                    pager.setCurrentItem(0, true);

                }
                else if(position==1){

                    if(is_menu_state==true){

                            dataReadWrite = "M2?";
                            positionPage = 1;
                            System.out.println("position : " + position);
                            showData();
                            flag_page_change=true;

                    }
                    pager.setCurrentItem(1, true);

                }
                else if(position==2){
                    if(is_menu_state==true){

                            dataReadWrite = "M3?";
                            System.out.println("position : " + position);
                            showData();
                            flag_page_change=true;


                    }
                    pager.setCurrentItem(2, true);
                }
                else if(position==3){
                    if(is_menu_state==true){

                            dataReadWrite = "M4?";
                            System.out.println("position : " + position);
                            showData();
                            flag_page_change=true;


                    }
                    pager.setCurrentItem(3, true);
                    }

                }

          //  }
        });


    }
    public void readDataMainThread(){

        final Handler hnd=new Handler();
        runnableWrite= new Runnable() {
            @Override
            public void run() {
                button.setText("flag : " + flag_page_change);
                hnd.postDelayed(runnableWrite,1000);
            }
        };
        hnd.post(runnableWrite);

        //Handler
        //  dataReadWrite="1/1/0/0/0/0/0/0/0/0/";
        // writeData();

       /* if(dataReadWrite==null){
            System.out.println("data is null");
        }
        else {*/
        handler=new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {


                if (flag_thread_ble == true && flag_page_change==true) {
                    System.out.println("Read Handler Start and Flag is " + flag_thread_ble );

                    dataStringArray = new String[1];
                    readData();
                    System.out.println("data  = " + dataReadWrite);
                    handler.postDelayed(runnable,1000);
                }
                else {
                    System.out.println("flag is ble : " +flag_thread_ble+ " Flag is menu data : " + is_menu_state);
                }
            }
        };
        handler.post(runnable);
        // }


    }
    public void Dur(){
        handler.removeCallbacks(runnable);
    }
    public void Devam(){
        handler.post(runnable);
    }

    public void showData(){
        writeData();
        stop();
        flag_page_change=true;
    }
    public void stop(){
        handler.removeCallbacks(runnable);
        flag_thread_ble=false;
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                flag_thread_ble=true;
                handler.removeCallbacks(runnable);
                handler.post(runnable);

            }
        },100);
    }


    public void writeData(){

        System.out.println("Write");


                    BleManager.getInstance().write(
                            globalBleDevice,
                            globalCharacteristic.getService().getUuid().toString(),
                            globalCharacteristic.getUuid().toString(),
                            dataReadWrite,
                            new BleWriteCallback() {

                                @Override
                                public void onWriteSuccess(final int current, final int total, final byte[] justWrite) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    });
                                }

                                @Override
                                public void onWriteFailure(final BleException exception) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //addText(txt, exception.toString());
                                        }
                                    });
                                }
                            });

    }
    public void readData(){

        BleManager.getInstance().read(
                globalBleDevice,
                globalCharacteristic.getService().getUuid().toString(),
                globalCharacteristic.getUuid().toString(),
                new BleReadCallback() {
                    @Override
                    public void onReadSuccess(final byte[] data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("Read run start");
                                for (int i = 0; i < dataStringArray.length; i++) {
                                  //  System.out.println("In read flag : "+is_menu_state);


                                    dataStringArray[i] = HexToString(HexUtil.formatHexString(data));
                                    dataReadWrite=HexToString(HexUtil.formatHexString(data));
                                    currentPage = pager.getCurrentItem();
                                    pageState = currentPage + 1;
                                    flag_page_change=true;
                                    stringToChar();
                                    if(flag_data_control==false) {
                                        separated = dataStringArray[i].split("/");
                                        headerData = Integer.parseInt(separated[0]);
                                        mcuDataPage = Integer.parseInt(separated[1]);
                                        tempData = Integer.parseInt(separated[9]);
                                        System.out.println("temp state : " + separated[9]+ " tempData : " + tempData);
                                        System.out.println("menu state : " + separated[1]);
                                       // System.out.println("header state : " + separated[0]);
                                       // System.out.println("current page :" + pageState);
                                        //boolean match = (separated[1] == null ? num == null : separated[1].equals(num));
                                        // System.out.println("Sonuc" + match);
                                        if (pageState != mcuDataPage) {
                                            is_menu_state = false;
                                          //  System.out.println("Sayfa değişimi = " + separated[1]);
                                            pager.setCurrentItem(mcuDataPage - 1, true);
                                        } else {
                                            is_menu_state = true;
                                         //   System.out.println("Data is same.");
                                        }
                                    }
                                    else {
                                        is_menu_state = true;
                                        System.out.println("Data is string.");
                                    }

                                }

                            }
                        });
                    }

                    @Override
                    public void onReadFailure(final BleException exception) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //   addText(txt_read, exception.toString());
                            }
                        });
                    }
                });
        }

    public void stringToChar()
    {
        String str = dataReadWrite;
        // Creating array of string length
        char[] ch = new char[str.length()];
        // Copy character by character into array
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
            if (ch[0]>='A' && ch[0]<='Z'){
                flag_data_control=true;
            }
            else{
                flag_data_control=false;
            }
        }
    }


   /* public void btn_readData(View view) {
        System.out.println("Read");
        final String[] MenuId = new String[1];

        if (TextUtils.isEmpty(dataReadWrite)) {
            return;
        }
        BleManager.getInstance().read(
                globalBleDevice,
                globalCharacteristic.getService().getUuid().toString(),
                globalCharacteristic.getUuid().toString(),
                new BleReadCallback() {


                    @Override
                    public void onReadSuccess(final byte[] data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i=0;i<MenuId.length;i++){

                                    MenuId[i] = HexToString(HexUtil.formatHexString(data));

                                    boolean match = (MenuId[i] == null ? num == null : MenuId[i].equals(num));
                                    System.out.println("Sonuc" + match);
                                    if( match==true){
                                        System.out.println("True1 = "+MenuId[i]);
                                        nextPageFunc();
                                    } else {
                                        System.out.println("False  " );
                                    }

                                }

                            }
                        });
                    }

                    @Override
                    public void onReadFailure(final BleException exception) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //addText(txt_read, exception.toString());
                            }
                        });
                    }
                });
    }*/

    public void nextPage(View view) {
      //  data=data+1;
      //  System.out.println("currentPageArtırıldı : " + currentPage );
        //pager.setCurrentItem(2,true);
      //  handler.removeCallbacks(runnable);
       // System.out.println("Handler stop");
    }
    public void nextPageFunc() {
        //pager.setCurrentItem(pager.getCurrentItem()+1,true);
        pager.setCurrentItem(2,true);

    }

    private void addText(TextView textView, String content) {
        textView.append(content);
        textView.append("\n");
        int offset = textView.getLineCount() * textView.getLineHeight();
        if (offset > textView.getHeight()) {
            textView.scrollTo(0, offset - textView.getHeight());
        }
    }

    public void nextPage1(View view) {
        dataReadWrite="1/2/0/0/0/0/0/0/0/50";
        showData();
       // pager.beginFakeDrag();

    }

    public void nextPage2(View view) {
        dataReadWrite="1/3/0/0/0/0/0/0/0/75";
        showData();


    }

}
/*
    <Button
        android:layout_width="200dp"
        android:layout_height="100dp"
        android:text="page3"
        android:id="@+id/page3"
        android:layout_marginTop="100dp"
        android:layout_marginLeft="-780dp"
        android:onClick="nextPage1"
        />
    <Button
        android:layout_width="200dp"
        android:layout_height="100dp"
        android:id="@+id/page4"
        android:layout_marginTop="210dp"
        android:layout_marginLeft="-180dp"
        android:text="page4"
        android:onClick="nextPage2"
        />

        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                MainTempPage mainTempPage=new MainTempPage();
                return mainTempPage;
            case 1: // Fragment # 0 - This will show FirstFragment different title
                if(i==5){MainFavPage mainFavPage=new MainFavPage();
                    return mainFavPage;}
                else {
                    MainTimerPage mainTimerPage=new MainTimerPage();
                    return  mainTimerPage;

                }
            case 2: // Fragment # 1 - This will show SecondFragment
                MainSettingsPage mainSettingsPage=new MainSettingsPage();
                return mainSettingsPage;
            case 3: // Fragment # 1 - This will show SecondFragment
                MainFavPage mainFavPage=new MainFavPage();
                return mainFavPage;
            default:
                return null;
        }


         <ImageView
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:src="@drawable/modemenu_aktif_1"
            android:id="@+id/modemenu_aktif_1"
            android:layout_marginRight="30dp"
            app:layout_constraintStart_toStartOf="@+id/pager"
            app:layout_constraintTop_toTopOf="@+id/pager"/>
        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/solusticon_op"
            android:id="@+id/solusticon_op"
            android:layout_marginRight="15dp"/>
        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/solusticon_yop"
            android:id="@+id/solusticon_yop"
            android:layout_marginRight="85dp"/>
        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/solusticon_lamp"
            android:id="@+id/solusticon_lamp"
            android:layout_marginRight="20dp"/>

        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/solusticon_eco"
            android:id="@+id/solusticon_eco"
            android:layout_marginRight="70dp"/>
        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/solusticon_bluetooth"
            android:id="@+id/solusticon_bluetooth"
            android:layout_marginRight="30dp"/>
        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/solusticon_eng"
            android:id="@+id/solusticon_eng"
            android:layout_marginRight="40dp"/>
 */
