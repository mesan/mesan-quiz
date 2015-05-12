package no.mesan.mesanquiz.view.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.view.AbstractActivity;
import no.mesan.mesanquiz.view.question.QuestionFragment;

/**
 * Created by mikkels on 12/05/15.
 */
public class QuizActivity extends AbstractActivity {

    @InjectView(R.id.quiz_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.toolbar_title)
    TextView titleView;

    @Override
    protected void init() {
        setSupportActionBar(toolbar);

        titleView.setText("Navn på quiz");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        goToFragment(QuestionFragment.class, false);
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

}
