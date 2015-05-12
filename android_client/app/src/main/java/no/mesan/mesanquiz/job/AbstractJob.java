package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

public abstract class AbstractJob extends Job {
    public Context context;

    public static final int HIGH_PRIORITY = 1;
    public static final int MEDIUM_PRIORITY = 2;
    public static final int LOW_PRIORITY = 3;

    private int retries = 0;

    protected AbstractJob(Context context, int priority) {
        super(new Params(priority));
        this.context = context;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        work();
    }

    @Override
    protected void onCancel() {
        retries = 0;
        onError();
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        Log.d("KLOVN", "error", throwable);
        retries++;
        return retries <= 1;
    }

    protected abstract void work() throws Throwable;
    protected abstract void onError();
}
