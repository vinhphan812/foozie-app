package vn.edu.huflit.foozie_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mbottomNavigationView;
    private ViewPager mviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbottomNavigationView=findViewById(R.id.bottom_nav);
        mviewPager=findViewById(R.id.view_pager);
        setUpViewPager();
        mbottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mviewPager.setCurrentItem(0);
                        break;
                    case R.id.action_activity:
                        mviewPager.setCurrentItem(1);
                        break;
                    case R.id.action_payment:
                        mviewPager.setCurrentItem(2);
                        break;
                    case R.id.action_gift:
                        mviewPager.setCurrentItem(3);
                        break;
                    case R.id.action_account:
                        mviewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(viewPagerAdapter);
        mviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mbottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mbottomNavigationView.getMenu().findItem(R.id.action_activity).setChecked(true);
                        break;
                    case 2:
                        mbottomNavigationView.getMenu().findItem(R.id.action_payment).setChecked(true);
                        break;
                    case 3:
                        mbottomNavigationView.getMenu().findItem(R.id.action_gift).setChecked(true);
                        break;
                    case 4:
                        mbottomNavigationView.getMenu().findItem(R.id.action_account).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}