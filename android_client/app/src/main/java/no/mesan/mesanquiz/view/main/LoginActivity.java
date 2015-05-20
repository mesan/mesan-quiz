package no.mesan.mesanquiz.view.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.common.ValueHolder;
import no.mesan.mesanquiz.event.LoggedInEvent;
import no.mesan.mesanquiz.view.AbstractActivity;
import no.mesan.mesanquiz.view.profile.LoginFragment;
import no.mesan.mesanquiz.view.question.QuestionFragment;

import static no.mesan.mesanquiz.common.Constants.AUTHORITY_URL;
import static no.mesan.mesanquiz.common.Constants.CLIENT_ID;
import static no.mesan.mesanquiz.common.Constants.REDIRECT_URL;
import static no.mesan.mesanquiz.common.Constants.RESOURCE_ID;

public class LoginActivity extends AbstractActivity {
    @InjectView(R.id.quiz_toolbar)
    Toolbar toolbar;

    private AuthenticationContext authenticationContext;
    private AuthenticationResult authenticationResult;
    private ProgressDialog loginProgressDialog;
    private Context context;

    @Override
    protected void init() {
        context = this;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        try {
            // init authentication Context
            authenticationContext = new AuthenticationContext(context, AUTHORITY_URL, false);
        } catch (Exception e) {
            Toast.makeText(context, "Encryption failed", Toast.LENGTH_SHORT).show();
        }

        goToFragment(LoginFragment.class, false);
    }

    @Override
    protected int getViewId() {
        return R.layout.quiz;
    }

    private void goToFragment(Class<? extends Fragment> fragment, boolean animate) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (animate) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        try {
            Fragment fragmentInstance = fragment.newInstance();
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
