package no.mesan.mesanquiz.event;

/**
 * Created by n06849 on 22.04.2015.
 */
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
