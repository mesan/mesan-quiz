package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

import java.util.List;

public class GameDto extends SugarRecord<GameDto> {

    private String topic;
    private String shortname;
    private List<QuestionDto> questions;

    public GameDto() {
        super();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
