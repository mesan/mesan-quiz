package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

import java.util.List;

public class GameDto extends SugarRecord<GameDto> {

    private String name;
    private int numberOfQuestions;
    private PersonDto creator;
    private String topic;
    private int timeLimit;
    private List<QuestionDto> questions;

    public GameDto() {
        super();
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

    public PersonDto getCreator() {
        return creator;
    }

    public void setCreator(PersonDto creator) {
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

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
