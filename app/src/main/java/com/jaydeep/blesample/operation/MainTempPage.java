package com.jaydeep.blesample.operation;

import static com.jaydeep.blesample.operation.TransationPage.dataReadWrite;
import static com.jaydeep.blesample.operation.TransationPage.flag_page_change;
import static com.jaydeep.blesample.operation.TransationPage.handler;
import static com.jaydeep.blesample.operation.TransationPage.positionPage;
import static com.jaydeep.blesample.operation.TransationPage.runnable;
import static com.jaydeep.blesample.operation.TransationPage.tempData;


import com.jaydeep.blesample.R;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.jaydeep.blesample.Slider.CircularSeekBar;


public class MainTempPage extends Fragment {
    private CircularSeekBar mSeekArc;
    private TextView mSeekArcProgress;
    private Handler handlerTemp;
    private Runnable runnableTemp;
    int temp=0,sonuc=0;
    String[] dataStringArray,separated;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootWiew = (ViewGroup) inflater.
                inflate(R.layout.main_temp_page, container
                        , false);

            readTemp();//Temp read thread
            final TransationPage transationPage = new TransationPage();
            mSeekArc = (CircularSeekBar) rootWiew.findViewById(R.id.seekBar);

            mSeekArcProgress = (TextView) rootWiew.findViewById(R.id.txtProgress);
            mSeekArcProgress.setText("0°C");

            LinearLayout layout=(LinearLayout)rootWiew.findViewById(R.id.tempLayout);
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
            mSeekArc.setOnProgressChangeListener(new CircularSeekBar.OnProgressChangeListener() {
                @Override
                public void onProgressChanged(CircularSeekBar seekBar, int progress, boolean isUser) {
                  if(positionPage==0) {
                        dataReadWrite = String.valueOf("R" + progress + "?");
                       // System.out.println("ekran çıktısı " + dataReadWrite);
                       // System.out.println("Page2 de bu veri basıldı.");

                        transationPage.writeData();
                    }

                }

                @Override
                public void onStartTrackingTouch(CircularSeekBar seekBar) {
                   // transationPage.stop();
                    positionPage=0;
                    handlerTemp.removeCallbacks(runnableTemp);


                }

                @Override
                public void onStopTrackingTouch(CircularSeekBar seekBar) {
                    handlerTemp.post(runnableTemp);
                  //  handler.post(runnable);
                  //  handlerTemp.post(runnableTemp);
                   // System.out.println("StopTrackinggg");

                }
            });


        return rootWiew;

    }
    public void readTemp(){
        handlerTemp = new Handler();
        runnableTemp = new Runnable() {
            @Override
            public void run() {
                System.out.println("temp handler start = " + tempData + " and positon : " + positionPage);
                System.out.println("flag page change : "+ flag_page_change);
                mSeekArcProgress.setText(String.valueOf(tempData+200 + "°C"));
                if(tempData!=temp && temp<243){

                    sonuc=tempData/8;
                    mSeekArc.setProgress(sonuc);
                    temp=tempData;
                }
                else if(tempData>243){
                    mSeekArc.setProgress(30);
                }

                handlerTemp.postDelayed(runnableTemp, 1000);

            }
        };
        handlerTemp.post(runnableTemp);
    }


   /* private void runOnUiThread(Runnable runnable) {
        if (isAdded() && getActivity() != null)
            getActivity().runOnUiThread(runnable);

    }
    private void addText(TextView textView, String content) {
        textView.append(content);
        textView.append("\n");
        int offset = textView.getLineCount() * textView.getLineHeight();
        if (offset > textView.getHeight()) {
            textView.scrollTo(0, offset - textView.getHeight());
        }
    }


    public void writeData(){

        BleManager.getInstance().write(
                globalBleDevice,
                globalCharacteristic.getService().getUuid().toString(),
                globalCharacteristic.getUuid().toString(),
                dataReadWriteP1,
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
    }*/


   /* public void readData(){

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
                                  TransationPage transationPage=new TransationPage();


                                    stringToChar();
                                    if(transationPage.flag_data_control==false) {
                                        separated = dataStringArray[i].split("/");

                                        tempData = Integer.parseInt(separated[2]);
                                        System.out.println("temp state : " + separated[2]+ " tempData : " + tempData);
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
                                        //  System.out.println("Data is same.");
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
    }*/

    }



