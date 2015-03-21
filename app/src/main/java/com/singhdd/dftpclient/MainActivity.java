package com.singhdd.dftpclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.singhdd.dftpclient.common.view.SlidingTabLayout;

import java.io.Serializable;

import static com.singhdd.dftpclient.RemoteFragment.*;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private NavigationDrawerFragment mNavigationDrawerFragmentRight;

    RemoteFragment tab1;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;

    static final String LOG_TAG = "SlidingTabsMainActivity";

    CharSequence Titles[]={"Remote","Local"};
    int Numboftabs =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragmentRight = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_right);

        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mNavigationDrawerFragmentRight.setUp(
                R.id.navigation_drawer_right,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ViewPagerAdapter adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(adapter);

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, RemoteFragment.newInstance(position + 1))
                .commit();*/
        Toast.makeText(MainActivity.this,"Selected Position = "+(position+1), Toast.LENGTH_SHORT).show();

        if(tab1 != null) {
            if(position == 0) {
               tab1.connectFTP("server.vigaas.com", "admin", "ER.dds1ng", "21");
            }
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
//            return true;
//        }
        return super.onCreateOptionsMenu(menu);
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


    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
        int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


        // Build a Constructor and assign the passed Values to appropriate values in the class
        public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
            super(fm);

            this.Titles = mTitles;
            this.NumbOfTabs = mNumbOfTabsumb;

        }

        //This method return the fragment for the every position in the View Pager
        @Override
        public Fragment getItem(int position) {

            if(position == 0) // if the position is 0 we are returning the First tab
            {
                tab1 = new RemoteFragment();
                return tab1;
            }
            else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            {
                LocalFragment tab2 = new LocalFragment();
                return tab2;
            }


        }

        // This method return the titles for the Tabs in the Tab Strip

        @Override
        public CharSequence getPageTitle(int position) {
            return Titles[position];
        }

        // This method return the Number of tabs for the tabs Strip

        @Override
        public int getCount() {
            return NumbOfTabs;
        }
    }

}
