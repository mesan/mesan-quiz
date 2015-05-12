package no.mesan.mobil.mesanquiz.domain;

/**
 * Created by Thomas on 12.05.2015.
 */
public class Alternative {
    private long id;
    private long alternativeId;
    private String alternative;
    private boolean isAnswer;

    public Alternative(long id, long alternativeId, String alternative, boolean isAnswer) {
        this.id = id;
        this.alternativeId = alternativeId;
        this.alternative = alternative;
        this.isAnswer = isAnswer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }
}
