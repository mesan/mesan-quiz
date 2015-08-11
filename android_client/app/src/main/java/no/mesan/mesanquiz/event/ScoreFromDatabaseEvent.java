package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.ScoreDto;

public class ScoreFromDatabaseEvent extends AbstractEvent{
    private ScoreDto scoreDto;

    public ScoreFromDatabaseEvent(ScoreDto scoreDto) {
        this.scoreDto = scoreDto;
    }

    public ScoreDto getScoreDto() {
        return scoreDto;
    }
}
