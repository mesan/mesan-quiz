package no.mesan.mobil.mesanquiz.domain;

import java.util.Date;

public class Score {
    private long id;
    private Game game;
    private Person player;
    private int correctAnswers;
    private int questionCount;
    private int timeUsed;
    private Date played;

    public Score(long id, Game game, Person player, int correctAnswers, int questionCount, int timeUsed, Date played) {
        this.id = id;
        this.game = game;
        this.player = player;
        this.correctAnswers = correctAnswers;
        this.questionCount = questionCount;
        this.timeUsed = timeUsed;
        this.played = played;
    }

    public Score() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Person getPlayer() {
        return player;
    }

    public void setPlayer(Person player) {
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

    public Date getPlayed() {
        return played;
    }

    public void setPlayed(Date played) {
        this.played = played;
    }
}
