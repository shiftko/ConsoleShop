package edu.test.db_mock;

import edu.test.entities.models.messages.Message;

import java.util.ArrayList;

final public class Messages {
    private static Messages instance;
    private static ArrayList<Message> messages = new ArrayList<>();

    static {
        messages.add(new Message("Roman", "Roman: test message to admin, hi there, how are you?"));
        messages.add(new Message("Ivan", "Ivan: test message to admin, hi there, how are you?"));
    }

    private Messages() {
    }

    public static Messages getInstance() {
        if (instance == null) {
            instance = new Messages();
        }
        return instance;
    }

    public static void addMessage(Message message) {
        messages.add(message);
    }

    public static ArrayList<Message> getMessages() {
        return messages;
    }
}
