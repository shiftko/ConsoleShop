package edu.test.entities.models.messages;

import edu.test.db_mock.enums.MessageReadStatus;

public class Message {
    String login;
    String messageBody;
    MessageReadStatus status;

    public Message(String login, String messageBody) {
        this.login = login;
        this.messageBody = messageBody;
        this.status = MessageReadStatus.NOT_READ;
    }

    public String getAuthor() {
        return login;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public MessageReadStatus getStatus() {
        return status;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void setStatus(MessageReadStatus status) {
        this.status = status;
    }
}
