package no.mesan.mesanquiz.event;

public class AbstractEvent {

    private String errorMessage;

    public boolean hasErrors() {
        return errorMessage != null;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
