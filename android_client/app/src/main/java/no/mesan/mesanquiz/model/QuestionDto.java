package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

import java.util.List;

public class QuestionDto extends SugarRecord<QuestionDto> {

    private int gameId;
    private String question;
    private List<AlternativeDto> alternatives;

    public QuestionDto() {
        super();
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AlternativeDto> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<AlternativeDto> alternatives) {
        this.alternatives = alternatives;
    }
}
