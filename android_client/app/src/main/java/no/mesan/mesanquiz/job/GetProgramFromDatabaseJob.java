package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.EventsStoredSimpleFeedback;
import no.mesan.mesanquiz.event.ProgramEvent;
import no.mesan.mesanquiz.event.ProgramFromDatabaseEvent;
import no.mesan.mesanquiz.model.ProgramDto;
import no.mesan.mesanquiz.service.BaseRestService;
import no.mesan.mesanquiz.service.databaseservice.ProgramDataBaseService;

/**
 * Created by n06849 on 22.04.2015.
 */
public class GetProgramFromDatabaseJob extends AbstractJob {

    public GetProgramFromDatabaseJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        ProgramDto programFromDb = new ProgramDataBaseService(context).getProgram();
        BusProvider.getInstance().post(new ProgramFromDatabaseEvent(programFromDb));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
