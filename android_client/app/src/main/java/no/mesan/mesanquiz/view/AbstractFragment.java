package no.mesan.mesanquiz.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.path.android.jobqueue.JobManager;

import butterknife.ButterKnife;
import no.mesan.mesanquiz.common.BusProvider;

/**
 * Created by kristint on 5/12/2015.
 */
public abstract class AbstractFragment extends Fragment {
    protected Context context;
    private JobManager jobManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        View view = inflater.inflate(getViewId(), container, false);

        ButterKnife.inject(this, view);
        jobManager = new JobManager(getContext());

        return view;
    }

    protected abstract int getViewId();

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    public Context getContext() {
        return context;
    }

    public JobManager getJobManager() {
        return jobManager;
    }
}
