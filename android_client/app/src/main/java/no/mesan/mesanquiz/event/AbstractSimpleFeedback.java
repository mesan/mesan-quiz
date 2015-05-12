package no.mesan.mesanquiz.event;


public class AbstractSimpleFeedback {
    private String messageToUser;

    public String getMessageToUser() {
        return messageToUser;
    }

    public AbstractSimpleFeedback(String messageToUser) {
        this.messageToUser = messageToUser;
    }

}
