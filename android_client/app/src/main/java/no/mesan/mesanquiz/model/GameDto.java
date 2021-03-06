package no.mesan.mesanquiz.model;

import com.google.android.gms.games.quest.Quest;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class GameDto extends SugarRecord<GameDto> {

    private String name;
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

    public void addQuestion(QuestionDto questionDto) {
        if(questions == null) {
            questions = new ArrayList<>();
        }

        questions.add(questionDto);
    }
}
