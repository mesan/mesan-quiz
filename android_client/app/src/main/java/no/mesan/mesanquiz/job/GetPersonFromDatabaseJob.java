package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.GameFromDatabaseEvent;
import no.mesan.mesanquiz.event.PersonFromDatabaseEvent;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.PersonDto;
import no.mesan.mesanquiz.service.databaseservice.GameDataBaseService;
import no.mesan.mesanquiz.service.databaseservice.PersonDataBaseService;

public class GetPersonFromDatabaseJob extends AbstractJob {

    public GetPersonFromDatabaseJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        PersonDto personDto = new PersonDataBaseService(context).getPeople();
        BusProvider.getInstance().post(new PersonFromDatabaseEvent(personDto));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
