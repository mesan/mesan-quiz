package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

import org.joda.time.DateTime;

public class ScoreDto extends SugarRecord<ScoreDto> {

    private GameDto game;
    private PersonDto player;

    private int correctAnswers;
    private int questionCount;
    private int timeUsed;
    private DateTime played;

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public PersonDto getPlayer() {
        return player;
    }

    public void setPlayer(PersonDto player) {
        this.player = player;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    public DateTime getPlayed() {
        return played;
    }

    public void setPlayed(DateTime played) {
        this.played = played;
    }

    @Override
    public String toString() {
        return "ScoreDto{" +
                "game=" + game +
                ", player=" + player +
                ", correctAnswers=" + correctAnswers +
                ", questionCount=" + questionCount +
                ", timeUsed=" + timeUsed +
                ", played=" + played +
                '}';
    }
}
