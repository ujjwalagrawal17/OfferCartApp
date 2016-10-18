package com.example.aman.offercart_v1.WelcomeScreen.view;

/**
 * Created by aman on 12/10/16.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman.offercart_v1.LoginScreen.view.view1.LoginScreenViewImpl;
import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.SharedPrefs;
import com.example.aman.offercart_v1.WelcomeScreen.models.RetrofitWelcomeScreenProvider;
import com.example.aman.offercart_v1.WelcomeScreen.models.data.WelcomeImageDetails;
import com.example.aman.offercart_v1.WelcomeScreen.models.data.WelcomeScreenData;
import com.example.aman.offercart_v1.WelcomeScreen.presenter.WelcomeScreenPresenter;
import com.example.aman.offercart_v1.WelcomeScreen.presenter.WelcomeScreenPresenterImpl;

import java.util.List;

public class WelcomeScreenActivity extends Activity implements WelcomeScreenView{

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btn_login;
    private SharedPrefs prefManager;
    private WelcomeScreenPresenter welcomeScreenPresenter;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        String s=getIntent().getExtras().getString("mobile");
//        Log.d("dd",s);

//         Checking for first time launch - before calling setContentView()
        prefManager = new SharedPrefs(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btn_login = (Button) findViewById(R.id.btn_login);
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};
        // adding bottom dots
        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);



        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    launchHomeScreen();

            }
        });
    }

    private void addBottomDots(int currentPage)
    {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        //prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeScreenActivity.this, LoginScreenViewImpl.class));
        finish();
    }





    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageSelected(int position)
        {
            addBottomDots(position);


        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {

        }

        @Override
        public void onPageScrollStateChanged(int arg0)
        {

        }
    };

    @Override
    public void showMessage(String message) {
        Toast.makeText(WelcomeScreenActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(List<WelcomeImageDetails> welcomeImageDetails)
    {

    }


    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void initialize() {
        prefManager = new SharedPrefs(this);
        welcomeScreenPresenter = new WelcomeScreenPresenterImpl(this, new RetrofitWelcomeScreenProvider());
//        homeDetailsAdapter = new WelcomeImageDetailsAdapter(this);


    }



}