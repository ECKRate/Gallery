package com.local.gallery;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<String> imageUrls = new ArrayList<>();

    ViewPagerAdapter(List<String> imageUrls) {
        this.imageUrls.addAll(imageUrls);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_gallery_image, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        Picasso.get().load(imageUrls.get(position)).into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    int addItem(String url) {
        if (!TextUtils.isEmpty(url)) {
            int position = imageUrls.size();
            imageUrls.add(position, url);
            notifyDataSetChanged();
            return position;
        }
        return 0;
    }

    void removeLast() {
        if (imageUrls.size() > 0) {
            imageUrls.remove(imageUrls.size() - 1);
            notifyDataSetChanged();
        }
    }
}
