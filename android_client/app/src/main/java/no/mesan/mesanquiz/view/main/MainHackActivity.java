package no.mesan.mesanquiz.view.main;
/*
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import butterknife.OnClick;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.event.EventsStoredSimpleFeedback;
import no.mesan.mesanquiz.event.ProgramEvent;
import no.mesan.mesanquiz.event.ProgramFromDatabaseEvent;
import no.mesan.mesanquiz.job.GetProgramFromDatabaseJob;
import no.mesan.mesanquiz.job.ProgramJob;
import no.mesan.mesanquiz.view.AbstractActivity;


public class MainHackActivity extends AbstractActivity {

    @InjectView(R.id.textViewHello)
    TextView textViewHello;

    @Override
    protected int getViewId() {
        return R.layout.activity_main_hack;
    }

    @OnClick(R.id.buttonGetData)
    public void getData() {
        getJobManager().addJobInBackground(new ProgramJob(getApplicationContext()));
    }

    @OnClick(R.id.buttonValidateDataBase)
    public void validateData() {
        getJobManager().addJobInBackground(new GetProgramFromDatabaseJob(getApplicationContext()));
    }


    @Subscribe
    public void programFetched(ProgramEvent programEvent) {
        if (programEvent.hasErrors()) {
            Log.d("KLOVN", "ERROR: " + programEvent.getErrorMessage());
            return;
        }

        textViewHello.setText("Antall innlegg: " + (programEvent.getGameDto().getEvents() !=null? programEvent.getGameDto().getEvents().size() : "null"));
    }

    @Subscribe
    public void programFromDbFetched(ProgramFromDatabaseEvent programEvent) {

        if (programEvent.hasErrors()) {
            Log.d("KLOVN", "ERROR: " + programEvent.getErrorMessage());
            return;
        }

        textViewHello.setText("Antall innlegg i database: " + (programEvent.getGameDto().getEvents() !=null? programEvent.getGameDto().getEvents().size() : "null"));
    }

    @Subscribe
    public void programStored(EventsStoredSimpleFeedback feedback) {
        Toast.makeText(getApplicationContext(), feedback.getMessageToUser(), Toast.LENGTH_LONG).show();
    }
}
*/