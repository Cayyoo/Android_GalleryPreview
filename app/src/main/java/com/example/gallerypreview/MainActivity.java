package com.example.gallerypreview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.gallerypreview.adapter.MyPagerAdapter;
import com.example.gallerypreview.gallery.GalleryViewPager;
import com.example.gallerypreview.gallery.ScalePageTransformer;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用ViewPager图片的预览，实现类似Gallery的动画效果。
 *
 * 原理介绍：
 * 1、它的作用就是让Viewpager展示多个条目，此PageTransformer是为了在Viewpager外面展示图片。
 * 2、所以PageTransformer并没有改变viewpager的大小状态，
 *    我们在viewpager的父控件中拦截dispatchTouchEvent的方法来控制viewpager的滑动和相应点击事件，
 *    以此来实现示例中两侧的图片即可点击也可滑动的动画效果。
 */
public class MainActivity extends AppCompatActivity {

    private GalleryViewPager mViewPager;
    private SimpleAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        mViewPager = (GalleryViewPager) findViewById(R.id.viewpager);
        mViewPager.setPageTransformer(true, new ScalePageTransformer());

        RelativeLayout relativeLayout= (RelativeLayout) findViewById(R.id.root);
        assert relativeLayout != null;
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        mPagerAdapter = new SimpleAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);

        initData();
    }

    private void initData() {
        List<Integer> list = new ArrayList<>();

        list.add(R.drawable.wo);
        list.add(R.drawable.wo1);
        list.add(R.drawable.wo2);
        list.add(R.drawable.wo3);
        list.add(R.drawable.wo4);
        list.add(R.drawable.wo5);
        list.add(R.drawable.wo);
        list.add(R.drawable.wo1);

        //设置OffscreenPageLimit
        mViewPager.setOffscreenPageLimit(Math.min(list.size(), 5));
        mPagerAdapter.addAll(list);
    }

    public class SimpleAdapter extends MyPagerAdapter {
        private final List<Integer> mList;
        private final Context mContext;

        public SimpleAdapter(Context context) {
            mList = new ArrayList<>();
            mContext = context;
        }

        public void addAll(List<Integer> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup container) {
            ImageView imageView ;

            if (convertView == null) {
                imageView = new ImageView(mContext);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setTag(position);
            imageView.setImageResource(mList.get(position));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((mViewPager.getCurrentItem() ) == position) {
                        Toast.makeText(mContext, "点击的位置是:::" + position, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return imageView;
        }

        @Override

        public int getCount() {
            return mList.size();
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
