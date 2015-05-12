package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.GameFromDatabaseEvent;
import no.mesan.mesanquiz.event.ProgramFromDatabaseEvent;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.ProgramDto;
import no.mesan.mesanquiz.service.databaseservice.GameDataBaseService;
import no.mesan.mesanquiz.service.databaseservice.ProgramDataBaseService;

public class GetGameFromDatabaseJob extends AbstractJob {

    public GetGameFromDatabaseJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        GameDto gameDto = new GameDataBaseService(context).getGames();
        BusProvider.getInstance().post(new GameFromDatabaseEvent(gameDto));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
