package no.mesan.mesanquiz.job;

import android.content.Context;
import android.util.Log;

import java.util.List;

import no.mesan.mesanquiz.common.BusProvider;
import no.mesan.mesanquiz.event.EventsStoredSimpleFeedback;
import no.mesan.mesanquiz.event.GameEvent;
import no.mesan.mesanquiz.event.ScoreEvent;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.ScoreDto;
import no.mesan.mesanquiz.service.databaseservice.GameDataBaseService;
import no.mesan.mesanquiz.service.restservice.BaseRestService;

public class ScoreJob extends AbstractJob {

    public ScoreJob(Context context) {
        super(context, HIGH_PRIORITY);
    }

    @Override
    protected void work() throws Throwable {
        List<ScoreDto> scores = BaseRestService.getScoreRestService().getScores(1);
        BusProvider.getInstance().post(new EventsStoredSimpleFeedback("Events stored!"));
        BusProvider.getInstance().post(new ScoreEvent(scores));
    }

    @Override
    protected void onError() {
        Log.d("KLOVN", "onCancel");
    }
}
