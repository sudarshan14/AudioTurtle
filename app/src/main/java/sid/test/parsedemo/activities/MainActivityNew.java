package sid.test.parsedemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.parse.ParseUser;

import sid.test.parsedemo.R;
import sid.test.parsedemo.activity.LoginActivity;
import sid.test.parsedemo.musicplayer.MusicPlayerActivity;

public class MainActivityNew extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, ViewPager.OnPageChangeListener {
    //AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivityNew.class.getSimpleName();
    PageFragment listItem;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private BasicSqlAdapter basicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity1);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        // ParseUser currentUser = ParseUser.getCurrentUser();

        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // NULL POINTER EXCEPTION here
//        getSupportActionBar().setHomeButtonEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        TextView currentUser = (TextView) findViewById(R.id.currentUser);
        currentUser.setText(ParseUser.getCurrentUser().toString());
//        // display the first navigation drawer view on app launch
//        displayView(0);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
   //     tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);

        basicAdapter = new BasicSqlAdapter(getSupportFragmentManager());
        pager.setAdapter(basicAdapter);

        //   tabs.setViewPager(pager);
        pager.setOnPageChangeListener(this);


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
        if (id == R.id.action_logout) {
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser == null) {
                Toast.makeText(MainActivityNew.this, "can not log out", Toast.LENGTH_LONG).show();

            } else {
                //       Toast.makeText(MainActivityNew.this, "can not log out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivityNew.this, LoginActivity.class));
                this.finish();
            }


            return true;
        }

        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    //
    private void displayView(int position) {
//        Fragment fragment = null;
//        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivityNew.this, LoginActivity.class));
                //    this.finish();
//                fragment = new HomeFragment();
//                title = getString(R.string.title_latest);
                break;
            case 1:
                Toast.makeText(getApplicationContext(),
                        "Coming Soon Stay Tuned", Toast.LENGTH_SHORT).show();

                break;
            case 2:
                startActivity(new Intent(MainActivityNew.this, MusicPlayerActivity.class));

                break;
            default:
                break;
        }

    }


    // This method will be invoked when a new page becomes selected.
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Toast.makeText(getApplicationContext(),
//                "scrolled" + position, Toast.LENGTH_SHORT).show();
    }

    // This method will be invoked when the current page is scrolled
    @Override
    public void onPageSelected(int position) {
//        Toast.makeText(getApplicationContext(),
//                "selected" + position, Toast.LENGTH_SHORT).show();
    }

    // Called when the scroll state changes:
    // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
    @Override
    public void onPageScrollStateChanged(int state) {
//        Toast.makeText(getApplicationContext(),
//                "scrolled" + state, Toast.LENGTH_SHORT).show();
    }

    public class BasicSqlAdapter extends FragmentPagerAdapter {
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        private String[] TITLES = {"Channels", "Subscriptions", " Play Later"};

        public BasicSqlAdapter(FragmentManager fm) {
            super(fm);
        }

//        @Override
//        public float getPageWidth(int position) {
//            return 0.93f;
//        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ChannelFragment.newInstance("Page # 1");
                case 1:
                    return SubscriptionFragment.newInstance("Page # 2");
                case 2:
                    return DownloadFragment.newInstance("Page # 3");
                default:
                    return null;
            }

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container,
                    position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }
}