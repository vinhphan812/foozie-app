package vn.edu.huflit.foozie_app.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vn.edu.huflit.foozie_app.fragments.homepage.AccountFragment;
import vn.edu.huflit.foozie_app.fragments.homepage.VoucherFragment;
import vn.edu.huflit.foozie_app.fragments.homepage.NotificationsFragment;
import vn.edu.huflit.foozie_app.fragments.homepage.ActivityFragment;
import vn.edu.huflit.foozie_app.fragments.homepage.HomeFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new ActivityFragment();
            case 2:
                return new NotificationsFragment();
            case 3:
                return new VoucherFragment();
            case 4:
                return new AccountFragment();
            default:
                return new HomeFragment();

        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
