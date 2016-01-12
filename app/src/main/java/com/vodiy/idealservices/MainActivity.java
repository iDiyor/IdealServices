package com.vodiy.idealservices;


import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.vodiy.idealservices.fragment.MainFragment;
import com.vodiy.idealservices.fragment.ServiceListFragment;
import com.vodiy.idealservices.navigation.NavAdapter;
import com.vodiy.idealservices.service.Services;


public class MainActivity extends AppCompatActivity {

    private String[] mNavMenuTitles;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;

    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mActivityTitle = getTitle().toString();

        mNavMenuTitles = getResources().getStringArray(R.array.nav_tab_names);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        int[] navIcons = {
                R.mipmap.nav_icon,
                R.mipmap.nav_icon,
                R.mipmap.nav_icon,
                R.mipmap.nav_icon,
                R.mipmap.nav_icon,
                R.mipmap.nav_icon,
                R.mipmap.nav_icon,
                R.mipmap.nav_icon
        };

        // Set the adapter for the list view
        NavAdapter adapter = new NavAdapter(this, getResources().getStringArray(R.array.nav_tab_names), navIcons);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position--;
                onServiceTabClick(position);
                mDrawerLayout.closeDrawers();
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // Get a support ActionBar corresponding to this toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        MainFragment mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, mainFragment);
        fragmentTransaction.commit();
    }

    public void onServiceTabClick(int position) {
        String serviceName = "";
        switch (position) {
            case 0:
                serviceName = Services.IT_SERVICE;
                break;
            case 1:
                serviceName = Services.HOUSEHOLD_SERVICE;
                break;
            case 2:
                serviceName = Services.MEDIA_SERVICE;
                break;
            case 3:
                serviceName = Services.BUSINESS_SERVICE;
                break;
            case 4:
                serviceName = Services.TRANSPORT_SERVICE;
                break;
            case 5:
                serviceName = Services.HEALTHCARE_SERVICE;
                break;
            case 6:
                serviceName = Services.BUILDING_SERVICE;
                break;
            case 7:
                serviceName = Services.EDUCATION_SERVICE;
                break;
            default:
                serviceName = Services.IT_SERVICE;
                break;
        }

        showFragmentByServiceName(serviceName);
        mActivityTitle = serviceName;
    }

    public void onServiceButtonClick(View view) {
        Button button = (Button) view;
        String serviceName = button.getText().toString();
        showFragmentByServiceName(serviceName);

    }

    public void showFragmentByServiceName(String serviceName) {
        // Create new fragment and transaction
        ServiceListFragment newFragment = new ServiceListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        Bundle args = new Bundle();
        args.putString("ServiceName", serviceName);
        newFragment.setArguments(args);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

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

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
