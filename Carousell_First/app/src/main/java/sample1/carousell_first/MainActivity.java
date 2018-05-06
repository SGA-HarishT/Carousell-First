package sample1.carousell_first;


import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;

/**
 * MainActivity to Display Fragments
 */

public class MainActivity extends AppCompatActivity implements OneFragment.DataPassListener{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    ViewPagerAdapter mViewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewpager);
        mViewpagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewpagerAdapter);
        mTabLayout = findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    public void passData(HashMap map) {
        Log.d("CHECK","Pass Data Entered");
        String tag = "android:switcher:" + R.id.viewpager + ":" + 1;
        TwoFragment f = (TwoFragment) getSupportFragmentManager().findFragmentByTag(tag);
        Bundle bundle = new Bundle();
        bundle.putSerializable(f.DATA_RECEIVE, map);
        f.setArguments(bundle);
        Log.d("CHECK","Pass Data Bundle values: "+bundle);
        f.displayReceivedData(bundle);
    }
}