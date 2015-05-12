package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.GameDto;

public class GameEvent extends AbstractEvent{
    private GameDto gameDto;

    public GameEvent(GameDto gameDto) {
        this.gameDto = gameDto;
    }

    public GameDto getGameDto() {
        return gameDto;
    }
}
