package vn.edu.huflit.foozie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class intro_screen extends AppCompatActivity {
    private ViewPager introPager;
    introViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext,btnStart;
    int position=0;
    Animation btnAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make the ativity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(restorePrefData()){
            Intent loginActivity=new Intent(getApplicationContext(), activity_signin.class);
            startActivity(loginActivity);
            finish();
        }

        setContentView(R.layout.activity_intro_screen);
        //ini Views
        btnNext=findViewById(R.id.btn_next);
        tabIndicator=findViewById(R.id.tabIndicator);
        btnStart=findViewById(R.id.btn_start);
        btnAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_anim);
        //fill List screen
        List<intro_item> intro_itemList= new ArrayList<>();
        intro_itemList.add(new intro_item("Xin Chào!","Chào mừng bạn đến với ứng dụng đặt đồ ăn trực tuyến, hãy để chúng tôi tự giới thiệu đôi chút nhé.",R.drawable.img1));
        intro_itemList.add(new intro_item("Đặt món, nhận thưởng","Sau khi đặt hàng, bạn sẽ đạt được số điểm nhất định tùy thuộc vào cấp độ của tài khoản, cấp độ càng cao thì điểm tích lũy càng nhiều và sẽ có nhiều phần thưởng hấp dẫn.",R.drawable.img2));
        intro_itemList.add(new intro_item("Nhân viên thân thiện","Đội ngũ nhân viên chuyên nghiệp, thân thiện và hết mình vì khách hàng.",R.drawable.img3));
        intro_itemList.add(new intro_item("Cùng trải nghiệm ngay","Chúng tôi cảm ơn bạn vì đã chọn dịch vụ của chúng tôi và chúc bạn có những trải nghiệm tuyệt vời.",R.drawable.img4));
        //setup viewpager
        introPager = findViewById(R.id.introViewPager);
        introViewPagerAdapter=new introViewPagerAdapter(this,intro_itemList);
        introPager.setAdapter(introViewPagerAdapter);
        //setup tablayout with viewpager
        tabIndicator.setupWithViewPager(introPager);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position=introPager.getCurrentItem();
                if(position<intro_itemList.size()){
                    position++;
                    introPager.setCurrentItem(position);
                }
                if(position==intro_itemList.size()-1){
                    loadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==intro_itemList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_screen=new Intent(getApplicationContext(), activity_signin.class);
                startActivity(login_screen);
                savePrefsData();
                finish();
            }

            private void savePrefsData() {
                SharedPreferences pref=getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putBoolean("isIntroOpened",true);
                editor.commit();
            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref=getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore=pref.getBoolean("isIntroOpened",false);
        return isIntroActivityOpenedBefore;
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        btnStart.setAnimation(btnAnim);
    }
}