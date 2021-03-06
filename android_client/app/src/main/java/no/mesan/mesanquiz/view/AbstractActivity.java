package no.mesan.mesanquiz.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.path.android.jobqueue.JobManager;

import butterknife.ButterKnife;
import no.mesan.mesanquiz.common.BusProvider;

public abstract class AbstractActivity extends ActionBarActivity {

    private JobManager jobManager;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        ButterKnife.inject(this);
        jobManager = new JobManager(getApplicationContext());
        this.savedInstanceState = savedInstanceState;
        init();
    }

    protected abstract void init();

    protected boolean isFirstTimeAppStart() {
        return savedInstanceState == null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    public Context getContext() {
        return getApplicationContext();
    }
    public JobManager getJobManager() {
        return jobManager;
    }

    protected abstract int getViewId();
}
