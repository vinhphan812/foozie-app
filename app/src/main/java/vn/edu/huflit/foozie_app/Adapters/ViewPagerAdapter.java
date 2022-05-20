package vn.edu.huflit.foozie_app.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vn.edu.huflit.foozie_app.Fragments.homepage.AccountFragment;
import vn.edu.huflit.foozie_app.Fragments.homepage.VoucherFragment;
import vn.edu.huflit.foozie_app.Fragments.homepage.NotificationsFragment;
import vn.edu.huflit.foozie_app.Fragments.homepage.HomeFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new NotificationsFragment();
            case 2:
                return new VoucherFragment();
            case 3:
                return new AccountFragment();
            default:
                return new HomeFragment();

        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
