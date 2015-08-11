package no.mesan.mesanquiz.view.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.common.ValueHolder;
import no.mesan.mesanquiz.event.LoggedInEvent;
import no.mesan.mesanquiz.view.AbstractActivity;
import no.mesan.mesanquiz.view.MenuAdapter;
import no.mesan.mesanquiz.view.model.MenuItem;
import no.mesan.mesanquiz.view.profile.LoginFragment;
import no.mesan.mesanquiz.view.question.QuestionFragment;

import static no.mesan.mesanquiz.common.Constants.AUTHORITY_URL;
import static no.mesan.mesanquiz.common.Constants.CLIENT_ID;
import static no.mesan.mesanquiz.common.Constants.REDIRECT_URL;
import static no.mesan.mesanquiz.common.Constants.RESOURCE_ID;

public class MainActivity extends AbstractActivity {

    private List<MenuItem> menuItems;
    private MenuAdapter menuAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String title;
    private AuthenticationContext authenticationContext;
    private AuthenticationResult authenticationResult;
    private ProgressDialog loginProgressDialog;
    private Context context;

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
        context = this;

        setSupportActionBar(toolbar);

        setTitle("");

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        if (isFirstTimeAppStart()) {
            menuAdapter.selectMenuItem(0);
        }

        try {
            // init authentication Context
            authenticationContext = new AuthenticationContext(context, AUTHORITY_URL, false);
        } catch (Exception e) {
            Toast.makeText(context, "Encryption failed", Toast.LENGTH_SHORT).show();
        }

        goToFragment(LoginFragment.class, false);
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

    public void goToFragment(Class<? extends Fragment> fragment, boolean animate) {
        goToFragment(fragment, null, animate);
    }

    public void goToFragment(Class<? extends Fragment> fragment, Bundle bundle, boolean animate) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (animate) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        try {
            Fragment fragmentInstance = fragment.newInstance();
            if(bundle != null) {
                fragmentInstance.setArguments(bundle);
            }
            transaction.replace(R.id.mainContainer, fragmentInstance, fragment.getName());
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(getClass().getName(), "Failed to create fragment instance: ", e);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        authenticationContext.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private AuthenticationCallback<AuthenticationResult> getCallback() {
        return new AuthenticationCallback<AuthenticationResult>() {

            @Override
            public void onError(Exception exc) {
                if (loginProgressDialog != null && loginProgressDialog.isShowing()) {
                    loginProgressDialog.dismiss();
                }

                Toast.makeText(context, "getToken Error:" + exc.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(AuthenticationResult result) {
                if (loginProgressDialog != null && loginProgressDialog.isShowing()) {
                    loginProgressDialog.dismiss();
                }

                authenticationResult = result;
                ValueHolder valueHolder = ValueHolder.getInstance(context);
                valueHolder.setAuthenticationResult(authenticationResult);
                valueHolder.setAccessToken(authenticationResult.getAccessToken());

                if (authenticationResult.getUserInfo() != null) {
                    updateLoginButton();
                    goToFragment(QuestionFragment.class, true);
                }
                BusProvider.getInstance().post(new LoggedInEvent());
            }
        };
    }

    private void updateLoginButton() {
//        loginButton.setText(authenticationResult.getUserInfo().getGivenName());
    }

    public void loginClicked() {

        loginProgressDialog = new ProgressDialog(context);
        loginProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loginProgressDialog.setMessage(getString(R.string.login_in_progress));
        loginProgressDialog.show();

        authenticationContext
                .acquireToken(this, RESOURCE_ID, CLIENT_ID, REDIRECT_URL, "", getCallback());
    }
}
