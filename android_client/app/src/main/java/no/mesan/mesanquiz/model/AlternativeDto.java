package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

public class AlternativeDto extends SugarRecord<AlternativeDto> {

    private long alternativeId;
    private String alternative;
    private boolean isAnswer;

    public AlternativeDto() {
        super();
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
        return isAnswer;
    }

    public void setIsAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }
}
