package com.kedrad.hoptest;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity implements SingleLegHopForDistanceFragment.OnFragmentInteractionListener,
        TimedOneLeggedHopFragment.OnFragmentInteractionListener, TripleHopForDistanceFragment.OnFragmentInteractionListener,
        CrossoverHopFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private int currentFragment = 0;
    private Fragment[] fragments = new Fragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting screen orientation to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                //Calling appropriate methods in the instance of the fragment that is currently displayed to get te result.
                switch (currentFragment){
                    case 0:
                        SingleLegHopForDistanceFragment f1 = (SingleLegHopForDistanceFragment) fragments[0];
                        if(f1.validateInput())
                            f1.calculateResult();
                        else
                            Snackbar.make(view, R.string.empty_edittexts_message, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        break;
                    case 1:
                        TimedOneLeggedHopFragment f2 = (TimedOneLeggedHopFragment) fragments[1];
                        if(f2.validateInput())
                            f2.calculateResult();
                        else
                            Snackbar.make(view, R.string.empty_edittexts_message, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        break;
                    case 2:
                        TripleHopForDistanceFragment f3 = (TripleHopForDistanceFragment) fragments[2];
                        if(f3.validateInput())
                            f3.calculateResult();
                        else
                            Snackbar.make(view, R.string.empty_edittexts_message, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        break;
                    case 3:
                        TripleHopForDistanceFragment f4 = (TripleHopForDistanceFragment) fragments[3];
                        if(f4.validateInput())
                            f4.calculateResult();
                        else
                            Snackbar.make(view, R.string.empty_edittexts_message, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        break;
                    default:
                        SingleLegHopForDistanceFragment f5 = (SingleLegHopForDistanceFragment) fragments[0];
                        if(f5.validateInput())
                            f5.calculateResult();
                        else
                            Snackbar.make(view, R.string.empty_edittexts_message, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        break;
                }


            }
        });

        //Changing title in the toolbar for the name of the first test and then changing it on every swipe.
        toolbar.setTitle(R.string.title_single_leg);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toolbar toolbar = findViewById(R.id.toolbar);

                switch (position){
                    case 0:
                        toolbar.setTitle(R.string.title_single_leg);
                        currentFragment = 0;
                        break;
                    case 1:
                        toolbar.setTitle(R.string.title_timed);
                        currentFragment = 1;
                        break;
                    case 2:
                        currentFragment = 2;
                        toolbar.setTitle(R.string.title_triple_hop_for_Distance);
                        break;
                    case 3:
                        currentFragment = 3;
                        toolbar.setTitle(R.string.title_crossover);
                        break;
                    default:
                        currentFragment = 0;
                        toolbar.setTitle(R.string.title_single_leg);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.

            switch (position){
                case 0:
                    fragments[0] = SingleLegHopForDistanceFragment.newInstance();
                    return fragments[0];
                case 1:
                    fragments[1] = TimedOneLeggedHopFragment.newInstance();
                    return fragments[1];
                case 2:
                    fragments[2] = TripleHopForDistanceFragment.newInstance();
                    return fragments[2];
                case 3:
                    fragments[3] = CrossoverHopFragment.newInstance();
                    return fragments[3];
                default:
                    fragments[0] = SingleLegHopForDistanceFragment.newInstance();
                    return fragments[0];
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

    }


    void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onFragmentInteraction(Uri uri){
    }



}
