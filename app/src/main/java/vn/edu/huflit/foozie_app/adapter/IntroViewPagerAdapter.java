package vn.edu.huflit.foozie_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Models.intro_item;

public class IntroViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<intro_item> intro_itemList;

    public IntroViewPagerAdapter(Context mContext, List<intro_item> intro_itemList) {
        this.mContext = mContext;
        this.intro_itemList = intro_itemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutIntro = inflater.inflate(R.layout.layout_intro, null);
        ImageView posterSlide = layoutIntro.findViewById(R.id.poster);
        TextView title = layoutIntro.findViewById(R.id.title);
        TextView description = layoutIntro.findViewById(R.id.description);

        title.setText(intro_itemList.get(position).getTitle());
        description.setText(intro_itemList.get(position).getDescription());
        posterSlide.setImageResource(intro_itemList.get(position).getPoster());

        container.addView(layoutIntro);
        return layoutIntro;
    }

    @Override
    public int getCount() {
        return intro_itemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
