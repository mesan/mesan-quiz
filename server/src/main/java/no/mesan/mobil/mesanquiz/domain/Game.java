package no.mesan.mobil.mesanquiz.domain;

public class Game {
    private long id;
    private String name;
    private int numberOfQuestions;
    private Person creator;
    private String topic;
    private int timeLimit;

    public Game(long id, String name, int numberOfQuestions, Person creator, String topic, int timeLimit) {
        this.id = id;
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
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

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
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
}
