package com.jaydeep.blesample.operation;

import static com.jaydeep.blesample.operation.CharacteristicOperationFragment.globalBleDevice;
import static com.jaydeep.blesample.operation.CharacteristicOperationFragment.globalCharacteristic;
import static com.jaydeep.blesample.operation.TransationPage.dataReadWrite;
import static com.jaydeep.blesample.operation.TransationPage.positionPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.exception.BleException;
import com.jaydeep.blesample.R;
import com.jaydeep.blesample.adapter.SlidePageAdapterFav;
import com.jaydeep.blesample.operation.FavPages.FavPageBread;
import com.jaydeep.blesample.operation.FavPages.FavPageCake;
import com.jaydeep.blesample.operation.FavPages.FavPageChicken;
import com.jaydeep.blesample.operation.FavPages.FavPageCookie;
import com.jaydeep.blesample.operation.FavPages.FavPageFish;
import com.jaydeep.blesample.operation.FavPages.FavPageMakarna;
import com.jaydeep.blesample.operation.FavPages.FavPageMeat;
import com.jaydeep.blesample.operation.FavPages.FavPagePie;
import com.jaydeep.blesample.operation.FavPages.FavPagePizza;
import com.jaydeep.blesample.operation.FavPages.FavPageVega;
import com.jaydeep.blesample.operation.FavPages.PageDeneme;

import java.util.ArrayList;
import java.util.List;

public class TransitionPageFav extends AppCompatActivity {
    ViewPager viewPagerFav;
    PagerAdapter pagerAdapterFav;
    String favPageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_page_fav);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        List<Fragment> listPageFav=new ArrayList<>();
        listPageFav.add(new FavPageMeat());
        listPageFav.add(new FavPageChicken());
        listPageFav.add(new FavPageFish());
        listPageFav.add(new FavPageVega());
        listPageFav.add(new FavPageMakarna());
        listPageFav.add(new FavPagePizza());
        listPageFav.add(new FavPageCake());
        listPageFav.add(new FavPageCookie());
        listPageFav.add(new FavPagePie());
        listPageFav.add(new FavPageBread());
        final TransationPage transationPage=new TransationPage();

        viewPagerFav=findViewById(R.id.pagerFav);
        pagerAdapterFav=new SlidePageAdapterFav(getSupportFragmentManager(),listPageFav);
        viewPagerFav.setAdapter(pagerAdapterFav);



        viewPagerFav.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){

                        dataReadWrite = "M17?";
                        transationPage.writeData();

                }
                else if(position==1){
                    positionPage=1;
                    dataReadWrite="M18?";
                    transationPage.writeData();
                }
               else if(position==2){dataReadWrite="M19?";
                    transationPage.writeData();
               }
               else if(position==3){
                   dataReadWrite="M20?";
                    transationPage.writeData();
               }
               else if(position==4){dataReadWrite="M21?";
                    transationPage.writeData();}
               else if(position==5){dataReadWrite="M22?";
                    transationPage.writeData();}
               else if(position==6){dataReadWrite="M23?";
                    transationPage.writeData();}
                else if(position==7){dataReadWrite="M24?";
                    transationPage.writeData();}
               else if(position==8){dataReadWrite="M25?";
                    transationPage.writeData();}
               else if(position==9){dataReadWrite="M26?";
                    transationPage.writeData();}


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}