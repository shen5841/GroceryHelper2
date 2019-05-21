package examples.pltw.groceryhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.*;
import android.widget.TextView;
import 	android.widget.FrameLayout;
import android.support.design.widget.CoordinatorLayout;
import 	android.support.design.widget.FloatingActionButton;


/*
adding textbox:
https://stackoverflow.com/questions/6930604/android-add-textview-to-layout-when-button-is-pressed/6932540#6932540
*/

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
/*
    private LinearLayout mLayout;
    private EditText mEditText;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //connects this file to xml

        //sets up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNewItem); //sets up fab/button
        fab.setVisibility(View.GONE);
        //checks if the button is pressed
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //snack bar class shows feedback message
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                startActivity(new Intent(MainActivity.this, Pop.class));
            }
        });

        //sets up drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //manipulates drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //is drawer is open close drawer
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        //open drawer
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //create textView object for the home page
        TextView homePage = (TextView) findViewById(R.id.homeView);
        FrameLayout mainFrame = (FrameLayout) findViewById(R.id.fram);

        //The "Add" button
        FloatingActionButton addItems = findViewById(R.id.addNewItem);
        /*
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) addItems.getLayoutParams();
        p.setAnchorId(View.NO_ID);
        addItems.setLayoutParams(p);
        */

        if (id == R.id.fram) {
            mainFrame.addView(homePage);
            addItems.setVisibility(View.GONE);
        } else if (id == R.id.grocery_list) {
            addItems.setVisibility(View.VISIBLE);
            mainFrame.removeView(homePage);
            setTitle("Grocery List");
            GroceryList groceryListFragment = new GroceryList();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, groceryListFragment, "GroceryList");
            fragmentTransaction.commit();
        } else if (id == R.id.suggestions) {
            addItems.setVisibility(View.GONE);
            mainFrame.removeView(homePage);
            setTitle("Heat Level");
            Suggestions suggestionsFragment = new Suggestions();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, suggestionsFragment, "Suggestions");
            fragmentTransaction.commit();
        } else if (id == R.id.grocery_links) {
            addItems.setVisibility(View.GONE);
            mainFrame.removeView(homePage);
            setTitle("Grocery Links");
            GroceryLinks groceryLinksFragment = new GroceryLinks();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, groceryLinksFragment, "GroceryLinks");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            addItems.setVisibility(View.GONE);
            mainFrame.removeView(homePage);
            setTitle("Send");
            Fragment4 fragment4 = new Fragment4();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment4, "fragment4");
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
