package no.mesan.mesanquiz.service.databaseservice;

import android.content.Context;

import java.util.List;

import no.mesan.mesanquiz.model.EventDto;
import no.mesan.mesanquiz.model.ScoreDto;


public class ScoreDataBaseService extends AbstractDatabaseService {


    public ScoreDataBaseService(Context context) {
        super(context);
    }

    public void saveScore(final ScoreDto scoreToBeSaved) {
        scoreToBeSaved.save();
        EventDto.saveInTx(scoreToBeSaved);
    }

    public ScoreDto getScores() {
        List<ScoreDto> scoreList = ScoreDto.listAll(ScoreDto.class);

        ScoreDto scoreDto = scoreList != null && !scoreList.isEmpty() ? scoreList.get(0) : null;

        return scoreDto;
    }

}
