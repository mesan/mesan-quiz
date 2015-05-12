package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.GameDto;

public class GameFromDatabaseEvent extends AbstractEvent{
    private GameDto gameDto;

    public GameFromDatabaseEvent(GameDto gameDto) {
        this.gameDto = gameDto;
    }

    public GameDto getGameDto() {
        return gameDto;
    }
}
