package com.local.gallery;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.photosTextView)
    TextView photosTextView;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.imageUrlEditText)
    EditText imageUrlEditText;

    @BindView(R.id.addImageButton)
    Button addImageButton;

    private ViewPagerAdapter viewPagerAdapter;

    private String[] imageUrls = {
            //"https://cdn.pixabay.com/photo/2017/07/01/19/48/background-2462436_1280.jpg",
            "https://cdn.pixabay.com/photo/2017/07/01/19/48/background-2462431_1280.jpg",
            "https://cdn.pixabay.com/photo/2017/07/01/19/48/background-2462434_1280.jpg",
            "https://cdn.pixabay.com/photo/2015/03/26/09/45/chainlink-690238_1280.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPagerAdapter = new ViewPagerAdapter(Arrays.asList(imageUrls));
        viewPager.setAdapter(viewPagerAdapter);

        photosTextView.setText(getString(R.string.photos, viewPagerAdapter.getCount()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_remove) {
            viewPagerAdapter.removeLast();
            photosTextView.setText(getString(R.string.photos, viewPagerAdapter.getCount()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.addImageButton)
    public void addImageButton() {
        int pageIndex = viewPagerAdapter.addItem(imageUrlEditText.getText().toString());
        viewPager.setCurrentItem(pageIndex, true);
        photosTextView.setText(getString(R.string.photos, viewPagerAdapter.getCount()));
        imageUrlEditText.setText("");
    }
}
