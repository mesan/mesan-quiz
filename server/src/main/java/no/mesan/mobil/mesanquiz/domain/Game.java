package no.mesan.mobil.mesanquiz.domain;

import java.util.List;

public class Game {
    private long id;
    private String name;
    private Person creator;
    private String topic;
    private int timeLimit;
    private List<QuestionAbc> questions;

    public Game(long id, String name, Person creator, String topic, int timeLimit) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.topic = topic;
        this.timeLimit = timeLimit;
    }

    public Game() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public List<QuestionAbc> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAbc> questions) {
        this.questions = questions;
    }
}
