package no.mesan.mesanquiz.view.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.common.ValueHolder;
import no.mesan.mesanquiz.event.LoggedInEvent;
import no.mesan.mesanquiz.view.AbstractFragment;
import no.mesan.mesanquiz.view.main.LoginActivity;

public class LoginFragment extends AbstractFragment {

    private ValueHolder valueHolder;
    private boolean awaitingLogin;

    @InjectView(R.id.loginButton)
    protected Button loginButton;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        valueHolder = ValueHolder.getInstance(context);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });


        return view;
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_login;
    }

    public void loginClicked() {
        if (valueHolder != null && valueHolder.getAuthenticationResult() != null) {
            // TODO: go to list of games
//            ((LoginActivity)getActivity().l)
        }
        else {
            ((LoginActivity)getActivity()).loginClicked();
            awaitingLogin = true;
        }
    }

    @Subscribe
    public void loggedIn(LoggedInEvent event) {
        if (awaitingLogin) {
            awaitingLogin = false;
            loginClicked();
        }
    }
}
