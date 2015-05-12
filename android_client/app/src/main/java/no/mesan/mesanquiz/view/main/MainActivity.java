package no.mesan.mesanquiz.view.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.view.AbstractActivity;
import no.mesan.mesanquiz.view.MenuAdapter;

public class MainActivity extends AbstractActivity {

    private List<no.mesan.mesanquiz.view.model.MenuItem> menuItems;
    private Context context;
    private MenuAdapter menuAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String title;

    @InjectView(R.id.quiz_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.navigationDrawer)
    protected RelativeLayout navigationDrawer;

    @InjectView(R.id.navigationDrawerMenu)
    protected ListView navigationDrawerMenu;

    @InjectView(R.id.drawerLayout)
    protected DrawerLayout drawerLayout;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);

        setTitle("");
        context = this;

        menuItems = new ArrayList<>();
/*        menuItems.add(new MenuItem(getString(R.string.menu_program), R.drawable.menu_program), ProgramFragment_.class);
        menuItems.add(new MenuItem(getString(R.string.menu_push), R.drawable.menu_received));
        menuItems.add(new MenuItem(getString(R.string.menu_persons), R.drawable.menu_persons));*/

        menuAdapter = new MenuAdapter(getSupportFragmentManager());
        menuAdapter.setData(menuItems);
        // Set the menuAdapter for the list view
        navigationDrawerMenu.setAdapter(menuAdapter);

        // Set the list's click listener
        navigationDrawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                menuAdapter.selectMenuItem(position);
                // Highlight the selected item, update the title, and close the drawer
                navigationDrawerMenu.setItemChecked(position, true);
                drawerLayout.closeDrawer(navigationDrawer);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                drawerLayout, /* DrawerLayout object */
                toolbar, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("");
                invalidateOptionsMenu();
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (isFirstTimeAppStart()) {
            menuAdapter.selectMenuItem(0);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title.toString();
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content
        // view
        // if (drawerLayout != null) {
        // boolean drawerOpen = drawerLayout.isDrawerOpen(navigationDrawer);
        // }
        // menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * When back pressed on the last fragment; exit the app.
     * <p/>
     * We could avoid adding the first fragment to backstack, but this creates a
     * weird drawing bug.
     */
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        }
        super.onBackPressed();
    }
}
