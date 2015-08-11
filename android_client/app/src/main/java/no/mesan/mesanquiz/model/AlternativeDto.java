package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

public class AlternativeDto extends SugarRecord<AlternativeDto> {

    private long alternativeId;
    private String alternative;
    private boolean answer;


    public AlternativeDto() {

    }

    public AlternativeDto(String alternative, boolean answer) {
        this.alternative = alternative;
        this.answer = answer;
    }

    public long getAlternativeId() {
        return alternativeId;
    }

    public void setAlternativeId(long alternativeId) {
        this.alternativeId = alternativeId;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setIsAnswer(boolean isAnswer) {
        this.answer = isAnswer;
    }
}
