package no.mesan.mesanquiz.event;

import java.util.List;

import no.mesan.mesanquiz.model.GameDto;

/**
 * Created by janeh on 11.08.2015.
 */
public class GameListEvent extends AbstractEvent {

    private List<GameDto> gameDtoList;

    public GameListEvent(List<GameDto> gameDtoList) {
        this.gameDtoList = gameDtoList;
    }

    public List<GameDto> getGameDtoList() {
        return gameDtoList;
    }

}
