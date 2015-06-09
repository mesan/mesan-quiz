package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.EventsStoredSimpleFeedback;
import no.mesan.mesanquiz.event.PersonEvent;
import no.mesan.mesanquiz.model.PersonDto;
import no.mesan.mesanquiz.service.restservice.BaseRestService;
import no.mesan.mesanquiz.service.databaseservice.PersonDataBaseService;

public class PersonJob extends AbstractJob {

    public PersonJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        PersonDto person = BaseRestService.getPeopleService().getPerson(1);
        new PersonDataBaseService(context).savePeople(person);
        BusProvider.getInstance().post(new EventsStoredSimpleFeedback("Events stored!"));
        BusProvider.getInstance().post(new PersonEvent(person));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
