package no.mesan.mesanquiz.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.path.android.jobqueue.JobManager;

import butterknife.ButterKnife;
import no.mesan.mesanquiz.common.BusProvider;

/**
 * Created by n06849 on 22.04.2015.
 */
public abstract class AbstractActivity extends Activity {

    private JobManager jobManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        ButterKnife.inject(this);
        jobManager = new JobManager(getApplicationContext());
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
