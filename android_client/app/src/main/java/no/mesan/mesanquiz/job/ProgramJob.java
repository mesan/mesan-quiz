package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.EventsStoredSimpleFeedback;
import no.mesan.mesanquiz.event.ProgramEvent;
import no.mesan.mesanquiz.model.ProgramDto;
import no.mesan.mesanquiz.service.restservice.BaseRestService;
import no.mesan.mesanquiz.service.databaseservice.ProgramDataBaseService;

public class ProgramJob extends AbstractJob {

    public ProgramJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        ProgramDto program = BaseRestService.getProgramService().getProgram();
        new ProgramDataBaseService(context).saveProgram(program);
        BusProvider.getInstance().post(new EventsStoredSimpleFeedback("Events stored!"));
        BusProvider.getInstance().post(new ProgramEvent(program));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
