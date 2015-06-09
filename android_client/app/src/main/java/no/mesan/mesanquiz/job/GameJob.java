package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.EventsStoredSimpleFeedback;
import no.mesan.mesanquiz.event.GameEvent;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.service.databaseservice.GameDataBaseService;
import no.mesan.mesanquiz.service.restservice.BaseRestService;

public class GameJob extends AbstractJob {

    public GameJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        GameDto game = BaseRestService.getGameRestService().getGame(1);
        new GameDataBaseService(context).saveGame(game);
        BusProvider.getInstance().post(new EventsStoredSimpleFeedback("Events stored!"));
        BusProvider.getInstance().post(new GameEvent(game));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
