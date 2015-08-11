package no.mesan.mesanquiz.event;

import java.util.List;

import no.mesan.mesanquiz.model.ScoreDto;

public class ScoreEvent extends AbstractEvent{
    private List<ScoreDto> scoreDtoList;

    public ScoreEvent(List<ScoreDto> scoreDtoList) {
        this.scoreDtoList = scoreDtoList;
    }

    public List<ScoreDto> getScoreDtoList() {
        return scoreDtoList;
    }
}
