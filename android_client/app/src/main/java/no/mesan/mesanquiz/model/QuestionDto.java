package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

import java.util.List;

public class QuestionDto extends SugarRecord<QuestionDto> {

    private GameDto game;

    public QuestionDto() {
        super();
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }
}
