package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import java.util.List;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.EventsStoredSimpleFeedback;
import no.mesan.mesanquiz.event.GameListEvent;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.service.databaseservice.GameDataBaseService;
import no.mesan.mesanquiz.service.restservice.BaseRestService;

/**
 * Created by janeh on 11.08.2015.
 */
public class GameListJob extends AbstractJob {

    public GameListJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        List<GameDto> games = BaseRestService.getGameRestService().getGames();
        new GameDataBaseService(context).saveGames(games);
        BusProvider.getInstance().post(new EventsStoredSimpleFeedback("Events stored!"));
        BusProvider.getInstance().post(new GameListEvent(games));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
